'use strict';

angular.module('developers').factory('DeveloperService', ['$resource', 'Configuration',
	function($resource, Configuration) {

		var url = Configuration.serviceContext + 'developers/:developerId/:operation';
		
		return $resource(url, {
            developerId: '@_id'
		}, {
			update: {
				method: 'PUT'
			},
            
            delete: {
				method: 'DELETE'
			},
            
            query: {
                method: 'GET',
                isArray: false
            },
            
            seniorities: {
                method: 'GET',
                isArray: true,
                params: {
                    operation: 'seniorities'
                }
            }
		});
	}
]);