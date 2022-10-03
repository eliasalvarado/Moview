# Moview
 Aplicación con una gran colección de películas y series, y sus distintas reseñas.
 ![This is an image](https://github.com/eliasalvarado/Moview/blob/main/Moview%20(3).png)

 MoView le brinda al usuario una colección de películas y series en donde se despliega la información de dicha película o serie, podrá comentar y agregarla a su lista de favoritos. Esta aplicación buscará reducir el número de esos cinéfilos y seriéfilos que al final de la cinta quedan decepcionados al percatarse que la película o serie no era lo que esperaban y consideran que fue una pérdida de tiempo.

 ## **Features**
- *Puntaje:*
> Se podrá puntuar desde varios perfiles una misma película en el orden de "Me gusta" o "No me gusta", y se mostrará el porcentaje de usuarios que les ha gustado la película en cuestión.
- *Información y filtro:*
> Se podrán filtrar las películas, según su género, año de publicación, puntaje, etc. Además, si el usuario selecciona una película en concreto, se le mostará toda la información relacionada a esta.
- *Perfiles de críticos:*
> Los perfiles de críticos conocidos serán identificados con un *Check* luego de su nombre.

## **Servicios**
 **APIs a utilizar:**
- *WATCHMODE API*
> Esta API será la que estará alimentando a la aplicación Moview con la información necesaria para que el usuario tenga 
> toda la información que necesita de las distintas películas y series que consulte. Proveerá a la aplicación con el nombre, año de estreno, reparto,
> imágenes, entre otros detalles requeridos para proporcionar al usuario la mejor especificación del título seleccionado.
- *Base de datos de usuarios*
> Cuando un usuario quiera loggearse o registrar una cuenta nueva, se estará consultado una base de datos local la cuál contendrá los usuarios que se han creado hasta el momento.
## **Librerías**
* _Navigation_: Servirá para generar una navegación segura y correcta dentro de la aplicación. 
* _Coil_: La utilizaremos para cargar las imágenes dentro de nuestra aplicación. 
* _Retrofit_: Con ella realizaremos las llamadas a la API. 
* _Datastore_: Se utilizará para guardar información en el teléfono, ya sean usuarios creados, opiniones nuevas, etc.
* _Okhttp3_: Se usará para realizar la conexión a internet y poder consumir el API.
* _Coroutines_: Junto con Datastore, se utilizará para la persistencia de información en cuanto la aplicación se cierre, o se guarde en caché en tiempo de ejecución.
