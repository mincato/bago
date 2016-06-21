'use strict';

angular.module('core')
	.directive('header',function(){
		return {
            templateUrl:'modules/core/views/header.view.html',
            restrict: 'E',
            replace: true
    	};
	});


