'use strict';

angular.module('demo')
    .directive('stats', function () {
            return {
                templateUrl: 'modules/demo/views/stats.view.html',
                restrict: 'E',
                replace: true,
                scope: {
                    'model': '=',
                    'comments': '@',
                    'number': '@',
                    'name': '@',
                    'colour': '@',
                    'details': '@',
                    'type': '@',
                    'goto': '@'
                }
            };
});