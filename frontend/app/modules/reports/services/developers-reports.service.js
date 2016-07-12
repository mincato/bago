'use strict';

angular.module('reports').factory('ReportService', ['$window', '$http', '$resource', 'Configuration', 'SessionService',

    function($window, $http, $resource, Configuration, SessionService) {

        var view = function(params) {
            var token = SessionService.getToken();
            var url = Configuration.viewerContext + 'developers/details-report.pdf?Authorization=Bearer ' + token;
            var key, value;
            for (key in params) {
                value = params[key];
                if (value !== null && value.length !== 0) {
                    url += '&' + key + '=' + value;
                }
            }
            $window.open(encodeURI(url));
        };

        
        return {
            view: view
        };

    }
]);