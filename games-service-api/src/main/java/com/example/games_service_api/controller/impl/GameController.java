package com.example.games_service_api.controller.impl;

import com.example.games_service_api.commons.entities.GameModel;
import com.example.games_service_api.controller.GameApi;
import com.example.games_service_api.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GameApi {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<GameModel> createGame(Long userId, GameModel gameRequest) {
        gameRequest.setUserId(userId);
        return ResponseEntity.ok(gameService.createGame(gameRequest));
    }

    @Override
    public ResponseEntity<GameModel> getGame(Long userId, Long gameId) {
        GameModel game = gameService.getGame(gameId);

        if (game.getUserId() != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(game);
    }

    @Override
    public ResponseEntity<Void> putGame(Long userId, Long gameId, GameModel gameRequest) {
        GameModel game = gameService.getGame(gameId);

        if (game.getUserId() != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        gameService.putGame(gameId, gameRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteGame(Long userId, Long gameId) {
        GameModel game = gameService.getGame(gameId);

        if (game.getUserId() != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }
}
