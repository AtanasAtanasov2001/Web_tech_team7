package com.chess.chess.invetory.game.state;

public class GameState
{
    private String state;
    private String gameId;

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getGameId()
    {
        return gameId;
    }

    public void setGameId(String gameId)
    {
        this.gameId = gameId;
    }


    @Override
    public String toString()
    {
        return "GameState{" +
                "state='" + state + '\'' +
                ", gameId='" + gameId + '\'' +
                '}';
    }
}
