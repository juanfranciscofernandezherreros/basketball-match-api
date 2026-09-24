# basketball-match-api

Current version: **1.1.0**

API HTTP de solo lectura para la web de partidos de baloncesto.

```text
PostgreSQL
    |
    v
basketball-match-projector
    |
    v
MongoDB
  +-- matches
  +-- match_point_by_point
    |
    v
basketball-match-api
    |
    v
Frontend
```

## Contrato

### USER REQUIREMENT

- El servicio lee MongoDB, no PostgreSQL.
- MongoDB contiene el read model generado por `basketball-match-projector`.
- La API está pensada para ser consumida por el frontend.
- La estructura sigue las reglas de `springboot-agent-rules`, adaptando la capa de persistencia a MongoDB.

### REPOSITORY DEFAULT

- Java 21.
- Spring Boot 4.x.
- Maven Wrapper.
- Arquitectura por capas.
- Controllers sin lógica de negocio ni acceso a repositorios.
- Servicio mediante interfaz + implementación.
- DTOs y mappers explícitos.
- Error global consistente.
- Spotless con Palantir Java Format.
- JaCoCo con mínimo 80% de cobertura de lógica de aplicación.
- CI separado en compilación y verify/cobertura.

### TECHNICAL ASSUMPTION

- La API es read-only.
- No se añade autenticación porque no se ha definido un contrato de seguridad.
- La base path es `/api/v1/matches`.
- Un `matchId` inexistente responde `404 MATCH_NOT_FOUND`.
- Las estadísticas y jugadores se obtienen del documento `matches`.
- El point-by-point se obtiene de `match_point_by_point`, agrupado por cuarto.
- No se usan JPA ni Flyway porque el servicio no es propietario del esquema: consume un read model MongoDB ya construido por el projector.

## Endpoints

| Método | Endpoint | Uso |
|---|---|---|
| GET | `/api/v1/matches?page=0&size=20&sort=projectedAt,desc` | Lista paginada de todos los partidos |
| GET | `/api/v1/matches/{matchId}` | Documento principal del partido |
| GET | `/api/v1/matches/{matchId}/stats` | Estadísticas de equipo por periodo |
| GET | `/api/v1/matches/{matchId}/players` | Jugadores agrupados por equipo |
| GET | `/api/v1/matches/{matchId}/point-by-point` | Eventos agrupados por cuarto |

La respuesta principal contiene fixture, resultado, summary, jugadores, estadísticas de equipo, número de eventos PBP, secciones disponibles y fecha de proyección. No incluye los eventos PBP completos.

### Paginación

`GET /api/v1/matches` devuelve una página de documentos completos de `matches`:

```json
{
  "content": [],
  "page": 0,
  "size": 20,
  "totalElements": 120,
  "totalPages": 6,
  "first": true,
  "last": false
}
```

Parámetros estándar:
- `page`: página base 0.
- `size`: elementos por página.
- `sort`: campo y dirección, por ejemplo `projectedAt,desc`.

El orden por defecto es `projectedAt DESC`. El point-by-point completo sigue fuera de esta lista y se consulta con el endpoint específico para evitar respuestas excesivamente grandes.

## Configuración

```text
MONGODB_URI=mongodb://localhost:27017/basketball
```

## Desarrollo

Formato:

```bash
./mvnw spotless:apply
./mvnw spotless:check
```

Compilación de producción:

```bash
./mvnw -Dmaven.test.skip=true clean package
```

Tests y cobertura:

```bash
./mvnw verify
```

## Docker

```bash
docker compose config
docker compose up -d --build
```

La aplicación expone `/actuator/health`.

## Propiedad de los datos

`basketball-match-api` no modifica MongoDB. El propietario del read model es `basketball-match-projector`; si el documento necesita reconstruirse, debe regenerarse desde PostgreSQL a través del projector.
