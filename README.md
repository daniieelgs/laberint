# laberint
Es un mini juego que se basa en encontrar la salida de un laberinto con distintos obstaculos como bombas o picos, con generador automatico de laberintos aleatorios y con resolució
automática

## Inicio
1. Para inicar el juego, descomprimar el archivo 'laberint.rar' y acceda a la carpeta 'laberint'.
2. Ahí encontrará tres archivo 'Joc Laberint.bat', 'Joc Laberint.sh' y 'Laberint vX.X.jar'.
> Windows
* Para iniciar el juego en Windows, ejecute 'Joc Laberint.bat' o inicie una terminal y ejecute  `"Joc Laberint.bat"`
> Linux
* Para inicar el juego en Linux, ejecute 'Joc Laberint.sh' o inicie una terminal y ejecute `./Joc\ Laberint.sh`. Si sale un error de 'Permiso denegado' ejecute `chmod +rwx Joc\ Laberint.sh` y vuelva a intentarlo
**Version mínima del JSE: `16.0.2`**

## Quick Usage
* 'P': Posicion del jugador
* 'S': Salida del laberinto
* '#': Muros
* '@': Bombas (Ocultas por defecto). Restan una vida
* '^': Pico, sirve para eliminar un muro

### Playing
> Introduzca los siguientes comandos + Enter para jugar
* 'W': Subir el jugador
* 'D': Mover a la derecha el jugador
* 'S': Bajar el jugador
* 'A': Mover a la izquierda el jugador
* 'info': Muestra el número de vidas y de picos del jugador
* 'reset': Reinicia la partida
* 'solve': Resulve el laberinto automaticamente. Pulse enter para hacer que avance el jugador
> La resolución automática no tiene en cuenta ni las bombas ni los picos, por lo que serán eliminados al ejecutar este comando
* 'exit': Sale del juevo
* '?': Muestra la guia de uso

### Nivel de satisfacción
Al finalizar cada laberinto se le mostrará el nivel de satisfacción en función del número de vidas al llegar al final:
1. ':(' Insatisfecho. Sales con 1 vida
2: ':|' Medio satisfecho. Sales con 2 vidas
3: ':)' Satisfecho. Sales con 3 vidas. Se le mostrará el laberinto con todas las bombas visibles

## Comandos
> Algunos comandos aceptan un valor de entrada, introduzca el comando, pulse enter, introduzca el valor para dicho comando y vuelva a pulsar enter
* 'GOD': Muestra la guia de comandos avanzados
* 'obstacles': Introduzca `true` o `false` para activar o desactivar las bombas y los picos del mapa
* 'bombes': Introduzca `true` o `false` para mostrar u ocultar las bombas en el mapa
* 'random': Introduzca `true` o `false` para generar un mapa aleatorio o volver al mapa por defecto. Introduzca `limit` y en la siguiente linea especifica un valor entre 0-50 para configurar las dimensiones del mapa aleatorio. Valor por defecto '-1', genera un laberinto de dimensiones aleatorias
* 'gui': **BETA** Carga la interfaz gráfica del juego. Haga click derecho sobre el laberinto para ocultar o desplegar el panel lateral.
> Se recomienda ocultar el panel lateral en laberintos generados automaticamente.
> Solamente si cambia el mapa por defecto a un mapa generado automaticamente o viceversa, tendrá que reinicar la interfaz.
> **La interfaz puede ir con cierto delay en laberintos grandes**
* 'debug': Activa o desactiva el modo debug de la GUI
* 'MAKER': Creador del juego

## License
[License](https://github.com/daniieelgs/laberint/blob/master/LICENSE)
