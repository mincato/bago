'use strict';

angular.module('developers').config(['$stateProvider',
	function($stateProvider) {
		$stateProvider.
		state('main.listDevelopers', {
			url: '/developers/list/:origin',
			templateUrl: 'modules/developers/views/dashboard-developers.view.html',
            data: {
                permission: 'READ_DEVELOPER'
            }
		}).
		state('main.createDeveloper', {
			url: '/developers/create',
			templateUrl: 'modules/developers/views/create-developer.view.html',
            data: {
                permission: 'CREATE_DEVELOPER'
            }
		}).
		state('main.viewDeveloper', {
			url: '/developers/:developerId/view',
			templateUrl: 'modules/developers/views/view-developer.view.html',
            data: {
                permission: 'READ_DEVELOPER'
            }
		}).
		state('main.editDeveloper', {
			url: '/developers/:developerId/edit',
			templateUrl: 'modules/developers/views/edit-developer.view.html',
            data: {
                permission: 'UPDATE_DEVELOPER'
            }
		});
	}
]);