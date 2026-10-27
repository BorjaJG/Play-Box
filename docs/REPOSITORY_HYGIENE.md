# Qué subir a GitHub

## Incluir

Código, recursos de la app, tests, configuración compartida de Gradle, catálogo de versiones, Gradle Wrapper completo (incluido su JAR), workflows y documentación pública de uso y arquitectura.

## Excluir

- `local.properties`: rutas del SDK en este ordenador.
- `.idea/`, `.gradle/`, `.kotlin/`, `build/`: configuración del editor, cachés y compilación.
- APK/AAB y capturas generadas: usar artefactos de CI; seleccionar manualmente solo material público para documentación.
- Claves y contraseñas: `.env`, `*.jks`, `*.keystore`, `key.properties`, `keystore.properties`, `secrets.properties`, certificados y claves privadas.
- Documentos privados: guardarlos en `private/`, `docs/private/`, `local-notes/` o `exports/`, carpetas ignoradas.

No se ignoran todos los PDF, Word o Markdown: una guía pública sí puede formar parte del repositorio. Revisar el contenido antes de incluirla.

## Antes de un commit

```sh
git status --short
git diff --cached
```

`.gitignore` evita añadir archivos nuevos coincidentes; no elimina archivos ya versionados ni detecta secretos incrustados en código. Si se publica una credencial, revocarla y resolver su historial; no basta con ignorarla después.
