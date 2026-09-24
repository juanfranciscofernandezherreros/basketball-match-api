package com.fernandez.basketball.matchapi.match.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.fernandez.basketball.matchapi.match.document.MatchDocument;
import com.fernandez.basketball.matchapi.match.document.PointByPointQuarterDocument;
import com.fernandez.basketball.matchapi.match.model.MatchModels;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MatchMapperTest {

    @Test
    void maps_complete_match_and_sections_ok() {
        // given
        var match = completeMatch();

        // when
        var response = MatchMapper.toDto(match);

        // then
        assertThat(response.matchId()).isEqualTo("m1");
        assertThat(response.fixture().competition()).isEqualTo("BBL");
        assertThat(response.result().homeScore()).isEqualTo(72);
        assertThat(response.summary().awayName()).isEqualTo("Bayern");
        assertThat(response.players()).hasSize(1);
        assertThat(response.players().getFirst().players().getFirst().pts()).isEqualTo(20);
        assertThat(response.teamStats()).hasSize(1);
    }

    @Test
    void maps_point_by_point_and_null_collections_ok() {
        // given
        var quarter = new MatchModels.PointByPointQuarter(
                "m1",
                "Q1",
                List.of(new MatchModels.PointByPointEvent(
                        "score", "Q1", 1, 0, 2, 0, 2, "away", "2", "away", false, true)),
                Instant.parse("2026-09-24T12:00:00Z"));

        // when
        var pointByPoint = MatchMapper.pointByPointToDto(List.of(quarter));

        // then
        assertThat(pointByPoint).hasSize(1);
        assertThat(pointByPoint.getFirst().events().getFirst().awayScore()).isEqualTo(2);
        assertThat(MatchMapper.statsToDto(null)).isEmpty();
        assertThat(MatchMapper.playersToDto(null)).isEmpty();
        assertThat(MatchMapper.pointByPointToDto(null)).isEmpty();
        assertThat(MatchMapper.toDto(null)).isNull();
    }

    @Test
    void document_mapper_maps_and_accepts_null_ok() {
        // given
        var document = new MatchDocument(
                "m1",
                "m1",
                null,
                null,
                null,
                List.of(),
                List.of(),
                0,
                Set.of(),
                Instant.parse("2026-09-24T12:00:00Z"));
        var quarterDocument = new PointByPointQuarterDocument(
                "m1:Q1",
                "m1",
                "Q1",
                List.of(),
                Instant.parse("2026-09-24T12:00:00Z"));

        // when
        var match = MatchDocumentMapper.toModel(document);
        var quarter = MatchDocumentMapper.toModel(quarterDocument);

        // then
        assertThat(match.matchId()).isEqualTo("m1");
        assertThat(quarter.quarter()).isEqualTo("Q1");
        assertThat(MatchDocumentMapper.toModel((MatchDocument) null)).isNull();
        assertThat(MatchDocumentMapper.toModel((PointByPointQuarterDocument) null)).isNull();
    }

    @Test
    void maps_team_with_null_player_collection_ok() {
        // given
        var teams = List.of(new MatchModels.TeamPlayers("Bayern", null));

        // when
        var response = MatchMapper.playersToDto(teams);

        // then
        assertThat(response.getFirst().players()).isEmpty();
    }

    @Test
    void maps_quarter_with_null_events_ok() {
        // given
        var quarters = List.of(new MatchModels.PointByPointQuarter(
                "m1", "Q1", null, Instant.parse("2026-09-24T12:00:00Z")));

        // when
        var response = MatchMapper.pointByPointToDto(quarters);

        // then
        assertThat(response.getFirst().events()).isEmpty();
    }

    private MatchModels.Match completeMatch() {
        var fixture = new MatchModels.Fixture("Germany", "BBL", "2026-09-20", "Bamberg", "Bayern");
        var result = new MatchModels.Result(
                "event-1",
                "2026-09-20",
                "Bamberg",
                "Bayern",
                72,
                108,
                List.of(13, 15, 22, 22),
                List.of(30, 25, 28, 25),
                "Germany",
                "BBL");
        var summary = new MatchModels.Summary(
                "2026-09-20",
                "Bamberg",
                "home.png",
                "Bayern",
                "away.png",
                "72",
                "108",
                "72",
                "13",
                "15",
                "22",
                "22",
                "0",
                "108",
                "30",
                "25",
                "28",
                "25",
                "0");
        var player = new MatchModels.Player(
                "Player",
                "Bayern",
                20,
                4,
                5,
                "25:00",
                7,
                12,
                4,
                6,
                3,
                6,
                3,
                4,
                8,
                1,
                3,
                2,
                1,
                2,
                0,
                0,
                1);
        var teamPlayers = new MatchModels.TeamPlayers("Bayern", List.of(player));
        var stat = new MatchModels.TeamStat(
                "Overall", "Scoring", "FGA", "Bamberg", "61", "Bayern", "66", "url");

        return new MatchModels.Match(
                "m1",
                fixture,
                result,
                summary,
                List.of(teamPlayers),
                List.of(stat),
                1,
                Set.of("fixture", "result", "summary", "players", "teamStats"),
                Instant.parse("2026-09-24T12:00:00Z"));
    }
}
