# Guía de Sustentación para el Profesor (SENA)

Esta guía te ayudará a explicar tu proyecto paso a paso, usando los conceptos clave que tu profesor espera escuchar.

## 1. El Concepto General
"Profesor, he desarrollado una aplicación de gestión de productos (CRUD) utilizando **Kotlin** y **Jetpack Compose**. La aplicación se conecta a una API externa para obtener datos, pero también permite crear, editar y eliminar productos de forma local."

## 2. La Arquitectura (Clean Architecture)
Explica que dividiste el código en 3 capas para que sea organizado:
- **Capa de Dominio (Domain)**: "Es el corazón de la app. Aquí definí qué hace mi negocio (UseCases) y cómo es un producto (`ProductModel`). No depende de ninguna librería externa."
- **Capa de Datos (Data)**: "Aquí es donde ocurre la conexión a Internet. Uso **Retrofit** para hablar con la API y **Moshi** para traducir el JSON a código Kotlin. También uso un **Mapper** para limpiar los datos antes de que lleguen al resto de la app."
- **Capa de UI (Presentación)**: "Uso **Jetpack Compose** para dibujar las pantallas. El **ViewModel** actúa como el cerebro que controla qué mostrar según el estado de la aplicación."

## 3. Librerías Clave (Las que el profesor preguntará)
- **Hilt**: "Es para inyección de dependencias. Sirve para que las piezas del código se conecten solas sin que yo tenga que crear los objetos manualmente con `new` o constructores largos."
- **Retrofit**: "Es la librería estándar para consumir servicios web (APIs) de forma segura."
- **StateFlow**: "Es como una variable que la pantalla está 'vigilando'. Si el valor cambia, la pantalla se actualiza sola."

## 4. El CRUD y la Persistencia Simulada
"Como la API de prueba (DummyJSON) no guarda los cambios permanentemente, implementé una lógica en el **ViewModel** que mantiene una lista en memoria. Así, si el usuario crea un producto, este no desaparece al refrescar la pantalla mientras la app esté abierta."

## 5. Preguntas Trampa del Profesor
- **P: ¿Por qué usas una interfaz en el Repositorio?**
  - **R**: "Para que mi código sea flexible. Si mañana decido cambiar la API por una Base de Datos local (Room), solo cambio la implementación y el resto de la app no se entera."
- **P: ¿Qué es el DTO y por qué no usas el mismo modelo en toda la app?**
  - **R**: "El DTO es el formato crudo que viene de internet. Lo mapeamos a un `ProductModel` de dominio para que nuestra app sea independiente de los nombres de campos que el servidor decida usar."
