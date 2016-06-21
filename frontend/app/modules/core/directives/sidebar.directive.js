'use strict';

angular.module('core')
    .directive('sidebar', ['$location', 'AuthenticationService',function () {
        return {
            templateUrl: 'modules/core/views/sidebar.view.html',
            restrict: 'E',
            replace: true,
            scope: {},
            controller: function ($scope, AuthenticationService) {
                $scope.authentication = AuthenticationService;
                $scope.selectedMenu = '';
                $scope.collapseVar = 0;
                $scope.multiCollapseVar = 0;

                $scope.check = function (x) {

                    if (x === $scope.collapseVar)
                        $scope.collapseVar = 0;
                    else
                        $scope.collapseVar = x;
                };

                $scope.multiCheck = function (y) {

                    if (y === $scope.multiCollapseVar)
                        $scope.multiCollapseVar = 0;
                    else
                        $scope.multiCollapseVar = y;
                };
            }
        };
  }]);