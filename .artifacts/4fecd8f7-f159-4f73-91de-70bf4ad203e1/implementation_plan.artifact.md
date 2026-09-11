# Plan: Proyecto SENA - Código Claro y Explicable

El objetivo es que este proyecto sea tu mejor herramienta de estudio. Vamos a organizar todo para que sea fácil de sustentar ante tu profesor.

## User Review Required

> [!TIP]
> **Enfoque Junior**: Usaremos nombres de funciones claros en español/inglés y añadiremos comentarios que expliquen la "magia" de las librerías (Hilt, Retrofit, Compose).
>
> **Guía de Sustentación**: Al finalizar, te entregaré un documento con las preguntas típicas que un profesor suele hacer y cómo responderlas basándote en tu código.

## Proposed Changes

### 1. Documentación Didáctica (Comentarios en el código)
- **Hilt (Inyección de dependencias)**: Explicar que sirve para que las piezas del código se conecten solas.
- **Retrofit (Consumo de API)**: Explicar que es el "puente" hacia internet.
- **Clean Architecture**: Comentar cada capa (`data`, `domain`, `ui`) con su función principal.

### 2. Limpieza de "Ruido"
- Quitar importaciones que no se usan (los textos en gris que a veces sobran).
- Asegurar que los nombres de las variables sean fáciles de leer (ej: `listaDeProductos` en lugar de `pList`).

### 3. Guía de Sustentación (Nuevo Artefacto)
- **[NEW] guia_sustentacion.artifact.md**: Un resumen paso a paso de cómo funciona tu app por dentro.

## Verification Plan
1. **Compilación**: El proyecto debe correr a la primera sin errores.
2. **Claridad**: Leeremos el código juntos para asegurar que cualquier "Junior" pueda entenderlo.
