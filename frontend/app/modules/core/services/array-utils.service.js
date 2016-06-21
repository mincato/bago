'use strict';

angular.module('core').factory('ArrayUtils', [
	function() {

		var indexOfByProperty = function(array, property, value) {
			var result = -1;
			array.some(function(entry, index) {
				if (entry[property] === value) {
					result = index;
					return true;
				}
			});
			return result;
		};
        
        var findObjectByProperty = function(array, property, value) {
            var index = indexOfByProperty(array, property, value);
            return (index === -1) ? null : array[index];
        };

	    return {
	    	indexOfByProperty : indexOfByProperty,
            findObjectByProperty : findObjectByProperty
	    };
	}
]);
