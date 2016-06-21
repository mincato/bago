'use strict';

angular.module('developers').service('PagedSearchDeveloperService', ['DeveloperService', 'PagedSearch',
	function(DeveloperService, PagedSearch) {

        this.items = [];
		this.query = {};
       
        this.executedFind = {
            result : false
        };
        
        this.defaultQuery = {
        };

        var queryCallback = function() {
            this.executedFind.result = true;
        }.bind(this);
        
        this.populate = function() {
			PagedSearch.populate(DeveloperService, this, queryCallback);
		};

	    this.reset = function() {
            this.pageNumber = 1;
			this.pageSize  = 10;
	    	this.totalSize = 0;
	    	this.numPages  = 0;
            this.items.length = 0;
            this.query = angular.copy(this.defaultQuery);
            this.executedFind.result = false;
        };

        this.reset();
	}
]);