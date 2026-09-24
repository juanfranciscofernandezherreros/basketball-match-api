package com.fernandez.basketball.matchapi.match.document;

import com.fernandez.basketball.matchapi.match.model.MatchModels;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("matches")
public record MatchDocument(
        @Id String id,
        String matchId,
        MatchModels.Fixture fixture,
        MatchModels.Result result,
        MatchModels.Summary summary,
        List<MatchModels.TeamPlayers> players,
        List<MatchModels.TeamStat> teamStats,
        int pointByPointEvents,
        Set<String> availableSections,
        Instant projectedAt) {}
