package com.chess.chess.api.game;

import com.chess.chess.invetory.game.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "game")
public class GameController
{
    private final GameService gameService;

    public GameController(GameService gameService)
    {
        this.gameService = gameService;
    }

    @PostMapping
    public String createGame(@RequestBody GameCreateRequest request)
    {
        String game = gameService.createGame(request);
        return game;
    }
}
