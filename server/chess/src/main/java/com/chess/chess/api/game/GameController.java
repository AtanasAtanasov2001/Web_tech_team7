package com.chess.chess.api.game;

import com.chess.chess.invetory.game.Game;
import com.chess.chess.invetory.game.GameService;
import org.springframework.web.bind.annotation.*;

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
        return gameService.createGame(request);
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable("id") String id)
    {
        return gameService.getGameById(id);
    }
}
