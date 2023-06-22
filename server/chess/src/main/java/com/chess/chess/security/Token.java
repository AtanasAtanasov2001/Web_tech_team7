package com.chess.chess.security;

public class Token
{
    public  String token;

    public Token(String token)
    {
        this.token = token;
    }

    public Token()
    {
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }
}
