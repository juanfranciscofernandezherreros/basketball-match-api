package com.fernandez.basketball.matchapi.match.model;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public final class MatchModels {

    private MatchModels() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public record Fixture(
            String country, String competition, String eventTime, String homeTeam, String awayTeam) {}

    public record Result(
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

    public record Summary(
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

    public record Player(
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

    public record TeamPlayers(String team, List<Player> players) {}

    public record TeamStat(
            String period,
            String category,
            String metric,
            String homeTeam,
            String homeValue,
            String awayTeam,
            String awayValue,
            String sourceUrl) {}

    public record PointByPointEvent(
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

    public record Match(
            String matchId,
            Fixture fixture,
            Result result,
            Summary summary,
            List<TeamPlayers> players,
            List<TeamStat> teamStats,
            int pointByPointEvents,
            Set<String> availableSections,
            Instant projectedAt) {}

    public record PointByPointQuarter(
            String matchId, String quarter, List<PointByPointEvent> events, Instant projectedAt) {}
}
