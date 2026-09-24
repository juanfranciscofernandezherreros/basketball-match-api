package com.fernandez.basketball.matchapi.match.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fernandez.basketball.matchapi.match.model.MatchModels;
import com.fernandez.basketball.matchapi.match.service.MatchService;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MatchControllerTest {

    private final MatchService matchService = mock(MatchService.class);
    private final MatchController controller = new MatchController(matchService);

    @Test
    void delegates_all_read_endpoints_ok() {
        // given
        var match = new MatchModels.Match(
                "m1", null, null, null, List.of(), List.of(), 0, Set.of(), Instant.EPOCH);
        var stat = new MatchModels.TeamStat(
                "Overall", "Scoring", "FGA", "A", "60", "B", "55", null);
        var players = new MatchModels.TeamPlayers("A", List.of());
        var quarter = new MatchModels.PointByPointQuarter("m1", "Q1", List.of(), Instant.EPOCH);

        when(matchService.getMatch("m1")).thenReturn(match);
        when(matchService.getTeamStats("m1")).thenReturn(List.of(stat));
        when(matchService.getPlayers("m1")).thenReturn(List.of(players));
        when(matchService.getPointByPoint("m1")).thenReturn(List.of(quarter));

        // when
        var matchResponse = controller.getMatch("m1");
        var statsResponse = controller.getTeamStats("m1");
        var playersResponse = controller.getPlayers("m1");
        var pointByPointResponse = controller.getPointByPoint("m1");

        // then
        assertThat(matchResponse.matchId()).isEqualTo("m1");
        assertThat(statsResponse).hasSize(1);
        assertThat(playersResponse).hasSize(1);
        assertThat(pointByPointResponse).hasSize(1);
    }
}
