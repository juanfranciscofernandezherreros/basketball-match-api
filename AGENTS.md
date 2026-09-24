# Repository agent rules

This repository follows the canonical rules from:
https://github.com/juanfranciscofernandezherreros/springboot-agent-rules

For this service, MongoDB is an explicit project override because it reads the read model produced by `basketball-match-projector`.

Key local contract:
- Java 21.
- Spring Boot 4.x.
- Maven Wrapper.
- Layered feature architecture.
- Read-only HTTP API.
- MongoDB collections `matches` and `match_point_by_point`.
- JaCoCo line coverage >= 80% for application logic.
- Default branch receives Java changes only after required CI compile and coverage jobs pass.
