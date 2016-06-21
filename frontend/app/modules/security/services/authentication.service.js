'use strict';

angular.module('security').factory('AuthenticationService', [
    '$http','SessionService', 'Configuration',
    function($http, SessionService, Configuration) {

        var login = function(credentials, sucess, error) {

            var url = Configuration.serviceContext + 'auth/login';
            
            $http.post(url, credentials).success(function(response) {

                SessionService.saveUser(response.user);
                SessionService.savePermissions(response.permissions);
                if (sucess) {
                    sucess(response);
                }

            }).error(function(response) {
                if (error) {
                    error(response);
                }
            });

        };
        
        var logout = function(success, error) {            
            
            var url = Configuration.serviceContext + 'auth/logout';
            
            $http.post(url).success(function(response) {
                
                SessionService.invalidate();
                
                if (success) {
                    success(response);
                }
                
            }).error(function(response) {
                if (error) {
                    error(response);
                }
            });
        };
        
        var isLogged = function() {
            return SessionService.isLogged();
        };

        var getUser = function() {
            return SessionService.loadUser();
        };
        
        var hasPermission = function(permission) {
            
            if (!isLogged()) {
                return false;
            }
            
            var permissions = SessionService.loadPermissions();
            
            var found = false;
            if (permissions) {
                angular.forEach(permissions, function(itemPermission) {
                    if (itemPermission === permission) {
                        found = true;
                    }
                });
            }
            return found;
        };
        
        return {
            login: login,
            logout: logout,
            isLogged: isLogged,
            getUser: getUser,
            hasPermission: hasPermission
        };
    }
]);
