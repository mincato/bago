'use strict';

angular.module('reports').factory('ReportService', ['$resource', 'Configuration',
    function($resource, Configuration) {

        var url = Configuration.serviceContext + 'developers/details-report.pdf';
        
        return $resource(url, {}, {
            download: {
                method: 'GET',
                responseType: 'blob',
                transformResponse: function(data, headers) {
                    return {
                        data: data
                    };
                }
            }
        });
    }
]);