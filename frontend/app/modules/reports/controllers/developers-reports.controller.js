'use strict';

angular.module('reports').controller('ReportController', ['$scope', '$stateParams', '$state', 'ReportService', 'Messages', 'DeveloperService',
	function($scope, $stateParams, $state, ReportService, Messages, DeveloperService) {
        
		var vm = this;

        vm.seniorities = DeveloperService.seniorities();
        vm.params = {};

        vm.generate = function() {
            ReportService.download(vm.params, function(response) {
                var url = URL.createObjectURL(response.data);
                var a = document.createElement('a');
                a.href = url;
                a.download = 'developer-report.pdf';
                a.target = '_blank';
                a.click();
            }, function() {
                var error = Messages.getErrorMessage();
                $scope.$emit('showError', error);
            });
        };
        
	}
]);