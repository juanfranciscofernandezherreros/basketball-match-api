package com.fernandez.basketball.matchapi.match.controller;

import com.fernandez.basketball.matchapi.match.dto.MatchDtos;
import com.fernandez.basketball.matchapi.match.mapper.MatchMapper;
import com.fernandez.basketball.matchapi.match.service.MatchService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{matchId}")
    public MatchDtos.MatchResponse getMatch(@PathVariable String matchId) {
        var match = matchService.getMatch(matchId);
        var response = MatchMapper.toDto(match);

        return response;
    }

    @GetMapping("/{matchId}/stats")
    public List<MatchDtos.TeamStatResponse> getTeamStats(@PathVariable String matchId) {
        var stats = matchService.getTeamStats(matchId);
        var response = MatchMapper.statsToDto(stats);

        return response;
    }

    @GetMapping("/{matchId}/players")
    public List<MatchDtos.TeamPlayersResponse> getPlayers(@PathVariable String matchId) {
        var players = matchService.getPlayers(matchId);
        var response = MatchMapper.playersToDto(players);

        return response;
    }

    @GetMapping("/{matchId}/point-by-point")
    public List<MatchDtos.PointByPointQuarterResponse> getPointByPoint(
            @PathVariable String matchId) {
        var pointByPoint = matchService.getPointByPoint(matchId);
        var response = MatchMapper.pointByPointToDto(pointByPoint);

        return response;
    }
}
