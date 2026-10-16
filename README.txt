╔══════════════════════ COFFEE CLICKER ══════════════════════╗
--
-- DISEÑO Y PROGRAMACIÓN ORIENTADO A OBJETOS
-- SEMESTRE 2 PROYECTO COFFEE CLICKER
-- 29 ABRIL DE 2024
--
-- GRUPO D_Coffee-Clicker-2:
--
--	• Pau Salip Chas
--	• Gabriel Sánchez López
--	• David Cutanda Esteve
--	• Marc Sánchez Cervera
--	• Alvaro Duelo Galán
--
═════════════════════════════ INSTRUCCIONES DE EJECUCIÓN ═════════════════════════════
--
-- En este apartado, detallaremos la ejecución del programa paso por paso.
--
-- El SDK usado ha sido openjdk-21 (Oracle OpenJDK version 22). Recomendamos por tanto
	que se use este mismo.
--
-- 1. Primero debemos de abrir el programa XAMPP y hacer click en el apartado de start
	tanto de Apache como. Una vez inicializados haremos click en admin del modulo
	MySQL. 
--
-- 2. Después, se abrirá una pestaña y se tiene que hacer click en "Nueva" que se encuentra
	en el menú secundario de la izquierda. Se debe introducir el nombre de la base 
	de datos que será coffeeclicker_db, en el campo adyacente se seleccionamos la
	opción de "latin1_spanish_ci". Posteriormente importaremos el archivo 
	.sql. Para hacerlo, vamoas a la barra superior y haremos click en "Importar", 
	luego "Elegir archivo" y adjuntaremos el archivo coffeeclicker_db.sql que 
	se encuentra en la carpeta llamada "Database".Finalmente, le daremos a "Importar".
--
-- 3. Ahora, abrimos el código de Java con nuestro programa de preferiencia, en
	nuestro caso dicho programa será IntelliJ.
--
-- 4. Una vez tengamos el programa abierto, vamos a la clase de "Main" y ejecutamos el programa.
	Si hemos realizado correctamente los pasos se nos tendría que abrir la ventana del 
	programa. Cabe recalcar que es imprescindible tener el XAMPP abierto con los servicios
	de Apache y MySQL activados mientras se ejecuta el programa.
--
═════════════════════════════ INSTRUCCIONES DE USO ══════════════════════════════════
--
-- En este apartado explicaremos con detalle como funciona Cofee Clicker.
--
-- 1. Cuando se ejecute el programa aparecerá una ventana con un mensaje de bienvenida
	y las opciones de logearse o registrarse. Si se entra en la pestaña de login
	se pedirá el nombre de usuario y la contraseña, una vez introducidas estará
	accesible el botón de abajo de "login" para entrar en el juego siempre y cuando 
	las credenciales sean correctas. Tanto el apartado de login como el de register
	tienen un botón en la parte superior izquierda de color rojo que permiten retroceder 
	a la vista anterior y debajo del botón de login/register hay una opción de cambiar
	a la vista opuesta. Es decir del login a register, y viceversa. 
	La opción de register permite crear una cuenta introduciendo el nombre de usuario,
	Email, contraseña y la confirmación de contraseña. Para que se considere válida 
	debe contener tanto caracteres como numeros y al menos una mayúscula.
	Una vez	introducida la información podrás crear una cuenta apretando el botón 
	de "Register".

--  ¡¡En caso que se quiera introducir caracteres especiales, el sistema solamente permite 
	los siguientes caracteres!! 	-> 	@ $ ! % * ? & + \ - ^ ¡ ¿ = # | / 
	Sino saltará error.
--
-- 2. Una vez se haya pasado la etapa de login/register, se puede ver el menú del juego.
	Este tiene las siguientes funciones: New Game para crear una partida nueva, Load
	Game para cargar una partida ya existente, Statistics para ver las estadísticas
	de partidas previas y Account que permite eliminar la cuenta o cerrar sesión.

	* New Game => Sirve para crear una nueva partida, si se tiene una partida creada
	se pedirá finalizar. En caso de no tener partida creada se pedirá introducir 
	un nombre a la partida y empezará el juego.

	* Load Game => Sirve para cargar una partida en curso, si no hay una existente
	el programa le informará.

	* Statistics => Sirve para mostrar las estadísticas de partidas ya finalizadas
	donde se podrá elegir el usuario y los games en concreto. Esto se representará
	por una gráfica tiempo-coffee de cada partida.
	
	* Account => Permite eliminar la cuenta o cerrar sesión.
--
-- 3. En caso de crear una nueva partida, debes introducir el nombre de la partida, 
	aceptar y entonces e abrirá la vista del juego. Esta vista se divide en 
	tres columnas separando las distintas funciones:
	
	* Columna Coffee => Aqui se ven los cafés totales obtenidos y abajo se encuentra
	el botón para comprar manualmente cafés.

	* Generators Table => En esta parte se muestra las estadísticas de los generadores
	siendo estas: la cantiadad, producción, total y global. Se muestra la cantidad por
	segundo y el porcentaje global. Abajo del todo de esta columna se encuentra botones
	para ver tu cuenta, volver al menú y acabar la partida.

	* Columna Shop => Esta columna está dividida en dos, por un lado se encuentra 
	upgrades y por otro generators. Generators sirve para comprar generadores de cafés
	automáticos mediante el botón de buy de al lado y se mostrará el coste de compra y 
	la cantidad que genera en el mismo apartado. Las upgrades multiplican la cantidad 
	de cafés que producen el generador respectivo de la upgrade, para comprar una upgrade
	simplemente haz click encima de ella.  
--
-- 4. Si se decide cargar una partida en curso, en caso que la haya, se abrirá la vista
	del juego con el progreso correspondiente.
--
-- 5. El apartado de estadísticas cuenta con un botón rojo en la esquina superior
	izquierda que te lleva de vuelta al menú y permite seleccionar el usuario y 
	cualquiera de las partidas acabadas del mismo que al ser selecionadas se mostrará
	un gráfico detallando la relación tiempo-numero de cafés. En esta vista 
	se puede acceder al apartado de account mediante el botón del mismo nombre.
--
-- 6. El apartado de account permite cerrar sesión mediante hacer click en el botón de
	logout, también permite borrar la cuenta mediante el botón de delete account y 
	mediante el botón cancel, el usuario puede volver al apartado anterior.
--------------------------------------------------------------------------------------
