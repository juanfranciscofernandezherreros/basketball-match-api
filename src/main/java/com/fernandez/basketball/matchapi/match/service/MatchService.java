package com.fernandez.basketball.matchapi.match.service;

import com.fernandez.basketball.matchapi.match.model.MatchModels;
import java.util.List;

public interface MatchService {

    MatchModels.Match getMatch(String matchId);

    List<MatchModels.TeamStat> getTeamStats(String matchId);

    List<MatchModels.TeamPlayers> getPlayers(String matchId);

    List<MatchModels.PointByPointQuarter> getPointByPoint(String matchId);
}
