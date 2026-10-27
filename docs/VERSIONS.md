# Versiones y actualizaciones

Revisión: 27 de octubre de 2026. `gradle/libs.versions.toml` centraliza las dependencias, plugins, SDK de Android y versión de la app. Se usan versiones exactas, sin `+`, snapshots ni prereleases.

| Componente | Versión | Decisión |
| --- | --- | --- |
| AGP / Gradle Wrapper | 9.4.1 / 9.6.0 | Mantener la combinación comprobada de la línea AGP 9.4 |
| Java del daemon y CI | 25 | Configurado en `gradle-daemon-jvm.properties` y workflow |
| Kotlin / compilador Compose | 2.4.21 | Una única versión Kotlin en el catálogo |
| KSP | 2.3.12 | Procesador para Room |
| Compose BOM | 2026.09.00 | Último BOM estable publicado al revisar el Maven de Google |
| Koin BOM | 4.2.2 | Alinea las librerías Koin |
| Room / SQLite Bundled | 3.0.3 / 2.7.1 | Persistencia con driver incluido |
| Activity Compose | 1.13.0 | Actualizado desde 1.11.0 |
| Lifecycle | 2.11.0 | Runtime y ViewModel alineados; antes 2.9.4 |
| Coroutines | 1.11.0 | Android y tests alineados; antes 1.10.2 |
| AndroidX JUnit / Espresso | 1.3.0 / 3.7.0 | Herramientas instrumentadas actuales |
| JUnit 4 | 4.13.2 | Mantener API usada por los tests existentes |
| Android SDK mínimo / compilación / destino | 24 / 37 / 37 | Centralizados en catálogo |
| App versionCode / versionName | 1 / 1.0 | Valores existentes; esta actualización no publica una release |

Los scripts Gradle resuelven el SDK 37 como paquete `platforms;android-37.0`; la CI instala ese identificador. Java 25 ejecuta Gradle; la compatibilidad Java del código sigue siendo 11.

## GitHub Actions

Checkout 7.0.1, setup-java 6.1.0 y upload-artifact 7.0.2. Gradle Actions y setup-android se mantienen en sus líneas 6 y 4. Dependabot revisa Gradle y GitHub Actions semanalmente; propone PRs y no las integra automáticamente.

## Procedimiento

1. Revisar notas de versión y compatibilidad AGP/Gradle/Kotlin/KSP antes de editar el catálogo. Actualizar el checksum si cambia el Wrapper, usando el checksum oficial.
2. Mantener alineados ambos BOM, el compilador Compose con Kotlin, y runtime/test de coroutines.
3. Ejecutar compilación debug, tests unitarios, lint y compilación de pruebas instrumentadas.
4. Comprobar la CI y probar en dispositivo los cambios de UI, lifecycle o persistencia antes de publicar.
5. Al distribuir una actualización Android, incrementar `appVersionCode` y elegir `appVersionName` de forma explícita. Subir dependencias no debe publicar ni etiquetar una release automáticamente.

## Referencias

- [Compatibilidad AGP 9.4](https://developer.android.com/build/releases/agp-9-4-0-release-notes)
- [Kotlin](https://kotlinlang.org/docs/releases.html)
- [Activity](https://developer.android.com/jetpack/androidx/releases/activity)
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [AndroidX Test](https://developer.android.com/jetpack/androidx/releases/test)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines/releases)
- [BOM Compose](https://developer.android.com/develop/ui/compose/bom/bom-mapping)
- [Koin](https://insert-koin.io/docs/setup/)
