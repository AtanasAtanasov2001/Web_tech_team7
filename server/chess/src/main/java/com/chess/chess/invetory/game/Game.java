package com.chess.chess.invetory.game;

public class Game
{
    private Long whiteId;
    private Long blackId;
    private String id;

    private String state;

    public Game(Long whiteId, Long blackId, String id, String state)
    {
        this.whiteId = whiteId;
        this.blackId = blackId;
        this.id = id;
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

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
                ", id='" + id + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
