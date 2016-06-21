'use strict';

angular.module('core').controller('HeaderController', ['$scope', 'Menus', '$location', 'AuthenticationService',
	function($scope, Menus, $location, AuthenticationService) {
		
        var vm = this;

		vm.isCollapsed = false;
		vm.menu = Menus.getMenu('topbar');
        vm.loggedUser = function() {
            var user = AuthenticationService.getUser();
            return user.firstName + ' ' + user.lastName;
        };

        vm.isLogged = function() {
            return AuthenticationService.isLogged();
        };
        
        vm.logout = function() {
            AuthenticationService.logout(function(response) {
                $location.path('login');
            }, function(errorResponse) {
                console.error('Error al cerrar sesión.');
            });
        };
        
		vm.toggleCollapsibleMenu = function() {
			vm.isCollapsed = !vm.isCollapsed;
		};

		// Collapsing the menu after navigation
		$scope.$on('$stateChangeSuccess', function() {
			vm.isCollapsed = false;
		});
	}
]);