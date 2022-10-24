package com.nct.tictactoe.service;

import com.nct.tictactoe.dto.Turn;
import com.nct.tictactoe.enumeration.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class GridService {

    public static final int GRID_SIZE = 3;

    private final Player[][] grid = new Player[GRID_SIZE][GRID_SIZE];

    public Player[][] getCurrentGrid() {
        return grid;
    }

    public void takeTurn(Turn turn) {
        if (grid[turn.getVerticalPosition()][turn.getHorizontalPosition()] != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid turn: This field is already marked.");
        }

        grid[turn.getVerticalPosition()][turn.getHorizontalPosition()] = turn.getPlayer();
    }

    public void newGame() {
        for (Player[] array: grid) {
            Arrays.fill(array, null);
        }
    }

}
