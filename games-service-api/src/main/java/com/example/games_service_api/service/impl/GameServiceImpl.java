package com.example.games_service_api.service.impl;

import com.example.games_service_api.commons.entities.GameModel;
import com.example.games_service_api.repository.GameRepository;
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
    public GameModel createGame(GameModel gameRequest) {
        return Optional.of(gameRequest)
                .map(this::mapToEntity)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error creating game"));
    }

    @Override
    public GameModel getGame(Long gameId) {
        return Optional.of(gameId)
                    .flatMap(gameRepository::findById)
                    .orElseThrow(() -> new RuntimeException("Error couldn't find game by id"));
    }

    @Override
    public void putGame(Long gameId, GameModel gameRequest) {
        gameRepository.findById(gameId)
                .map(existingGame -> updatedFieldsGame(existingGame, gameRequest))
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("Error couldn't edit game by id"));
    }

    private GameModel updatedFieldsGame(GameModel existingGame, GameModel gameRequest) {
        existingGame.setName(gameRequest.getName());
        return existingGame;
    }

    @Override
    public void deleteGame(Long gameId) {
        Optional.of(gameId)
                .map(this::getGame)
                .ifPresent(gameRepository::delete);
    }

    private GameModel mapToEntity(GameModel gameRequest) {
        return GameModel.builder()
                .name(gameRequest.getName())
                .build();
    }
}
