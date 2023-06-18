package com.chess.chess.security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class Hashing
{

    private final static Logger LOGGER = Logger.getLogger(Hashing.class.getName());

    public static String generateStoringPasswordHash(String input)
    {
        try {
            String hashtext;
            input = "$%&#$)*@^%&()#@*%)#@salt%$%$%$%$" + input + "sa#$&#*)(%)(@%*#@)%&#@)#+#@$)_#@)$#@+lt";
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            hashtext = convertToHex(messageDigest);
            return hashtext;

        } catch (NoSuchAlgorithmException e) {
            LOGGER.info("No such algorithm exception");
        }

        return "unknown";
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