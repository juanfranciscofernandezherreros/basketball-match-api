# Changelog

## 1.2.1 - 2026-09-25

- [patch] Añade eliminación automática de la rama origen después de mergear una Pull Request en `main`.

## 1.2.0 - 2026-09-24

- Amplía `GET /api/v1/matches/{matchId}` para devolver toda la información del partido en una sola llamada.
- Incluye fixture, resultado, summary, jugadores, team stats y todo el point-by-point agrupado por cuarto.
- Mantiene el listado paginado ligero, sin incluir point-by-point en cada fila.

## 1.1.0 - 2026-09-24

- Añade `GET /api/v1/matches` para listar todos los partidos de forma paginada.
- Soporta parámetros estándar `page`, `size` y `sort`.
- Usa `projectedAt DESC` como orden por defecto.
- Devuelve metadatos de página y el documento completo de cada partido sin incluir el point-by-point.
- Corrige el uso de tipos explícitos en la implementación del servicio según las reglas Java del repositorio.

## 1.0.0 - 2026-09-24

- Crea la API read-only de partidos sobre MongoDB.
- Lee la colección `matches` generada por `basketball-match-projector`.
- Lee `match_point_by_point` de forma separada para no inflar la respuesta principal.
- Expone endpoints de partido, estadísticas de equipo, jugadores y point-by-point.
- Añade arquitectura por capas, error global 404, Maven Wrapper, Spotless y JaCoCo >= 80%.
- Añade CI con jobs independientes de compilación y cobertura.
- Añade Dockerfile y Docker Compose con MongoDB.
