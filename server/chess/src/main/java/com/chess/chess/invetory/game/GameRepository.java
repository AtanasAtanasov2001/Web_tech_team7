package com.chess.chess.invetory.game;

import com.chess.chess.api.game.GameCreateRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Repository
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
}
