package com.chess.chess.security;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing
{
    public static String generateStoringPasswordHash(String input) throws NoSuchAlgorithmException
    {

        String hashtext = null;
        input = "$%&#$)*@^%&()#@*%)#@salt%$%$%$%$" + input + "sa#$&#*)(%)(@%*#@)%&#@)#+#@$)_#@)$#@+lt";
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));

        hashtext = convertToHex(messageDigest);
        return hashtext;
    }

    private static String convertToHex(final byte[] messageDigest)
    {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }
}