# Repository agent rules

## Mandatory pre-flight

The first repository read operation in every task or session MUST be reading this `AGENTS.md` from the default branch. Any referenced canonical rules MUST then be read before the first write.

Reading these rules in a previous chat, task, or session does not count. Do not rely on memory.

**No repository write is allowed before this pre-flight is complete.**

## Autonomous execution

After the pre-flight, the agent should continue autonomously. It must choose a descriptive branch, determine the appropriate SemVer level from the actual impact, and document those choices in the Pull Request. It must not stop for branch/SemVer/commit/push/test/merge confirmations unless the user explicitly asks to participate in those decisions.

## Absolute ban on direct writes to the default branch

**No file may be modified, committed, or pushed directly to the default branch.**

This applies to Java, documentation, configuration, workflows, dependencies, versioning, badges, hotfixes, reverts, and every other repository change.

Every change must follow this workflow:

1. Read `AGENTS.md` and all referenced rules.
2. Start from the updated default branch.
3. Create a dedicated branch before modifying any file.
4. Apply the appropriate SemVer change.
5. Make all changes only on that branch.
6. Update `CHANGELOG.md`, `README.md`, and version metadata when applicable.
7. Run the applicable checks, including the Maven Wrapper test/verify workflow required by this repository.
8. Open or update a Pull Request to the default branch.
9. Check required CI on the current PR SHA.
10. Fix failures in the same branch/PR and re-run checks automatically.
11. When all required/applicable checks are green on the current SHA and no GitHub protection blocks the merge, automatically merge the Pull Request without asking for additional authorization.
12. Delete only the PR source branch after merge and verify that it no longer exists.

Work is not complete until the Pull Request is merged and source-branch cleanup has been verified.

## Canonical rules

This repository follows the canonical rules from:
https://github.com/juanfranciscofernandezherreros/springboot-agent-rules

The local rules in this file are mandatory and add the pre-flight/autonomy/default-branch protections above.

## Project-specific contract

MongoDB is an explicit project override because this service reads the read model produced by `basketball-match-projector`.

- Java 21.
- Spring Boot 4.x.
- Maven Wrapper.
- Layered feature architecture.
- Read-only HTTP API.
- MongoDB collections `matches` and `match_point_by_point`.
- JaCoCo line coverage >= 80% for application logic.
- Default branch receives changes only through Pull Requests after required CI compile and coverage jobs pass.

## Operational safety

Every merge decision must use the current PR SHA. If a user instruction conflicts with these rules, stop only the incompatible operation; never improvise a direct write to the default branch.
