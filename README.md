# MoviesBoss

MoviesBoss es una aplicación desarrollada por Jose Julián Bustos Díaz y Antonio Parralejo Pablo que proporciona información sobre cualquier película documentada en [IMDB][link1].




### Instalación
En primer lugar debes de tener activada en tu dispositivo Android la opción de instalar aplicaciones de fuentes desconocidas. Esta configuración puede ser modificada una vez instalada la aplicación.

*  Ve a Ajustes
* Pulsa en Seguridad
* Activa la opción Orígenes desconocidos

A continuación instalaremos la aplicación


* Dirígete a MoviesBoss/app 
* Copia el archivo MoviesBoss.apk en tu dispositivo
* Dirígete a la carpeta donde hayas guardado el archivo
* Haz click en el archivo y acepta la instalación
### API OMDb
Puesto que IMDB no tiene una API publicada, hemos usado la que se proporciona en [OMDb] [link2]. Según lo que vimos en clase, decidimos usar el formato json para obtener la información en lugar de xml.
### Funcionamiento

La aplicación usa una API que mediante una petición http, esta devuelve en formato json la información requerida.

Para que se encuentre la película deseada, se debe de introducir el titulo original de la pelicula en inglés. Si no se encuentra la película se redirige al usuario a la vista inicial.

```sh
String url = "http://www.omdbapi.com/?t="+pelicula+"&y=&plot=short&r=json";
```
Después, cogemos de la consulta los valores que nos interesa para crear la vista de la película.
```sh
 String title = jsonObj.getString(TAG_TITLE);
```


### Librería Picasso

Tuvimos problemas para una vez recibida la url de la imagen de la película, poner dicha imagen en la aplicación. Mediante la libreria [Picasso] [link3] conseguimos solucionarlo de una forma sencilla. 
```sh
 Picasso.with(getApplicationContext()).load(foto_global).into(iv);
```




**¡ Disfrutad !**

[link1]: <http://www.imdb.com/>
[link2]: <http://www.omdbapi.com/>
[link3]: <http://square.github.io/picasso/>



