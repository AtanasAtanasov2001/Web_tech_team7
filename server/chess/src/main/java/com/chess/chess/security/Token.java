package com.chess.chess.security;

public class Token
{
    public final String jwtLoginToken;

    public Token(String jwtLoginToken)
    {
        this.jwtLoginToken = jwtLoginToken;
    }
}
