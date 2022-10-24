package com.nct.tictactoe.dto;

import com.nct.tictactoe.enumeration.Player;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class Turn {

    @NotNull(message = "horizontalPosition can't be null.")
    @Min(value = 0, message = "horizontalPosition must be between 0 and 2")
    @Max(value = 2, message = "horizontalPosition must be between 0 and 2")
    @Schema(description = "The horizontal position where a player puts a mark. The position is counted from left to right starting at 0.",
            example = "1")
    private Integer horizontalPosition;

    @NotNull(message = "verticalPosition can't be null.")
    @Min(value = 0, message = "verticalPosition must be between 0 and 2")
    @Max(value = 2, message = "verticalPosition must be between 0 and 2")
    @Schema(description = "The vertical position where a player puts a mark. The position is counted from top to bottom starting at 0.",
            example = "2")
    private Integer verticalPosition;

    @NotNull(message = "player can't be null.")
    @Schema(description = "The player that takes the turn.",
            example = "PLAYER_O")
    private Player player;
}
