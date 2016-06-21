'use strict';

angular.module('demo')
    .directive('notifications', function () {
        return {
            templateUrl: 'modules/demo/views/notifications.view.html',
            restrict: 'E',
            replace: true,
        };
    });