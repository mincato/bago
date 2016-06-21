'use strict';

module.exports = {
	app: {
		title: 'bago-archetype',
		description: 'Bago Archetype',
		keywords: 'AngularJS'
	},
	port: process.env.PORT || 3000,
    jsFiles: [
        'app/bower_components/jquery/dist/jquery.js',
        'app/bower_components/angular/angular.js',
        'app/bower_components/moment/min/moment-with-locales.js',
        'app/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js',
        'app/bower_components/bootstrap/dist/js/bootstrap.js', 
        'app/bower_components/angular-resource/angular-resource.js', 
        'app/bower_components/angular-ui-router/release/angular-ui-router.js',
        'app/bower_components/angular-ui-utils/ui-utils.js',
        'app/bower_components/angular-bootstrap/ui-bootstrap-tpls.js',
        'app/lib-sb-admin-angular/sb-admin-angular/js/sb-admin-2.js',
        'app/bower_components/metisMenu/dist/metisMenu.js',
        'app/bower_components/angular-toggle-switch/angular-toggle-switch.js',
        'app/bower_components/angular-auto-validate/dist/jcs-auto-validate.js',
        'app/bower_components/Chart.js/Chart.js',
        'app/bower_components/angular-chart.js/dist/angular-chart.js',
        'app/config.js',
        'app/application.js',
        'app/modules/*/*.js',
        'app/modules/*/*/*.js'
    ],
    cssFiles: [
        'app/bower_components/bootstrap/dist/css/bootstrap.css',
        'app/lib-sb-admin-angular/sb-admin-angular/styles/sb-admin-2.css',
        'app/bower_components/angular-toggle-switch/angular-toggle-bootstrap.css',
        'app/lib-sb-admin-angular/sb-admin-angular/styles/main.css',
        'app/lib-sb-admin-angular/sb-admin-angular/styles/timeline.css',
        'app/bower_components/components-font-awesome/css/font-awesome.css',
        'app/bower_components/metisMenu/dist/metisMenu.css',
        'app/bower_components/angular-chart.js/dist/angular-chart.css',
        'app/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css',
        'app/modules/**/css/*.css'
    ]
};