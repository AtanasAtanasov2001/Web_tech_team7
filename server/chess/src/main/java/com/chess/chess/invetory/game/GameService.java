package com.chess.chess.invetory.game;

import com.chess.chess.api.game.GameCreateRequest;
import com.chess.chess.invetory.game.state.GameStateService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService
{
    private final GameRepository repository;

    private final GameStateService gameStateService;

    public GameService(GameRepository repository, GameStateService gameStateService)
    {
        this.repository = repository;
        this.gameStateService = gameStateService;
    }


    public String createGame(GameCreateRequest request)
    {
        final UUID gameUUID = UUID.randomUUID();

        repository.save(request, gameUUID);

        gameStateService.save(request, gameUUID);

        return gameUUID.toString();
    }
}
