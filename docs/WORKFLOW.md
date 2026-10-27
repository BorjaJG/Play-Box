# Flujo de desarrollo

El diagrama principal está en el README y GitHub lo representa con Mermaid.

## Automatización

- `Android CI` se ejecuta en pushes a `main`, pull requests a `main` y manualmente.
- Valida el Gradle Wrapper, compila debug, ejecuta tests unitarios y Android Lint, y compila las pruebas instrumentadas.
- Conserva informes durante 7 días. En `main` y ejecuciones manuales correctas guarda también la APK debug para pruebas.
- No firma releases, no publica en tiendas y no ejecuta tests instrumentados en emulador. La prueba de persistencia se puede ejecutar manualmente con `./gradlew :app:connectedDebugAndroidTest` cuando haya un dispositivo o emulador.
- Dependabot propone actualizaciones semanales de Gradle y GitHub Actions. Revisar compatibilidad AGP/Gradle/SDK antes de integrarlas.

## Revisión e integración

Ramas cortas desde `main`, una tarea por PR y squash merge cuando los checks estén correctos. Para uso individual, revisar el diff y la evidencia antes de integrar.

**Pendiente de configurar en GitHub Settings:** una regla de protección para `main` que exija PR y el check `Build, tests and lint`, sin aprobación obligatoria de otra persona para no bloquear el trabajo individual. Los archivos de este repositorio no activan esa protección por sí solos.

## Material para LinkedIn

Guardar un vídeo breve o antes/después de un avance probado. Explicar problema, decisión, resultado y siguiente paso. El contenido debe distinguir el Android implementado del macOS futuro. No publicar builds o funciones como terminadas sin haberlas probado.

## Referencias de configuración

- [Gradle Actions: setup y validación del Wrapper](https://github.com/gradle/actions/blob/main/docs/setup-gradle.md)
- [Compatibilidad Android Gradle Plugin 9.4](https://developer.android.com/build/releases/agp-9-4-0-release-notes)
