  'use strict';

  angular
      .module('core')
      .directive('datetimepicker', [
      '$timeout', 'MomentService',
      function ($timeout, MomentService) {
              return {
                  require: '?ngModel',
                  restrict: 'EA',
                  scope: {
                      datetimepickerOptions: '@',
                      minDate: '=',
                      onDateChangeFunction: '&',
                      onDateClickFunction: '&'
                  },
                  link: function ($scope, $element, $attrs, controller) {
                      var options = $scope.$eval($attrs.datetimepickerOptions);
                      if ($scope.minDate) {
                          options.minDate = $scope.minDate;
                      }
                      options.useCurrent = false;
                      $element.datetimepicker(options);

                      controller.$formatters.push(function (value) {
                          var date = MomentService.getMoment()(value, $scope.$eval($attrs.datetimepickerOptions).format);
                          if (date.isValid()) {
                              return date.format($scope.$eval($attrs.datetimepickerOptions).format);
                          }
                          return '';
                      });

                      controller.$render = function () {
                          var result = controller.$viewValue;
                          if (result !== undefined && result !== '') {
                              $element.data('DateTimePicker').date(result);
                          } else {
                              $element.data('DateTimePicker').date(null);

                          }
                      };

                      $element.on('dp.change', function (event) {
                          $timeout(function () {
                              var dtp = $element.data('DateTimePicker');
                              if (dtp.date() === null) {
                                  controller.$setViewValue(null);
                              } else {
                                  controller.$setViewValue(dtp.date().format($scope.$eval($attrs.datetimepickerOptions).format));
                              }
                              $scope.onDateChangeFunction();

                          });
                      });

                      $element.on('click', function () {
                          $scope.onDateClickFunction();
                      });

                  }
              };
      }
    ]);