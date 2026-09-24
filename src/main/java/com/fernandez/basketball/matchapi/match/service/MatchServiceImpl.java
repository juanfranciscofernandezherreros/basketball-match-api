package com.fernandez.basketball.matchapi.match.service;

import com.fernandez.basketball.matchapi.match.exception.AppErrorCode;
import com.fernandez.basketball.matchapi.match.exception.AppException;
import com.fernandez.basketball.matchapi.match.mapper.MatchDocumentMapper;
import com.fernandez.basketball.matchapi.match.model.MatchModels;
import com.fernandez.basketball.matchapi.match.repository.MatchRepository;
import com.fernandez.basketball.matchapi.match.repository.PointByPointQuarterRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final PointByPointQuarterRepository pointByPointQuarterRepository;

    public MatchServiceImpl(
            MatchRepository matchRepository,
            PointByPointQuarterRepository pointByPointQuarterRepository) {
        this.matchRepository = matchRepository;
        this.pointByPointQuarterRepository = pointByPointQuarterRepository;
    }

    @Override
    public MatchModels.Match getMatch(String matchId) {
        var document = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new AppException(AppErrorCode.MATCH_NOT_FOUND));

        return MatchDocumentMapper.toModel(document);
    }

    @Override
    public List<MatchModels.TeamStat> getTeamStats(String matchId) {
        var match = getMatch(matchId);

        return match.teamStats() == null ? List.of() : match.teamStats();
    }

    @Override
    public List<MatchModels.TeamPlayers> getPlayers(String matchId) {
        var match = getMatch(matchId);

        return match.players() == null ? List.of() : match.players();
    }

    @Override
    public List<MatchModels.PointByPointQuarter> getPointByPoint(String matchId) {
        getMatch(matchId);

        return pointByPointQuarterRepository.findByMatchIdOrderByQuarterAsc(matchId).stream()
                .map(MatchDocumentMapper::toModel)
                .toList();
    }
}
