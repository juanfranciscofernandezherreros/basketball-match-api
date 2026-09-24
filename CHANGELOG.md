# Changelog

## 1.0.0 - 2026-09-24

- Crea la API read-only de partidos sobre MongoDB.
- Lee la colección `matches` generada por `basketball-match-projector`.
- Lee `match_point_by_point` de forma separada para no inflar la respuesta principal.
- Expone endpoints de partido, estadísticas de equipo, jugadores y point-by-point.
- Añade arquitectura por capas, error global 404, Maven Wrapper, Spotless y JaCoCo >= 80%.
- Añade CI con jobs independientes de compilación y cobertura.
- Añade Dockerfile y Docker Compose con MongoDB.
