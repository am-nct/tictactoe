package com.nct.tictactoe.controller;

import com.nct.tictactoe.dto.Status;
import com.nct.tictactoe.dto.Turn;
import com.nct.tictactoe.service.GridService;
import com.nct.tictactoe.service.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TicTacToeController {

    private final GridService gridService;
    private final StatusService statusService;

    @Operation(summary = "Returns the current status of the game.")
    @GetMapping("/status")
    @ResponseBody
    public Status status() {
        return statusService.getStatus();
    }

    @Operation(summary = "Send the next mark for a player.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input supplied", content = @Content)
    })
    @PostMapping("/takeTurn")
    public void takeTurn(@Valid @RequestBody Turn turn) {
        statusService.validatePlayer(turn);
        gridService.takeTurn(turn);
    }

    @Operation(summary = "Start a new game, the progress of the current game will be lost.")
    @PutMapping("/newGame")
    public void newGame() {
        gridService.newGame();
    }

}
