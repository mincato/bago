# bago Archetype

Para construir este Front-End web nos hemos basado en tres proyectos:

 * Generador Angular-Seed: https://github.com/angular/angular-seed
 * Generador Mean.js: http://meanjs.org/
 * Template SB Admin Angular: http://startangular.com/product/sb-admin-angular-theme/

El resultado es un proyecto que utiliza la base de Angular-Seed, la estructura de Mean.JS, y los widgets y el look & feel de SB Admin Angular.

### Prerequisites

Es necesario contar con la última versión de Node.js instalada. Se recomienda utilizar Node Version Manager (NPM) para la instalación y gestión de distintas versiones.

Es necesario instalar de forma global grunt-cli y bower. Parado en el root del frontend se deben ejecutar estos dos comandos:

```
npm install -g grunt-cli
npm install -g bower
```

Luego se deben instalar las dependencias del proyecto:

```
npm install
```

### Run the Application

Hemos incluido un servidor HTTP bien simple desarrollado con Node.js, que levanta en el puerto 3000 por default (ver configuración de puerto en config.js).

Para levantar el servidor simplemente ejecutar la tarea default de grunt:

```
grunt
```

Now browse to the app at `http://localhost:3000/`.

Los servicios apuntan al puerto 8080, con lo cual debe haber un servidor de API levantado escuchando.

### Running the App in Production

Se ha incluido una tarea en Grunt para empaquetar la aplicación para producción. Esta tarea se llama *build* y realiza las siguientes acciones:

 * Ajusta rutas de recursos para correr dentro de un contexto distinto
 * Limpia la carpeta /app/dist de destino
 * Ejecuta validadores de JS y de CSS
 * Comprime y minimifica el código JS, juntándolo todo en un solo archivo
 * Comprime y minimifica el código CSS, juntándolo todo en un solo archivo
 * Corre el template engine Swig por el cual se agregan las dependencias al HTML index
 * Copia todos los distribuibles a la carpeta /app/dist de destino
 * Borra archivos temporales
 
Todo lo que queda en la carpeta /app/dist de destino está listo para ser hosteado desde un servidor de contenido productivo.
 

