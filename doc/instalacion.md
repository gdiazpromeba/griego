#Instalación

##Configuración
La clase **Configuracion**, al comenzar el programa, necesita leer propiedades de un archivo __kalos.properties__ . 

Este archivo debe estar al mismo nivel (en el mismo directorio), que el jar principal de la aplicación. 

Como las propiedaes usadas para Windows y para Mac son distintas, prefiero conservar 2 archivitos separados, llamados /setup/kalos.properties-win y /setup/kalos.properties-mac, los cuales renombro como "kalos.prperties" y copio en el lugar adecuado en el momento de construir el setup.


##Configuración de la base de datos Mckoi
La base de datos Mckoi tiene 3 componentes:  
 
*  el jar con las bibliotecas de la base de datos 
*  un archivito db.conf, que es binario 
*  un directorio llamado _data_ que contiene los archivos de tablas y otros objetos de la base de datos

El directorio _data_  tiene que estar al mismo nivel (en el mismo directorio) que el archivito _db.conf_, a menos que se especifique lo contrario en una propiedad de conexión, cuando se llama a la base de datos. Cuando el directorio _data_ se especifica, se le puede dar otro nombre, por ejemplo.

```
dataSource.addConnectionProperty("database_path", "///Applications/kalos-4.16.app/Contents/Java/dataDistinto");
```  

###La propiedad _url_ de la base de datos Mckoi
La URL que se indica como propiedad de conexión para Mckoi, puede ser "estilo embebido" o "estilo servidor". La que nos preocupa para la instalación, es la de "estilo embebido", y tiene el siguiente formato:

```
database_url=jdbc\:mckoi\:local\:[el path completo, archivo incluido, de db.conf]
```
por ejemplo:

```
database_url=jdbc\:mckoi\:local\:///Applications/kalos-4.16.app/Contents/Java/db.conf
```

Cuando el path comienza con tres barras, ///, es un path absoluto. 
Cuando comienza con sólo dos barras, lo que se especifica luego es un path relativo, por ejemplo:

```
database_url=jdbc\:mckoi\:local\://./db.conf
```
Este path relativo (y esto es poco intuitivo), no es un path relativo respecto al jar principal de la aplicación, ni del jar común, sino relativo __al directorio de ejecución de donde se ejecutó java para llamar al jar.__

Esto es una complicación, porque en Mac, por ejemplo, el directorio de ejecución para las aplicaciones .app, es _el directorio de usuario_  (por ejemplo _/Users/gdiaz_, el cual no tiene nada que ver con el directrio en donde la aplicación y los jars están instalados.

Por eso, para Mac, al menos, prefiero poner una dirección absoluta. De lo contrario, la propiedad debería ir para atrás varios directorios, y luego para adelante nuevamente.



##Instrucciones para el setup en Mac

* crear un directorio vacío “work” , y dentro de él, otro directorio vacío “lib”
* ejecutar una vez “runBuild”. Debería andar. Pararlo
* ejecutar una vez “runBuildOfuscado”. Debería andar. Pararlo.
* ejecutar una vez “bundleMac”. El resultado queda en el subdirectorio “bundleMac” bajo “work”

##Instrucciones para el setup en Windows
* crear un directorio vacío “work” , y dentro de él, otro directorio vacío “lib”
* ejecutar una vez “runBuild”. Debería andar. Pararlo
* ejecutar una vez “runBuildOfuscado”. Debería andar. Pararlo.
* ejecutar una vez “bundleWin”. El resultado queda en el subdirectorio “bundleWin” bajo “work”








    

