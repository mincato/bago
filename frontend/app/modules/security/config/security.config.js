'use strict';

angular.module('security').config(['$stateProvider', '$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {

		$stateProvider.
		state('login', {
			url: '/login',
			templateUrl: 'modules/security/views/login.view.html'
		});        
	}
]);
