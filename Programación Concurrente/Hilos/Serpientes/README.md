# Proyecto Serpientes
Debemos crear un juego donde vamos a tener una matriz de tamaño n. En la matriz vamos a tener n cantidad de serpientes
(minimo 4 serpientes) que cada una debe ser un hilo. También en la matriz vamos a tener semillas que se van a posicionar en toda
la matriz.

Cada serpiente es un hilo que se va a estar moviendo por toda la matriz. Cuando la serpiente encuentra una semilla se la va a comer y
se debe quitar de la matriz. Cuando una serpiente se cambia de posiciones debe esperar 10 segundos antes de pasar a la siguiente
posición. Pero si se come una semilla debe esperar 10 segundos adicionales.

Debemos tener un hilo que de manera aleatorio posicione semillas en la matriz. Cada 10 segundos el hilo va a posicionar 3 semillas
en la matriz.

Ahora debemos tener otro hilo que le pida posiciones al usuario dentro de la matriz donde crea que este una semilla. Cada vez que
el usuario pasa de posición debe esperar 5 segundo cuando encuentra una semilla la debe de tomar y esperar S segundos más.

El juego termina cuando una serpiente cae en la misma posición del usuario.

Se debe desplegar cuantas semillas se agregaron a la matriz. Cuantas semillas se comieron las serpientes y cuantas semillas
encontró el usuario.
