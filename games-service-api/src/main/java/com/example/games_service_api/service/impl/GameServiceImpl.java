package com.example.games_service_api.service.impl;

import com.example.games_service_api.commons.dtos.GameResponse;
import com.example.games_service_api.commons.dtos.GameRequest;
import com.example.games_service_api.commons.entities.GameModel;
import com.example.games_service_api.repositories.GameRepository;
import com.example.games_service_api.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameResponse createGame(Long userId, GameRequest gameRequest) {
        return Optional.of(gameRequest)
                .map(request -> mapToEntity(userId, request))
                .map(gameRepository::save)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Error creating game"));
    }

    @Override
    public GameResponse getGame(Long userId, Long gameId) {
        return gameRepository.findById(gameId)
                .filter(game -> game.getUserId().equals(userId))
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Error: couldn't find game by id or userId"));
    }

    @Override
    public void putGame(Long userId, Long gameId, GameRequest gameRequest) {
        gameRepository.findById(gameId)
                .filter(game -> game.getUserId().equals(userId))
                .map(existingGame -> updatedFieldsGame(existingGame, gameRequest))
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error couldn't edit game by id"));
    }

    private GameModel updatedFieldsGame(GameModel existingGame, GameRequest gameRequest) {
        existingGame.setName(gameRequest.getName());
        return existingGame;
    }

    @Override
    public void deleteGame(Long userId, Long gameId) {
        gameRepository.findById(gameId)
                .filter(game -> game.getUserId().equals(userId))
                .ifPresentOrElse(
                        gameRepository::delete,
                        () -> { throw new RuntimeException("Error: couldn't find game to delete by id"); }
                );
    }

    private GameModel mapToEntity(Long userId, GameRequest gameRequest) {
        return GameModel.builder()
                .name(gameRequest.getName())
                .userId(userId)
                .build();
    }

    private GameResponse mapToResponse(GameModel gameModel) {
        return new GameResponse(gameModel.getGameId(), gameModel.getName());
    }
}
