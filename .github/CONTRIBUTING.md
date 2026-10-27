# Cómo trabajar en Play Box

1. Abrir una incidencia con la plantilla adecuada y criterios observables de aceptación.
2. Crear una rama desde `main`: `feature/12-nombre`, `fix/12-nombre`, `docs/12-nombre` o `chore/12-nombre`.
3. Resolver un objetivo pequeño por pull request. Para un proyecto personal puedes revisar tu propio cambio antes de integrarlo.
4. Ejecutar `./gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug` y probar manualmente la interacción afectada.
5. Abrir una PR, completar la plantilla y enlazar el issue con `Closes #12` cuando lo resuelva por completo.
6. Corregir los checks y revisar el diff. Integrar con squash merge y eliminar la rama cuando ya no sea necesaria.

Usar commits claros, por ejemplo `feat: añadir selección de juego`, `fix: evitar doble pulsación` o `docs: explicar instalación`.

Nunca incluir credenciales, claves de firma, `local.properties`, cachés, builds ni documentación privada. Consultar `docs/REPOSITORY_HYGIENE.md`.

Los cambios de plataforma necesitan una decisión de arquitectura documentada; Android y macOS no se consideran compatibles solo porque el código compile en un Mac.
