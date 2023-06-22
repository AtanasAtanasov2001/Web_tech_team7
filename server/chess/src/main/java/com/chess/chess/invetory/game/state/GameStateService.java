package com.chess.chess.invetory.game.state;

import com.chess.chess.api.game.GameCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

        repository.onGameCreation(stateUUID, gameUUID, request.getState());
    }

    public String update(GameState gameState)
    {
        final UUID stateUUID = UUID.randomUUID();
        repository.update(gameState, stateUUID);

        return gameState.getGameId();
    }

    public ResponseEntity<String> getLastStateForGame(String game_id)
    {
        final Optional<String> state = repository.getLastStateForGame(game_id);
        return state.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
