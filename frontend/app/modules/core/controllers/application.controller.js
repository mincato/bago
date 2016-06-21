'use strict';

angular.module('core').controller('ApplicationController', ['$scope', 'AuthenticationService',
	function($scope, AuthenticationService) {
		
        var vm = this;

        vm.authentication = AuthenticationService;
        
        vm.messages = {};
        
        $scope.$on('showMessage', function(event, message) {
            vm.messages.info = message;
        });
        
        $scope.$on('showError', function(event, error) {
            vm.messages.danger = error;
        });

        $scope.$on('showWarning', function(event, warning) {
            vm.messages.warning = warning;
        });

        $scope.$on('showSuccess', function(event, success) {
            vm.messages.success = success;
        });        
	}
]);