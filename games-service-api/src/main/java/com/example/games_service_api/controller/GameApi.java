package com.example.games_service_api.controller;

import com.example.games_service_api.commons.constants.ApiPathConstants;
import com.example.games_service_api.commons.entities.GameModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.GAME_ROUTE)
public interface GameApi {
    @PostMapping(value = "/create")
    ResponseEntity<GameModel> createGame(
            @RequestAttribute("userIdRequest") Long userId,
            @RequestBody GameModel gameRequest
    );

    @GetMapping(value = "/{gameId}")
    ResponseEntity<GameModel> getGame(
            @RequestAttribute("userIdRequest") Long userId,
            @PathVariable Long gameId
    );

    @PutMapping(value = "/{gameId}")
    ResponseEntity<Void> putGame(
            @RequestAttribute("userIdRequest") Long userId,
            @PathVariable Long gameId,
            @RequestBody GameModel gameRequest
    );

    @DeleteMapping(value = "/{gameId}")
    ResponseEntity<Void> deleteGame(
            @RequestAttribute("userIdRequest") Long userId,
            @PathVariable Long gameId
    );
}
