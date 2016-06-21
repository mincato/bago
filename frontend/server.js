'use strict';

var http = require('http');
var config = require('./config');
var swig = require('swig');
var glob = require('glob-all');

var url = require('url'),
    path = require('path'),
    fs = require('fs');

var mimeTypes = {
    'html': 'text/html',
    'jpeg': 'image/jpeg',
    'jpg': 'image/jpeg',
    'png': 'image/png',
    'js': 'text/javascript',
    'css': 'text/css'};

var variables = {
    jsFiles: glob.sync(config.jsFiles),
    cssFiles: glob.sync(config.cssFiles),
    buildingDist: false
};

http.createServer(function(request, response) {
    
    var uri = url.parse(request.url).pathname;
    if (!uri.startsWith('/app')) {
        uri = '/app' + uri;
    }
    var filename = path.join(process.cwd(), uri);
    
    fs.exists(filename, function(exists) {
        if(!exists) {
              response.writeHead(404, {'Content-Type': 'text/plain'});
              response.write('404 Not Found\n');
              response.end();
              return;
        }    

        if (fs.statSync(filename).isDirectory()) {
            var indexProcessed = swig.renderFile('app/index.template.html', variables);

            response.writeHead(200, {'Content-Type': 'text/html'});
            response.write(indexProcessed, 'binary');
            response.end();
            return;
        }

        fs.readFile(filename, 'binary', function(err, file) {
          if(err) {
            response.writeHead(500, {'Content-Type': 'text/plain'});
            response.write(err + '\n');
            response.end();
            return;
          }

          var mimeType = mimeTypes[path.extname(filename).split('.')[1]];
          response.writeHead(200, {'Content-Type': mimeType});
          response.write(file, 'binary');
          response.end();
        });
    });
    
}).listen(parseInt(config.port, 10));

// Logging initialization
console.log('Front-End application started on port ' + config.port);