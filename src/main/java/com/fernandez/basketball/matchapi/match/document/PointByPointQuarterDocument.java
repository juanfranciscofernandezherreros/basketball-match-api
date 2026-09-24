package com.fernandez.basketball.matchapi.match.document;

import com.fernandez.basketball.matchapi.match.model.MatchModels;
import java.time.Instant;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("match_point_by_point")
public record PointByPointQuarterDocument(
        @Id String id,
        @Indexed String matchId,
        String quarter,
        List<MatchModels.PointByPointEvent> events,
        Instant projectedAt) {}
