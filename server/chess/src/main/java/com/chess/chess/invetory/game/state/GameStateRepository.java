package com.chess.chess.invetory.game.state;

import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
@DependsOn({"flyway", "flywayInitializer"})
public class GameStateRepository extends NamedParameterJdbcDaoSupport
{
    public GameStateRepository(DataSource dataSource)
    {
        setDataSource(dataSource);
    }


    public void onGameCreation(final UUID id, final UUID gameUUID, final String state)
    {
        final String sql = """
                INSERT INTO game_state (id, game_id, state, time_updated)
                VALUES (:id, :game_id, :state, :time_updated);
                """;

        final MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id.toString())
                .addValue("game_id", gameUUID.toString())
                .addValue("state", state)
                .addValue("time_updated", LocalDateTime.now());

        Objects.requireNonNull(getNamedParameterJdbcTemplate()).update(sql, params);
    }

    public void update(GameState gameState, UUID uuid)
    {
        final String sql = """
                INSERT INTO game_state (id, game_id, player_id, state, time_updated)
                VALUES (:id, :game_id, :player_id, :state, :time_updated);
                """;

        final MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", uuid.toString())
                .addValue("game_id", gameState.getGameId())
                .addValue("state", gameState.getState())
                .addValue("time_updated", LocalDateTime.now());

        Objects.requireNonNull(getNamedParameterJdbcTemplate()).update(sql, params);
    }

    public Optional<String> getLastStateForGame(String game_id)
    {
        final String sql = """
                SELECT state
                FROM game_state
                WHERE game_id = :game_id
                ORDER BY time_updated DESC
                LIMIT 1;
                """;

        final MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("game_id", game_id);
        try {
            return Optional.ofNullable(Objects.requireNonNull(getNamedParameterJdbcTemplate()).queryForObject(sql, params, String.class));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
