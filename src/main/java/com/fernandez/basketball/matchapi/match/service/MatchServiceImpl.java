package com.fernandez.basketball.matchapi.match.service;

import com.fernandez.basketball.matchapi.match.exception.AppErrorCode;
import com.fernandez.basketball.matchapi.match.exception.AppException;
import com.fernandez.basketball.matchapi.match.mapper.MatchDocumentMapper;
import com.fernandez.basketball.matchapi.match.model.MatchModels;
import com.fernandez.basketball.matchapi.match.repository.MatchRepository;
import com.fernandez.basketball.matchapi.match.repository.PointByPointQuarterRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<MatchModels.Match> searchMatches(Pageable pageable) {
        return matchRepository.findAll(pageable).map(MatchDocumentMapper::toModel);
    }

    @Override
    public MatchModels.MatchDetail getMatchDetail(String matchId) {
        MatchModels.Match match = getMatch(matchId);
        List<MatchModels.PointByPointQuarter> pointByPoint =
                pointByPointQuarterRepository.findByMatchIdOrderByQuarterAsc(matchId).stream()
                        .map(MatchDocumentMapper::toModel)
                        .toList();

        return new MatchModels.MatchDetail(match, pointByPoint);
    }

    @Override
    public MatchModels.Match getMatch(String matchId) {
        com.fernandez.basketball.matchapi.match.document.MatchDocument document = matchRepository
                .findById(matchId)
                .orElseThrow(() -> new AppException(AppErrorCode.MATCH_NOT_FOUND));

        return MatchDocumentMapper.toModel(document);
    }

    @Override
    public List<MatchModels.TeamStat> getTeamStats(String matchId) {
        MatchModels.Match match = getMatch(matchId);

        return match.teamStats() == null ? List.of() : match.teamStats();
    }

    @Override
    public List<MatchModels.TeamPlayers> getPlayers(String matchId) {
        MatchModels.Match match = getMatch(matchId);

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
