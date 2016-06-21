'use strict';

/**
 * @ngdoc directive
 * @name izzyposWebApp.directive:adminPosHeader
 * @description
 * # adminPosHeader
 */
angular.module('core')
	.directive('headerNotification',function(){
		return {
            templateUrl:'modules/core/views/header-notification.view.html',
            restrict: 'E',
            replace: true
    	};
	});


