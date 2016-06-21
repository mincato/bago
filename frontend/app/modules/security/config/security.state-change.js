'use strict';

angular.module('security').run(function($rootScope, $location, AuthenticationService, $window, $log) {
    
	$rootScope.$on('$stateChangeStart', function (event, next) {
        
		if (!AuthenticationService.isLogged()) {
			$location.path('login');
		}
        
		if (next.data && next.data.permission && !AuthenticationService.hasPermission(next.data.permission)) {
			$log.error('Acceso Denegado: Se requiere permiso ' + next.data.permission);
			event.preventDefault();
			return false;
		}
	});
});
