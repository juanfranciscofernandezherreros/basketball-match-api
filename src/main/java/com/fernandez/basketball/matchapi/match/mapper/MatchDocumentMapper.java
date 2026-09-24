package com.fernandez.basketball.matchapi.match.mapper;

import com.fernandez.basketball.matchapi.match.document.MatchDocument;
import com.fernandez.basketball.matchapi.match.document.PointByPointQuarterDocument;
import com.fernandez.basketball.matchapi.match.model.MatchModels;

public final class MatchDocumentMapper {

    private MatchDocumentMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static MatchModels.Match toModel(MatchDocument document) {
        if (document == null) {
            return null;
        }

        MatchModels.Match model = new MatchModels.Match(
                document.matchId(),
                document.fixture(),
                document.result(),
                document.summary(),
                document.players(),
                document.teamStats(),
                document.pointByPointEvents(),
                document.availableSections(),
                document.projectedAt());

        return model;
    }

    public static MatchModels.PointByPointQuarter toModel(PointByPointQuarterDocument document) {
        if (document == null) {
            return null;
        }

        MatchModels.PointByPointQuarter model = new MatchModels.PointByPointQuarter(
                document.matchId(), document.quarter(), document.events(), document.projectedAt());

        return model;
    }
}
