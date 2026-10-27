# Play Box: contexto para Copilot

- Es un proyecto personal de escaparate. Priorizar una mecánica pequeña, usable y probada.
- Actualmente solo existe el proyecto Android. macOS es un objetivo futuro; documentar una decisión antes de migrar o añadir frameworks.
- Mantener las versiones del catálogo Gradle y usar el Wrapper existente. El daemon requiere Java 25.
- Cambios pequeños asociados a un issue, con criterios de aceptación. Completar la plantilla de PR.
- Ejecutar `./gradlew :app:assembleDebug :app:testDebugUnitTest :app:lintDebug` cuando se cambie código Android.
- Añadir pruebas que verifiquen comportamientos relevantes; no presentar los tests de ejemplo como cobertura funcional.
- No incluir claves, rutas locales, builds ni documentos privados. Seguir `docs/REPOSITORY_HYGIENE.md`.
- Actualizar la documentación cuando cambie el comportamiento o estado real de una plataforma.
- No publicar en tiendas, firmar releases ni cambiar permisos del repositorio como parte de cambios rutinarios.

## Arquitectura

- Seguir `docs/ARCHITECTURE.md`: `core` para infraestructura/tema/metadatos compartidos, `feature/<juego>/{data,domain,presentation}` para lo específico.
- `domain` debe ser Kotlin puro, sin imports de Android, Compose, Room ni implementaciones de `data`.
- Compose representa `UiState`; el ViewModel procesa acciones y publica `StateFlow`. Inyectar casos de uso en los ViewModels; los contratos Repository viven en domain y sus implementaciones en data. Recoger flujos con ciclo de vida.
- Persistencia con Room 3 y SQLite Bundled. Versionar `app/schemas` y definir migraciones al cambiar el esquema.
- Las entidades/DAO viven junto a su juego; la base en `app/data/database` reúne el esquema. `app/di` reúne los módulos Koin; `core` no importa `feature`.
- Usar Koin con módulos por funcionalidad e inyección por constructor; no introducir clases base o capas genéricas vacías.
