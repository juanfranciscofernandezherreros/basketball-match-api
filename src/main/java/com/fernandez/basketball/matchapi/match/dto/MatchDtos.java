package com.fernandez.basketball.matchapi.match.dto;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public final class MatchDtos {

    private MatchDtos() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public record FixtureResponse(
            String country, String competition, String eventTime, String homeTeam, String awayTeam) {}

    public record ResultResponse(
            String sourceEventId,
            String eventTime,
            String homeTeam,
            String awayTeam,
            Integer homeScore,
            Integer awayScore,
            List<Integer> homePeriods,
            List<Integer> awayPeriods,
            String country,
            String competition) {}

    public record SummaryResponse(
            String date,
            String homeName,
            String homeImage,
            String awayName,
            String awayImage,
            String resultHome,
            String resultAway,
            String totalLocal,
            String firstLocal,
            String secondLocal,
            String thirdLocal,
            String fourthLocal,
            String extraLocal,
            String totalAway,
            String firstAway,
            String secondAway,
            String thirdAway,
            String fourthAway,
            String extraAway) {}

    public record PlayerResponse(
            String name,
            String team,
            Integer pts,
            Integer reb,
            Integer ast,
            String min,
            Integer fgm,
            Integer fga,
            Integer twopm,
            Integer twopa,
            Integer threepm,
            Integer threepa,
            Integer ftm,
            Integer fta,
            Integer plusMinus,
            Integer offensiveRebounds,
            Integer defensiveRebounds,
            Integer personalFouls,
            Integer steals,
            Integer turnovers,
            Integer blocks,
            Integer blocksAgainst,
            Integer tfs) {}

    public record TeamPlayersResponse(String team, List<PlayerResponse> players) {}

    public record TeamStatResponse(
            String period,
            String category,
            String metric,
            String homeTeam,
            String homeValue,
            String awayTeam,
            String awayValue,
            String sourceUrl) {}

    public record PointByPointEventResponse(
            String recordType,
            String quarter,
            Integer sequence,
            Integer homeScore,
            Integer awayScore,
            Integer homePointsAdded,
            Integer awayPointsAdded,
            String leaderSide,
            String advantage,
            String advantageDirection,
            Boolean homeIsWinning,
            Boolean awayIsWinning) {}

    public record PointByPointQuarterResponse(
            String matchId,
            String quarter,
            List<PointByPointEventResponse> events,
            Instant projectedAt) {}

    public record MatchResponse(
            String matchId,
            FixtureResponse fixture,
            ResultResponse result,
            SummaryResponse summary,
            List<TeamPlayersResponse> players,
            List<TeamStatResponse> teamStats,
            int pointByPointEvents,
            Set<String> availableSections,
            Instant projectedAt) {}

    public record ErrorResponse(
            String code, String message, Instant timestamp, String path) {}
}
