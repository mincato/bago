'use strict';

angular.module('reports').controller('ReportController', ['$scope', '$stateParams', '$state', 'ReportService', 'Messages', 'DeveloperService',
	function($scope, $stateParams, $state, ReportService, Messages, DeveloperService) {
        
		var vm = this;

        vm.seniorities = DeveloperService.seniorities();
        vm.params = {};

        vm.generate = function() {
            ReportService.view(vm.params);
        };
        
	}
]);