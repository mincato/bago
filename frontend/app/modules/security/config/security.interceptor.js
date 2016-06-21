'use strict';

angular.module('security').config(['$httpProvider',
	function($httpProvider) {
        
//        $httpProvider.defaults.withCredentials = true;
        
		$httpProvider.interceptors.push(['$q', '$location', 'SessionService', '$injector', 'Configuration',
			function($q, $location, SessionService, $injector, Configuration) {

				var redirectToLogin = function() {
					SessionService.invalidate();
					$location.path('login');
				};
                
                var tryToRefreshToken = function(rejection) {
                    
                    var url = Configuration.serviceContext + 'auth/refresh';
                    
                    // defer until we can re-request a new token
                    var deferred = $q.defer();                    
                    
                    var token = SessionService.getToken();                    
                    if (token) {
                        
                        $injector.get('$http').post(url, {}, {
                            headers: {'Authorization': 'Bearer ' + token}
                        }).then(
                            function(newToken) {
                                if (newToken && newToken.data && newToken.data.value) {
                                    SessionService.updateToken(newToken.data.value);
                                    // Now, let's try the original request
                                    $injector.get('$http')(rejection.config).then(function(response) {
                                        // we have a successful response - resolve it using deferred
                                        deferred.resolve(response);
                                    },function(response) {
                                        // something went wrong
                                        deferred.reject();
                                    });                                    
                                } else {
                                    redirectToLogin();
                                }
                            }, function (refreshError) {
                                redirectToLogin();
                            }
                        );
                        return deferred.promise;
                    } else {
                        redirectToLogin();
                    }
                    
                    
                };

				return {
					request: function(config) {
						config.headers = config.headers || {};
						var token = SessionService.getToken();
						if (token) {
							config.headers.Authorization = 'Bearer ' + token;                            
						}
						return config;
					},
					responseError: function(rejection) {
						switch (rejection.status) {
							case 401:
                                if (rejection.data && rejection.data.code === 8) {
                                    // Token has expired, we have to call 'refresh' to regeneration
                                    // Here, we cannot use $http directly as will cause a circular ref
                                    return tryToRefreshToken(rejection);
                                } else {
								    redirectToLogin();
                                }
								break;
							case 403:
								// Add unauthorized behaviour 
								break;
						}

						return $q.reject(rejection);
					}
				};
			}
		]);
	}
]);
