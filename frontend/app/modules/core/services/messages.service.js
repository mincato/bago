'use strict';

angular.module('core').factory('Messages', [
	function() {
        
        var getErrorMessage = function(response) {
            return (response && response.message) ? response.message : 'Error Inesperado';
        };

	    return {
	    	getErrorMessage : getErrorMessage
	    };
	}
]);
