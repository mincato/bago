'use strict';

angular.module('security').controller('LoginController', ['$scope', 'AuthenticationService', '$location', 'Configuration', 'Messages',
    function ($scope, AuthenticationService, $location, Configuration, Messages) {
        
        $scope.init = function() {            
            $scope.credentials = {
                username: '',
                password: ''
            };
        };
        
        var loginSuccess = function(response) {
            $location.path('dashboard');
        };
        
        var loginError = function(response) {
            var error = Messages.getErrorMessage(response);            
            $scope.$emit('showError', error);
        };
        
        $scope.login = function() {
            AuthenticationService.login($scope.credentials, loginSuccess, loginError);
        };
        
        $scope.init();
    }
]);
