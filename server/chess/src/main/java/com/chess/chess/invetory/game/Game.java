package com.chess.chess.invetory.game;

public class Game
{
    private Long whiteId;
    private Long blackId;
    private Long winnerId;
    private String id;

    public Long getWhiteId()
    {
        return whiteId;
    }

    public void setWhiteId(Long whiteId)
    {
        this.whiteId = whiteId;
    }

    public Long getBlackId()
    {
        return blackId;
    }

    public void setBlackId(Long blackId)
    {
        this.blackId = blackId;
    }

    public Long getWinnerId()
    {
        return winnerId;
    }

    public void setWinnerId(Long winnerId)
    {
        this.winnerId = winnerId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Game{" +
                "whiteId=" + whiteId +
                ", blackId=" + blackId +
                ", winnerId=" + winnerId +
                ", Id='" + id + '\'' +
                '}';
    }
}
