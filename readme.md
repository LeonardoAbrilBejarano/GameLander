La aplicaci�n contiene un login/registro en el que el usuario necesita una cuenta para jugar,
ese login/registro tiene sus respectivos servlets para el tratamiento de datos, asi como su 
respuesta true/false. Al realizar un login correcto entraremos en el menu, en el que habr� 
cinco opciones:historial de partidas, mirar� el historial de las partidas del usuario logeado;
top 10, mirar� el top 10 de las partidas con mas puntuaci�n; usuarios conectados, se mirar� la 
�ltima partida jugada de cada jugador; jugar en el que se crear� la partida; cerrar sesi�n para
cambiar de sesi�n. Todos estas opciones tienen sus respectivos servlets para su organizaci�n y 
mantenimiento.

En torno al c�digo de la aplicaci�n, se ha realizado con un �nico jsp y varios servlets respecto a
las opciones que realiza la aplicaci�n. Todo ello se ha podido realizar gracias a las consultas jquery 
a los servlets mediante javascript.Seg�n las acciones realizadas las ventanas se cambian gracias a funciones
de javascript que modifican el css de la pagina.

En torno a las cookies, la aplicaci�n utiliza en cada acci�n un autentificador de cookies, que consiste en que
cada acci�n que realize ser� autentificado antes, y en caso erroneo de autentificaci�n, se limpiar� las cookies 
y quedar� la aplicaci�n inservible hasta que no realize una actualizaci�n. Para esta funci�n de autentificaci�n,
es muy importante acceder a las cookies mediante los nombres que hayamos definido a nuestras cookies creadas, ya
que si accedemos a las cookies mediante su array natural, no estamos contemplando la posibilidad que, dependiendo
que servidor o navegador, el numero y el orden de las cookies que vayamos a tratar y como consecuencia posibles 
fallos dificiles de detectar ya que, en este caso concreto, este fallo equivale a que las cookies son erroneas y
por lo tanto queda inservible la aplicaci�n, pero no lanza alg�n tipo de error indicativo.

Por �ltimo, cabe decir que la generaci�n de tablas se hace en el persistante,pero originariamente
utilizaba el contextlistener aunque en el servidor local el codigo del contextlistener si que funcionaba,
pero al realizarlo al servidor del instituto hacia caer el servidor por alg�n motivo desconocido.