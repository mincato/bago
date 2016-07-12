'use strict';

angular.module('reports').config(['$stateProvider',
	function($stateProvider) {
		$stateProvider.
		state('main.reports', {
			url: '/reports',
			templateUrl: 'modules/reports/views/reports.view.html',
            data: {
                permission: 'READ_DEVELOPER'
            }
		});
	}
]);