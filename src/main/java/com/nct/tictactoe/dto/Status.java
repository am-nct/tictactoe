package com.nct.tictactoe.dto;

import com.nct.tictactoe.enumeration.Player;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Status {

    @Schema(description = "Current grid",
            example = "[[\"PLAYER_X\",\"PLAYER_O\",null],[\"PLAYER_O\",\"PLAYER_X\",null],[null,\"PLAYER_X\",null]]")
    private Player[][] grid;

    @Schema(description = "Contains null until the winner is found. Games can be over without a winner.",
            example = "null")
    private Player winner;

    @Schema(description = "Contains the player that needs to take the next turn. If nextPlayer is null, no turns are left, so the game is over.",
            example = "PLAYER_X")
    private Player nextPlayer;

}
