package com.game.dungeon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.dungeon.dto.CreateGameRequest;
import com.game.dungeon.dto.MoveRequest;
import com.game.dungeon.dto.AnswerQuestionRequest;
import com.game.dungeon.model.Game;
import com.game.dungeon.model.Direction;
import com.game.dungeon.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    @Test
    void createGame_ShouldReturnNewGame() throws Exception {
        CreateGameRequest request = new CreateGameRequest();
        request.setPlayerName("testPlayer");
        request.setLevel(Game.GameLevel.EASY);

        Game game = new Game();
        game.setId("test-id");
        game.setPlayerName("testPlayer");
        game.setLevel(Game.GameLevel.EASY);
        game.setStatus(Game.GameStatus.IN_PROGRESS);

        when(gameService.createGame(eq("testPlayer"), eq(Game.GameLevel.EASY))).thenReturn(game);

        mockMvc.perform(post("/api/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("test-id"))
                .andExpect(jsonPath("$.playerName").value("testPlayer"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void movePlayer_ShouldUpdateGameState() throws Exception {
        String gameId = "test-id";
        MoveRequest request = new MoveRequest();
        request.setDirection(Direction.RIGHT);

        Game updatedGame = new Game();
        updatedGame.setId(gameId);
        updatedGame.setStatus(Game.GameStatus.IN_PROGRESS);

        when(gameService.movePlayer(eq(gameId), eq(Direction.RIGHT))).thenReturn(updatedGame);

        mockMvc.perform(post("/api/games/{gameId}/move", gameId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(gameId))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void answerQuestion_ShouldUpdateGameState() throws Exception {
        String gameId = "test-id";
        String questionId = "question-1";
        String answer = "test answer";

        AnswerQuestionRequest request = new AnswerQuestionRequest();
        request.setQuestionId(questionId);
        request.setAnswer(answer);

        Game updatedGame = new Game();
        updatedGame.setId(gameId);
        updatedGame.setQuestionsAnswered(1);

        when(gameService.answerQuestion(eq(gameId), eq(questionId), eq(answer))).thenReturn(updatedGame);

        mockMvc.perform(post("/api/games/{gameId}/answer", gameId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(gameId))
                .andExpect(jsonPath("$.questionsAnswered").value(1));
    }

    @Test
    void getGame_ShouldReturnGame() throws Exception {
        String gameId = "test-id";
        Game game = new Game();
        game.setId(gameId);
        game.setPlayerName("testPlayer");

        when(gameService.getGame(gameId)).thenReturn(game);

        mockMvc.perform(get("/api/games/{gameId}", gameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(gameId))
                .andExpect(jsonPath("$.playerName").value("testPlayer"));
    }
} 