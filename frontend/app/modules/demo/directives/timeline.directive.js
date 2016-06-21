'use strict';

angular.module('demo')
    .directive('timeline', function () {
        return {
            templateUrl: 'modules/demo/views/timeline.view.html',
            restrict: 'E',
            replace: true,
        };
    });