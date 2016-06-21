'use strict';

angular.module('demo')
    .directive('chat', function () {
        return {
            templateUrl: 'modules/demo/views/chat.view.html',
            restrict: 'E',
            replace: true,
        };
    });