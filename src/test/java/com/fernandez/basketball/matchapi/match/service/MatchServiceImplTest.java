package com.fernandez.basketball.matchapi.match.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fernandez.basketball.matchapi.match.document.MatchDocument;
import com.fernandez.basketball.matchapi.match.document.PointByPointQuarterDocument;
import com.fernandez.basketball.matchapi.match.exception.AppErrorCode;
import com.fernandez.basketball.matchapi.match.exception.AppException;
import com.fernandez.basketball.matchapi.match.model.MatchModels;
import com.fernandez.basketball.matchapi.match.repository.MatchRepository;
import com.fernandez.basketball.matchapi.match.repository.PointByPointQuarterRepository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MatchServiceImplTest {

    private final MatchRepository matchRepository = mock(MatchRepository.class);
    private final PointByPointQuarterRepository pointByPointQuarterRepository =
            mock(PointByPointQuarterRepository.class);
    private final MatchServiceImpl service =
            new MatchServiceImpl(matchRepository, pointByPointQuarterRepository);

    @Test
    void get_match_existing_ok() {
        // given
        var document = matchDocument(List.of(teamPlayers()), List.of(teamStat()));
        when(matchRepository.findById("m1")).thenReturn(Optional.of(document));

        // when
        var match = service.getMatch("m1");

        // then
        assertThat(match.matchId()).isEqualTo("m1");
        assertThat(match.teamStats()).hasSize(1);
        assertThat(match.players()).hasSize(1);
    }

    @Test
    void get_match_missing_ko() {
        // given
        when(matchRepository.findById("missing")).thenReturn(Optional.empty());

        // when / then
        assertThatThrownBy(() -> service.getMatch("missing"))
                .isInstanceOf(AppException.class)
                .extracting(exception -> ((AppException) exception).errorCode())
                .isEqualTo(AppErrorCode.MATCH_NOT_FOUND);
    }

    @Test
    void get_team_stats_missing_collection_value_returns_empty_ok() {
        // given
        var document = matchDocument(null, null);
        when(matchRepository.findById("m1")).thenReturn(Optional.of(document));

        // when
        var stats = service.getTeamStats("m1");
        var players = service.getPlayers("m1");

        // then
        assertThat(stats).isEmpty();
        assertThat(players).isEmpty();
    }

    @Test
    void get_point_by_point_existing_match_ok() {
        // given
        when(matchRepository.findById("m1"))
                .thenReturn(Optional.of(matchDocument(List.of(), List.of())));
        when(pointByPointQuarterRepository.findByMatchIdOrderByQuarterAsc("m1"))
                .thenReturn(List.of(new PointByPointQuarterDocument(
                        "m1:Q1",
                        "m1",
                        "Q1",
                        List.of(new MatchModels.PointByPointEvent(
                                "score",
                                "Q1",
                                1,
                                0,
                                2,
                                0,
                                2,
                                "away",
                                "2",
                                "away",
                                false,
                                true)),
                        Instant.parse("2026-09-24T12:00:00Z"))));

        // when
        var quarters = service.getPointByPoint("m1");

        // then
        assertThat(quarters).hasSize(1);
        assertThat(quarters.getFirst().quarter()).isEqualTo("Q1");
        assertThat(quarters.getFirst().events()).hasSize(1);
        verify(pointByPointQuarterRepository).findByMatchIdOrderByQuarterAsc("m1");
    }

    private MatchDocument matchDocument(
            List<MatchModels.TeamPlayers> players, List<MatchModels.TeamStat> stats) {
        return new MatchDocument(
                "m1",
                "m1",
                new MatchModels.Fixture("Germany", "BBL", "2026-09-20", "Bamberg", "Bayern"),
                null,
                null,
                players,
                stats,
                1,
                Set.of("fixture"),
                Instant.parse("2026-09-24T12:00:00Z"));
    }

    private MatchModels.TeamPlayers teamPlayers() {
        return new MatchModels.TeamPlayers(
                "Bayern",
                List.of(new MatchModels.Player(
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
                        1)));
    }

    private MatchModels.TeamStat teamStat() {
        return new MatchModels.TeamStat(
                "Overall", "Scoring", "FGA", "Bamberg", "61", "Bayern", "66", "https://example");
    }
}
