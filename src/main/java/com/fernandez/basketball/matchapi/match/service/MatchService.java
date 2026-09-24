package com.fernandez.basketball.matchapi.match.service;

import com.fernandez.basketball.matchapi.match.model.MatchModels;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchService {

    Page<MatchModels.Match> searchMatches(Pageable pageable);

    MatchModels.MatchDetail getMatchDetail(String matchId);

    MatchModels.Match getMatch(String matchId);

    List<MatchModels.TeamStat> getTeamStats(String matchId);

    List<MatchModels.TeamPlayers> getPlayers(String matchId);

    List<MatchModels.PointByPointQuarter> getPointByPoint(String matchId);
}
