package com.chess.chess.invetory.game;

import com.chess.chess.api.game.GameCreateRequest;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
@DependsOn({"flyway", "flywayInitializer"})
public class GameRepository extends NamedParameterJdbcDaoSupport
{
    public GameRepository(DataSource dataSource)
    {
        setDataSource(dataSource);
    }

    public void save(GameCreateRequest game, UUID uuid)
    {
        final String sql = """
                INSERT INTO game (id, white_id, black_id, time_created)
                VALUES (:id, :white_id, :black_id, :time_created);
                """;

        final MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", uuid.toString())
                .addValue("white_id", game.getWhiteId())
                .addValue("black_id", game.getBlackId())
                .addValue("time_created", LocalDateTime.now());


        Objects.requireNonNull(getNamedParameterJdbcTemplate()).update(sql, params);

    }

    public Game getGameById(String id)
    {
        final String sql = """
                SELECT g.white_id AS white_id,
                       g.black_id AS black_id,
                       g.id       AS id,
                       gs.state   AS state
                FROM game g
                         JOIN game_state gs ON g.id = gs.game_id
                WHERE g.id = :id
                ORDER BY gs.time_updated DESC
                LIMIT 1;
                """;

        final MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        return Objects.requireNonNull(getNamedParameterJdbcTemplate()).queryForObject(sql, params, new GameRowmapper());
    }

    public List<Game> getAllGames()
    {
        final String sql = """
                SELECT g.white_id AS white_id,
                       g.black_id AS black_id,
                       g.id       AS id,
                       gs.state   AS state
                FROM game g
                         JOIN game_state gs ON g.id = gs.game_id
                ORDER BY gs.time_updated DESC;
                """;

        return Objects.requireNonNull(getNamedParameterJdbcTemplate()).query(sql, new GameRowmapper());
    }
}
