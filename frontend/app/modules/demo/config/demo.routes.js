'use strict';

angular.module('demo').config(['$stateProvider',
	function($stateProvider) {
		$stateProvider.
		state('main.dashboard', {
			url: '/demo/dashboard',
			templateUrl: 'modules/demo/views/dashboard.view.html'
		}).state('main.chart', {
			url: '/demo/chart',
			templateUrl: 'modules/demo/views/chart.view.html'
		}).state('main.table', {
			url: '/demo/table',
			templateUrl: 'modules/demo/views/table.view.html'
		})
        .state('main.form', {
			url: '/demo/form',
			templateUrl: 'modules/demo/views/form.view.html'
		}).state('main.panels-wells', {
			url: '/demo/panels-wells',
			templateUrl: 'modules/demo/views/panels-wells.view.html'
		}).state('main.buttons', {
			url: '/demo/buttons',
			templateUrl: 'modules/demo/views/buttons.view.html'
		}).state('main.panels-notifications', {
			url: '/demo/panels-notifications',
			templateUrl: 'modules/demo/views/panels-notifications.view.html'
		}).state('main.typography', {
			url: '/demo/typography',
			templateUrl: 'modules/demo/views/typography.view.html'
		}).state('main.icons', {
			url: '/demo/icons',
			templateUrl: 'modules/demo/views/icons.view.html'
		}).state('main.grid', {
			url: '/demo/grid',
			templateUrl: 'modules/demo/views/grid.view.html'
		});
	}
]);