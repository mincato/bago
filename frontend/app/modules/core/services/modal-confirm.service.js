'use strict';

//Menu service used for managing  menus
angular.module('core').service('ModalConfirmService', 
	['$modal', '$log', 
function($modal, $log) {
	this.open = function (msg, f) {
		var modalInstance = $modal.open({
		  templateUrl: 'modules/core/views/modal-confirm.client.view.html',
		  controller: 'ModalConfirmController',
		  resolve: {
		    model: function () {
              return msg ? msg : 'Would you like to confirm the operation?';
		    }
		  }
		});

		modalInstance.result.then(function () {
			f();
		}, function () {
		//	$log.info('Modal dismissed at: ' + new Date());
		});
	};
}]);

angular.module('core').controller('ModalConfirmController', 
	['$scope', '$modalInstance', 'model', 
function ($scope, $modalInstance, model) {

  $scope.model = model;
  
  $scope.ok = function () {
    $modalInstance.close();
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };

}]);

