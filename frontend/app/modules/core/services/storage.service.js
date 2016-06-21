'use strict';

angular.module('core').service('StorageService', [ '$window',
	function($window) {
        return $window.sessionStorage;
    }
]);
