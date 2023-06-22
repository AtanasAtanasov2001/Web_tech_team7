package com.chess.chess.api.game;

import com.chess.chess.invetory.game.state.GameState;
import com.chess.chess.invetory.game.state.GameStateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

@RestController
@RequestMapping(path = "state")
public class GameStateController
{
    private final GameStateService gameStateService;

    public GameStateController(GameStateService gameStateService)
    {
        this.gameStateService = gameStateService;
    }

    @PostMapping("/update")
    public String updateState(@RequestBody GameState request)
    {
        return gameStateService.update(request);
    }

    @GetMapping ("/{gameId}/currentState")
    public ResponseEntity<String> getCurrentState(@PathVariable("gameId") String gameId)
    {
        return gameStateService.getLastStateForGame(gameId);
    }
}
