# 🎮 Proyecto GameMaker: Modificación de GameLluvia 🚀

Este proyecto consiste en la modificación del videojuego **GameLluvia** para crear una experiencia mejorada y adaptada a nuevas funcionalidades. Fue desarrollado como parte del curso de Programación Avanzada en la **Pontificia Universidad Católica de Valparaíso**, utilizando el framework **LibGDX**.

## 🌟 Descripción General

En **GameLluvia**, el jugador controla un personaje con el objetivo de recolectar puntos a través de diversos elementos beneficiosos mientras evita peligros. La mecánica de juego ha sido optimizada y ampliada respecto a su versión base, incluyendo mejoras en la jugabilidad y la estructura del código.

## ✨ Funcionalidades Principales

- **🎮 Control del personaje:** Maneja al protagonista y recolecta ítems usando teclas direccionales.
- **🍪 Recolección de objetos:**
  - **Galletas:** Otorgan puntos.
  - **Botiquines:** Restauran vidas o, si ya están completas, otorgan puntos adicionales.
  - **Bombas:** Hacen perder una vida al jugador.
- **💖 Sistema de vidas:** El jugador comienza con tres vidas; puede recuperarlas o ganar puntos adicionales mediante botiquines.
- **📈 Dificultad progresiva:** Los ítems aparecen con mayor frecuencia y velocidad conforme avanza el juego.
- **🖥️ Pantallas del juego:**
  - **Menú principal**: Navegación inicial.
  - **Pantalla de juego**: Acción principal.
  - **Pantalla de pausa**: Para detener temporalmente la partida.
  - **Game Over**: Mostrando el puntaje final.

## 🛠️ Mejoras Técnicas

- **🎨 Fondos personalizados:** Pantallas diseñadas con elementos visuales únicos para cada estado del juego.
- **🌀 Movilidad avanzada:** Desplazamiento continuo del personaje entre los bordes de la pantalla.
- **📋 Refactorización del código:**
  - Implementación del patrón **Modelo-Vista-Controlador (MVC)**.
  - Uso de patrones de diseño como **Singleton**, **Template Method**, **Strategy** y **Abstract Factory** para optimizar la estructura y escalabilidad del juego.
  - Modularización y encapsulamiento de atributos clave, como vidas y puntos.

## 💡 Diseño Orientado a Objetos

El juego ha sido desarrollado respetando los principios de Programación Orientada a Objetos (POO), destacando:

- **Encapsulamiento:** Gestión controlada de atributos a través de getters y setters.
- **Abstracción y Polimorfismo:** Uso de clases abstractas e interfaces para garantizar un comportamiento extensible y modular.
- **Principio de Responsabilidad Única (SRP):** Cada clase se encarga de una única tarea específica.
- **Principio Abierto/Cerrado (OCP):** El sistema permite incorporar nuevas funcionalidades sin modificar el código existente.

## 📜 Patrones de Diseño Aplicados

1. **Singleton:** Centraliza configuraciones globales como dificultad, puntuación máxima y estado del audio.
2. **Template Method:** Centraliza la lógica compartida de los objetos que caen, delegando detalles específicos a las subclases.
3. **Strategy:** Separa los patrones de movimiento, permitiendo intercambiarlos dinámicamente.
4. **Abstract Factory:** Gestiona la creación desacoplada de diferentes tipos de botiquines.

## 🔧 Requisitos del Sistema

- **Java 8** o superior.
- **LibGDX Framework.**
- Entorno de desarrollo recomendado: **IntelliJ IDEA.**

## 🕹️ Instrucciones de Ejecución

1. **Clonar el repositorio:**  
   `git clone <url-del-repositorio>`
2. **Configurar LibGDX:**  
   Sigue las instrucciones en [LibGDX Setup](https://libgdx.com/wiki/start/setup).
3. **Compilar y ejecutar:**  
   Ejecuta desde la clase `Lwjgl3Launcher` ubicada en:  
   `GameLluviaMenu2024/lwjgl3/src/main/java/puppy/code/lwjgl3/Lwjgl3Launcher.java`.

---

## 🏫 Universidad y Créditos

**Pontificia Universidad Católica de Valparaíso**  
**Facultad de Ingeniería - Escuela de Ingeniería Informática**  

### 👨‍💻 Integrantes del Proyecto:

- Matías Pardo  
- Juan Pablo Pizarro  
- Joaquín Saldivia  

---

🎉 ¡Gracias por leer y esperamos que disfrutes el juego! 🚀
