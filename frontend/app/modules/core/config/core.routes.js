'use strict';

// Setting up route
angular.module('core').config(['$stateProvider', '$urlRouterProvider',
	function ($stateProvider, $urlRouterProvider) {
    // Redirect to home view when route not found
    $urlRouterProvider.otherwise('/main/home');

    $stateProvider
        .state('main', {
            url: '/main',
            templateUrl: 'modules/core/views/main.view.html'
        }).
    state('main.home', {
        url: '/home',
        templateUrl: 'modules/core/views/home.view.html'
    });
            
}]);