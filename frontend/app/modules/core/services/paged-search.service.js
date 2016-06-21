'use strict';

angular.module('core').service('PagedSearch',
    function () {

        this.reset = function (searchObj) {
            searchObj.items = [];
            searchObj.pageSize = searchObj.pageSizeDefault;
            searchObj.pageNumber = 1;
            searchObj.totalSize = 0;
            searchObj.numPages = 0;
        };

        this.filterNullProps = function (item) {
            var key, value;
            for (key in item) {
                value = item[key];
                if (value === null || value.length === 0) {
                    delete item[key];
                }
            }
        };

        var fillResult = function (res, searchObj) {
            searchObj.items = [];
            angular.forEach(res.items, function(value, key) {
                searchObj.items.push(value);
            });
            searchObj.pageSize = res.pageSize;
            searchObj.totalSize = res.totalSize;
            searchObj.numPages = Math.floor(searchObj.totalSize / searchObj.pageSize) + 1;
        }.bind(this);

        this.populate = function (service, searchObj, callbackSuccess, callbackError) {
            var q = JSON.parse(JSON.stringify(searchObj.query));
            this.filterNullProps(q);
            q.pageNumber = searchObj.pageNumber - 1;
            q.pageSize = searchObj.pageSize;
            service.query(q, function (response) {
                fillResult(response, searchObj);
                if (callbackSuccess) {
                    callbackSuccess();
                }
            }, function (response) {
                if (callbackError) {
                    callbackError();
                }
            });
        };
    }
);