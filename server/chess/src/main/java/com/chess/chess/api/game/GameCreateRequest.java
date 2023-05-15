package com.chess.chess.api.game;

public class GameCreateRequest
{
    private Long whiteId;
    private Long blackId;
    private String state;

    public GameCreateRequest(Long whiteId, Long blackId, String state)
    {
        this.whiteId = whiteId;
        this.blackId = blackId;
        this.state = state;
    }

    public Long getWhiteId()
    {
        return whiteId;
    }

    public Long getBlackId()
    {
        return blackId;
    }

    public String getState()
    {
        return state;
    }
}
