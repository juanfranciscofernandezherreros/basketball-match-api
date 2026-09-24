package com.fernandez.basketball.matchapi.match.mapper;

import com.fernandez.basketball.matchapi.match.dto.MatchDtos;
import com.fernandez.basketball.matchapi.match.model.MatchModels;
import java.util.List;

public final class MatchMapper {

    private MatchMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static MatchDtos.MatchResponse toDto(MatchModels.Match model) {
        if (model == null) {
            return null;
        }

        MatchDtos.MatchResponse response = new MatchDtos.MatchResponse(
                model.matchId(),
                fixtureToDto(model.fixture()),
                resultToDto(model.result()),
                summaryToDto(model.summary()),
                playersToDto(model.players()),
                statsToDto(model.teamStats()),
                model.pointByPointEvents(),
                model.availableSections(),
                model.projectedAt());

        return response;
    }

    public static List<MatchDtos.TeamStatResponse> statsToDto(List<MatchModels.TeamStat> stats) {
        if (stats == null) {
            return List.of();
        }

        List<MatchDtos.TeamStatResponse> response = stats.stream()
                .map(stat -> new MatchDtos.TeamStatResponse(
                        stat.period(),
                        stat.category(),
                        stat.metric(),
                        stat.homeTeam(),
                        stat.homeValue(),
                        stat.awayTeam(),
                        stat.awayValue(),
                        stat.sourceUrl()))
                .toList();

        return response;
    }

    public static List<MatchDtos.TeamPlayersResponse> playersToDto(
            List<MatchModels.TeamPlayers> teams) {
        if (teams == null) {
            return List.of();
        }

        List<MatchDtos.TeamPlayersResponse> response = teams.stream()
                .map(team -> new MatchDtos.TeamPlayersResponse(
                        team.team(),
                        team.players() == null
                                ? List.of()
                                : team.players().stream().map(MatchMapper::playerToDto).toList()))
                .toList();

        return response;
    }

    public static List<MatchDtos.PointByPointQuarterResponse> pointByPointToDto(
            List<MatchModels.PointByPointQuarter> quarters) {
        if (quarters == null) {
            return List.of();
        }

        List<MatchDtos.PointByPointQuarterResponse> response = quarters.stream()
                .map(quarter -> new MatchDtos.PointByPointQuarterResponse(
                        quarter.matchId(),
                        quarter.quarter(),
                        quarter.events() == null
                                ? List.of()
                                : quarter.events().stream()
                                        .map(MatchMapper::pointByPointEventToDto)
                                        .toList(),
                        quarter.projectedAt()))
                .toList();

        return response;
    }

    private static MatchDtos.FixtureResponse fixtureToDto(MatchModels.Fixture fixture) {
        if (fixture == null) {
            return null;
        }

        MatchDtos.FixtureResponse response = new MatchDtos.FixtureResponse(
                fixture.country(),
                fixture.competition(),
                fixture.eventTime(),
                fixture.homeTeam(),
                fixture.awayTeam());

        return response;
    }

    private static MatchDtos.ResultResponse resultToDto(MatchModels.Result result) {
        if (result == null) {
            return null;
        }

        MatchDtos.ResultResponse response = new MatchDtos.ResultResponse(
                result.sourceEventId(),
                result.eventTime(),
                result.homeTeam(),
                result.awayTeam(),
                result.homeScore(),
                result.awayScore(),
                result.homePeriods(),
                result.awayPeriods(),
                result.country(),
                result.competition());

        return response;
    }

    private static MatchDtos.SummaryResponse summaryToDto(MatchModels.Summary summary) {
        if (summary == null) {
            return null;
        }

        MatchDtos.SummaryResponse response = new MatchDtos.SummaryResponse(
                summary.date(),
                summary.homeName(),
                summary.homeImage(),
                summary.awayName(),
                summary.awayImage(),
                summary.resultHome(),
                summary.resultAway(),
                summary.totalLocal(),
                summary.firstLocal(),
                summary.secondLocal(),
                summary.thirdLocal(),
                summary.fourthLocal(),
                summary.extraLocal(),
                summary.totalAway(),
                summary.firstAway(),
                summary.secondAway(),
                summary.thirdAway(),
                summary.fourthAway(),
                summary.extraAway());

        return response;
    }

    private static MatchDtos.PlayerResponse playerToDto(MatchModels.Player player) {
        MatchDtos.PlayerResponse response = new MatchDtos.PlayerResponse(
                player.name(),
                player.team(),
                player.pts(),
                player.reb(),
                player.ast(),
                player.min(),
                player.fgm(),
                player.fga(),
                player.twopm(),
                player.twopa(),
                player.threepm(),
                player.threepa(),
                player.ftm(),
                player.fta(),
                player.plusMinus(),
                player.offensiveRebounds(),
                player.defensiveRebounds(),
                player.personalFouls(),
                player.steals(),
                player.turnovers(),
                player.blocks(),
                player.blocksAgainst(),
                player.tfs());

        return response;
    }

    private static MatchDtos.PointByPointEventResponse pointByPointEventToDto(
            MatchModels.PointByPointEvent event) {
        MatchDtos.PointByPointEventResponse response = new MatchDtos.PointByPointEventResponse(
                event.recordType(),
                event.quarter(),
                event.sequence(),
                event.homeScore(),
                event.awayScore(),
                event.homePointsAdded(),
                event.awayPointsAdded(),
                event.leaderSide(),
                event.advantage(),
                event.advantageDirection(),
                event.homeIsWinning(),
                event.awayIsWinning());

        return response;
    }
}
