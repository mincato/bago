'use strict';

module.exports = function(grunt) {
	// Unified Watch Object
	var watchFiles = {
		serverViews: ['app/views/**/*.*'],
		serverJS: ['gruntfile.js', 'server.js'],
		clientViews: ['app/modules/**/views/**/*.html'],
		clientJS: ['app/js/*.js', 'app/modules/**/*.js'],
		clientCSS: ['app/modules/**/*.css']
	};

	// Project Configuration
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		watch: {
			serverViews: {
				files: watchFiles.serverViews,
				options: {
					livereload: true
				}
			},
			serverJS: {
				files: watchFiles.serverJS,
				tasks: ['jshint'],
				options: {
					livereload: true
				}
			},
			clientViews: {
				files: watchFiles.clientViews,
				options: {
					livereload: true,
				}
			},
			clientJS: {
				files: watchFiles.clientJS,
				tasks: ['jshint'],
				options: {
					livereload: true
				}
			},
			clientCSS: {
				files: watchFiles.clientCSS,
				tasks: ['csslint'],
				options: {
					livereload: true
				}
			}
		},
		jshint: {
			all: {
				src: watchFiles.clientJS.concat(watchFiles.serverJS),
				options: {
					jshintrc: true
				}
			}
		},
		csslint: {
			options: {
				csslintrc: '.csslintrc',
			},
			all: {
				src: watchFiles.clientCSS
			}
		},
		uglify: {
			production: {
				options: {
					mangle: false
				},
				files: {
					'app/dist/application.min.js': 'app/dist/application.js'
				}
			}
		},
		cssmin: {
			combine: {
				files: {
					'app/dist/application.min.css': '<%= applicationCSSFiles %>'
				}
			}
		},
		nodemon: {
			dev: {
				script: 'server.js',
				options: {
					nodeArgs: ['--debug'],
					ext: 'js,html',
					watch: watchFiles.serverViews.concat(watchFiles.serverJS)
				}
			}
		},
		'node-inspector': {
			custom: {
				options: {
					'web-port': 1337,
					'web-host': 'localhost',
					'debug-port': 5858,
					'save-live-edit': true,
					'no-preload': true,
					'stack-trace-limit': 50,
					'hidden': []
				}
			}
		},
		ngAnnotate: {
			production: {
				files: {
					'app/dist/application.js': '<%= applicationJavaScriptFiles %>'
				}
			}
		},
		concurrent: {
			default: ['nodemon', 'watch'],
			debug: ['nodemon', 'watch', 'node-inspector'],
			options: {
				logConcurrentOutput: true,
				limit: 10
			}
		},
		copy: {
			main: {
				expand: true,
				cwd: 'app/',
				src: ['modules/**/views/*.html', 'modules/**/img/**'],
				dest: 'app/dist'
			},
            fontsAwesome: {
                expand: true,
                cwd: 'app/bower_components/components-font-awesome/',
                src: ['fonts/**'],
                dest: 'app/dist'
            },
            fontsBootstrap: {
                expand: true,
                cwd: 'app/bower_components/bootstrap/',
                src: ['fonts/**'],
                dest: 'app/dist'
            }
		},
		clean: {
			main: ['app/dist'],
			app: ['app/dist/application.js']
		},
        less: {
            production: {
                options: {
                    compress: false,
                    yuicompress: false,
                    modifyVars: {
                        'fa-font-path': 'fonts',
                        'icon-font-path': '~"fonts/"'
                    }
                },
                files: {
                      // target.css file: source.less file
                      'app/bower_components/components-font-awesome/css/font-awesome.css': 'app/bower_components/components-font-awesome/less/font-awesome.less',
                      'app/bower_components/bootstrap/dist/css/bootstrap.css': 'app/bower_components/bootstrap/less/bootstrap.less'
                }
            },
            development: {
                options: {
                    compress: false,
                    yuicompress: false,
                    modifyVars: {
                        'fa-font-path': '~"../fonts"',
                        'icon-font-path': '~"../fonts/"'
                    }
                },
                files: {
                      // target.css file: source.less file
                      'app/bower_components/components-font-awesome/css/font-awesome.css': 'app/bower_components/components-font-awesome/less/font-awesome.less',
                      'app/bower_components/bootstrap/dist/css/bootstrap.css': 'app/bower_components/bootstrap/less/bootstrap.less'
                }
            }
        }        
	});

	// Load NPM tasks
	require('load-grunt-tasks')(grunt);
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-less');

	// Making grunt default to force in order not to break the project.
	grunt.option('force', true);

	// A Task for loading the configuration object
	grunt.task.registerTask('loadConfig', 'Task that loads the config into a grunt option.', function() {
		var config = require('./config');

		grunt.config.set('applicationJavaScriptFiles', config.jsFiles);
		grunt.config.set('applicationCSSFiles', config.cssFiles);
	});

	// A Task to run swig to process the server.view.html templates
	grunt.registerTask('runSwig', 'Task that run swig for process the view templates.', function() {

		var swig  = require('swig');

		var variables = {
			jsFiles: ['application.min.js'],
			cssFiles: ['application.min.css'],
			buildingDist: true
		};

		var indexProcessed = swig.renderFile(__dirname +'/app/index.template.html', variables);
		grunt.file.write(__dirname + '/app/dist/index.html', indexProcessed);
	});

	// Default task(s).
	grunt.registerTask('default', ['less:development', 'lint', 'concurrent:default']);

	// Lint task(s).
	grunt.registerTask('lint', ['jshint', 'csslint']);

	// Build task(s).
	grunt.registerTask('build', ['less:production', 'clean', 'lint', 'loadConfig', 'ngAnnotate', 'uglify', 'cssmin', 'runSwig', 'copy', 'clean:app']);

};