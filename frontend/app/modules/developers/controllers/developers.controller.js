'use strict';

angular.module('developers').controller('DeveloperController', ['$scope', '$stateParams', '$state', 'DeveloperService', 'PagedSearchDeveloperService', 'Messages',
	function($scope, $stateParams, $state, DeveloperService, PagedSearchDeveloperService, Messages) {
        
		var vm = this;

        vm.developer = new DeveloperService();
        vm.search = PagedSearchDeveloperService;
        vm.seniorities = DeveloperService.seniorities();
            

        var shouldReset = function() {
            return ($stateParams.origin === 'menu');
        };
        
        var backToSearch = function () {
            $state.transitionTo('main.listDevelopers');
        };
        
		vm.create = function() {
            
           vm.developer.$save(function(response) {
               backToSearch();
            }, function(errorResponse) {
                var error = Messages.getErrorMessage(errorResponse.data);
                $scope.$emit('showError', error);
			});
		};

		vm.remove = function(id) {
            DeveloperService.delete({developerId: id}, function(response) {
               vm.find();
            }, function(errorResponse) {
                    var error = Messages.getErrorMessage(errorResponse.data);
                    $scope.$emit('showError', error);
			});
        };

		vm.update = function() {
			vm.developer.$update(function() {
				backToSearch();
			}, function(errorResponse) {
                var error = Messages.getErrorMessage(errorResponse.data);
                $scope.$emit('showError', error);
			});
		};

		vm.findOne = function() {
			vm.developer = DeveloperService.get({
				developerId: $stateParams.developerId
			});
		};
        
        
        vm.init = function () {
            if (shouldReset()) {
                vm.reset();
            } else if (vm.search.executedFind.result === true) {
                vm.find();
            }
        };

        vm.reset = function () {
            vm.search.reset();
        };

        vm.find = function () {
            vm.search.populate();
        };
        
	}
]);