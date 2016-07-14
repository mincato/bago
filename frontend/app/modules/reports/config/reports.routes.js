'use strict';

angular.module('reports').config(['$stateProvider',
	function($stateProvider) {
		$stateProvider.
		state('main.reports', {
			url: '/reports/developers',
			templateUrl: 'modules/reports/views/developers-reports.view.html',
            data: {
                permission: 'READ_DEVELOPER'
            }
		});
	}
]);