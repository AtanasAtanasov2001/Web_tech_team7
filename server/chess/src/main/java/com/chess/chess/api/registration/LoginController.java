package com.chess.chess.api.registration;

import com.chess.chess.security.Hashing;
import com.chess.chess.security.JWTUtils;
import com.chess.chess.security.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(path = "registration/customer-login")
public class LoginController
{

    private final JWTUtils jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final Logger logger = Logger.getLogger(LoginController.class.getName());


    public LoginController(JWTUtils jwtUtil, AuthenticationManager authenticationManager)
    {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public Token generateToken(@RequestBody AuthRequest authRequest)
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.username(), Hashing.generateStoringPasswordHash(authRequest.password())));

        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password", e);
        }

        return new Token(jwtUtil.generateToken(authRequest.username()));
    }

    @PostMapping(value = "/skinny")
    public Token generateToken(@RequestParam String username, @RequestParam String password)
    {
        try {
            final var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, Hashing.generateStoringPasswordHash(password)));

            if(auth.isAuthenticated())
            {
                logger.info("User authenticated: " + username);
            }
            else
            {
                logger.info("User not authenticated: " + username);
            }

        } catch (Exception e) {
            throw new RuntimeException("Invalid username or password", e);
        }

        return new Token(jwtUtil.generateToken(username));
    }

}