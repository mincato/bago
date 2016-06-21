'use strict';

angular.module('core').service('MomentService', [ '$window',
	function($window) {
        return {
            getMoment: function() {
                return $window.moment;
            }
        };
    }
]);