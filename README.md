# Proyecto Come Galletas (Juego en LibGDX)

Este proyecto consiste en el desarrollo de un videojuego arcade llamado **"Come Galletas"**. El objetivo del juego es ayudar a un personaje, un perro, a recolectar galletas mientras esquiva bombas y recoge botiquines para mantenerse con vida. Fue desarrollado como parte de la asignatura de Programación Avanzada en la Pontificia Universidad Católica de Valparaíso, utilizando la biblioteca **LibGDX**, y el proyecto base entregado por el Académico Claudio Cubillos.

## Descripción

En **Come Galletas**, el jugador controla un personaje canino usando las teclas de dirección y debe recolectar tantos puntos como sea posible. Los puntos se acumulan al recoger galletas y botiquines. Las bombas, sin embargo, deben evitarse, ya que al tocarlas el jugador pierde una vida. El juego finaliza cuando el jugador pierde todas las vidas.

## Funcionalidades

- **Control del personaje**: Movimiento del personaje (perro) para recolectar objetos y evitar obstáculos.
- **Recolección de ítems**:
  - **Galletas**: Otorgan puntos al jugador.
  - **Botiquines**: Pueden restaurar vidas si el jugador tiene menos de tres, o proporcionar puntos adicionales si tiene el máximo de vidas.
  - **Bombas**: El jugador pierde una vida al tocarlas.
- **Sistema de vidas**: El jugador comienza con tres vidas, y cada colisión con una bomba disminuye una.
- **Incremento de dificultad**: La frecuencia y velocidad de los objetos aumentan a medida que el jugador alcanza ciertos puntajes.
- **Pantallas de juego**:
  - **Menú principal**: Pantalla inicial del juego.
  - **Pantalla de juego**: Donde ocurre la acción principal.
  - **Pantalla de pausa**: Permite al jugador pausar la partida.
  - **Pantalla de Game Over**: Se muestra cuando el jugador pierde todas las vidas.

## Estructura del Proyecto

El proyecto está organizado en varias clases, cada una con una responsabilidad específica:

- **Clase `GameLluviaMenu`**: Clase principal que inicializa el juego y controla el flujo entre pantallas.
- **Clase `Tarro`**: Representa al personaje controlado por el jugador, incluyendo su movimiento y administración de vidas y puntos.
- **Clase `Botiquin` (Abstracta)**: Define los atributos y métodos comunes para los botiquines.
  - **`BotiquinPequeno`** y **`BotiquinGrande`**: Subclases de `Botiquin` que implementan los efectos específicos de cada tipo de botiquín.
- **Clase `Lluvia`**: Controla la generación de objetos como galletas y bombas que caen desde la parte superior de la pantalla.
- **Clases de Pantallas (`Screen`)**: Cada fase del juego tiene una clase dedicada (`MainMenuScreen`, `GameScreen`, `PausaScreen`, `GameOverScreen`), lo que facilita la navegación y la organización del flujo del juego.

## Diseño Conceptual

- **Patrón Modelo-Vista-Controlador (MVC)**: El juego sigue el patrón MVC, separando la lógica de la presentación y las interacciones del jugador.
- **Interfaz `Collectible`**: Define el método `collect()` para los objetos que el jugador puede recolectar. Esto permite añadir nuevos objetos recolectables de manera sencilla.
- **Incremento progresivo de dificultad**: El juego se vuelve más desafiante a medida que se alcanzan ciertos hitos de puntaje, generando una mayor cantidad de obstáculos y aumentando su velocidad.

## Menú Interactivo

El juego se maneja principalmente con un menú gráfico que permite al jugador iniciar una partida, pausar el juego o salir. En cada partida, el jugador debe usar las teclas de dirección para moverse y recolectar ítems o evitar bombas.

## Buenas Prácticas

- **Encapsulamiento**: Los atributos en las clases están encapsulados y accesibles mediante getters y setters para proteger la integridad del juego.
- **Uso de Polimorfismo y Herencia**: Se emplea herencia en la clase abstracta `Botiquin` y sus subclases para implementar los diferentes tipos de botiquines.
- **Control de Versiones**: Se recomienda el uso de control de versiones con commits frecuentes para documentar el desarrollo.

## Requisitos

- **Java 8** o superior
- **LibGDX**: Configurado en el entorno de desarrollo para ejecutar el proyecto
- **Consola**: Para ejecutar los comandos de compilación, si es necesario

## Instrucciones de Ejecución

1. **Clonar el repositorio**: `git clone <url-del-repositorio>`
2. **Navegar al directorio del proyecto**: `cd <nombre-del-directorio>`
3. **Preparar LibGDX**: Por favor guiarse de las instrucciones de instalación explicadas en la siguiente página:
   https://libgdx.com/wiki/start/setup.
5. **Compilar y ejecutar el proyecto**: De preferencia en el entorno de desarrollo IntelliJ IDEA, esto en la direccion "GameLluviaMenu2024\lwjgl3\src\main\java\puppy\code\lwjgl3\Lwjgl3Launcher.java"

## Pontificia Universidad Católica de Valparaíso - Facultad de Ingeniería - Escuela de Ingeniería Informática

**Integrantes del proyecto:**
- Matías Pardo
- Juan Pablo Pizarro
- Joaquín Saldivia 
