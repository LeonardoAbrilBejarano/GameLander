# GameLander

Una aplicación web de un juego y la realización de la capa de acceso a datos.

La aplicación contiene un login/registro en el que el usuario necesita una cuenta para jugar,
ese login/registro tiene sus respectivos servlets para el tratamiento de datos, asi como su 
respuesta true/false. Al realizar un login correcto entraremos en el menu, en el que habrá 
cinco opciones:historial de partidas, mirará el historial de las partidas del usuario logeado;
top 10, mirará el top 10 de las partidas con mas puntuación; usuarios conectados, se mirará la 
última partida jugada de cada jugador; jugar en el que se creará la partida; cerrar sesión para
cambiar de sesión. Todos estas opciones tienen sus respectivos servlets para su organización y 
mantenimiento.

En torno al código de la aplicación, se ha realizado con un único jsp y varios servlets respecto a
las opciones que realiza la aplicación. Todo ello se ha podido realizar gracias a las consultas jquery 
a los servlets mediante javascript.Según las acciones realizadas las ventanas se cambian gracias a funciones
de javascript que modifican el css de la pagina.

En torno a las cookies, la aplicación utiliza en cada acción un autentificador de cookies, que consiste en que
cada acción que realize será autentificado antes, y en caso erroneo de autentificación, se limpiará las cookies 
y quedará la aplicación inservible hasta que no realize una actualización. Para esta función de autentificación,
es muy importante acceder a las cookies mediante los nombres que hayamos definido a nuestras cookies creadas, ya
que si accedemos a las cookies mediante su array natural, no estamos contemplando la posibilidad que, dependiendo
que servidor o navegador, el numero y el orden de las cookies que vayamos a tratar y como consecuencia posibles 
fallos dificiles de detectar ya que, en este caso concreto, este fallo equivale a que las cookies son erroneas y
por lo tanto queda inservible la aplicación, pero no lanza algún tipo de error indicativo.

Por último, cabe decir que la generación de tablas se hace en el persistante,pero originariamente
utilizaba el contextlistener aunque en el servidor local el codigo del contextlistener si que funcionaba,
pero al realizarlo al servidor del instituto hacia caer el servidor por algún motivo desconocido.
