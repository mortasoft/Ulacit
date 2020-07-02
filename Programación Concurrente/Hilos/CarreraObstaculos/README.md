# Proyecto Carrera de Obstaculos
La pista va a tener 5 lineas para poder correr. Cada linea de carrera va a estar representada por un arreglo de 10 posiciones.

En cada una de las lineas vamos a tener un equipo  de competidores conformado por 6 competidores, cada uno de los competidores debe ser un hilo.
Cada uno de los competidores debe moverse por el arreglo un espacio a la vez. En el momento que pasa a un nuevo espacio debe dormir por unos 1000 milisegundos.

Cuando uno de los competidores llega al final de la linea puede salir el siguiente competidor, cuando llegan todos los compeditores de un equipo termina la carrera.

El equipo con más competidores al final gana la carrera.

Dentro del sistema vamos a tener un hilo que pone obstaculos en la carrera. Cada uno de los obstáculos va a deteener al competidor por un tiempo, el hilo debe poner los obstaculos de manera aleatoria en cualquier posición o linea de la carrera. 

El hilo que pone los obstaculos debe poner obstaculos cada 2000 milisegundos.

Los obstaculos que puede poner el hilo son los siguientes:

- Primer obstaculo puedo retrasar al competidor 0 milisegundos
- Segundo obstaculo puedo retrasar al competidor 100 milisegundos
- Tercer obstaculo puedo retrasar al competidor 300 milisegundos
- Cuarto obstaculo puedo retrasar al competidor 1000 milisegundos

# Pendiente
- [ ] Hilo que pone obstaculos en la carrera
