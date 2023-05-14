package com.chess.chess.invetory.game.state;

import com.chess.chess.api.game.GameCreateRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameStateService
{
    private final GameStateRepository repository;

    public GameStateService(GameStateRepository repository)
    {
        this.repository = repository;
    }

    public void save(GameCreateRequest request, UUID gameUUID)
    {
        final UUID stateUUID = UUID.randomUUID();

        repository.onGameCreation(stateUUID, gameUUID, request.getBlackId(), request.getState());
    }

    public void update(GameState gameState)
    {
        final UUID stateUUID = UUID.randomUUID();
        repository.update(gameState, stateUUID);
    }

    public String getLastStateForGame(UUID game_id)
    {
        return repository.getLastStateForGame(game_id);
    }
}
