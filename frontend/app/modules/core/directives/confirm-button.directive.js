'use strict';

angular.module('core').directive('confirmButton', ['ModalConfirmService', function(ModalConfirmService) {
  return {
    restrict: 'A',
    scope: {
      msg: '@',
      action: '&'
    },
    link: function(scope, element, attr) {
      element.on('mousedown', function(event) {
      	ModalConfirmService.open(scope.msg, scope.action);
      });
    }
  };
}]);