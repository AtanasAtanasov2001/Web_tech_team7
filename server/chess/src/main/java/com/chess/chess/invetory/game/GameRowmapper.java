package com.chess.chess.invetory.game;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRowmapper implements RowMapper<Game>
{
    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Game(rs.getLong("white_id"),
                rs.getLong("black_id"),
                rs.getString("id"),
                rs.getString("state"));
    }
}
