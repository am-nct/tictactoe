package com.nct.tictactoe.service;

import com.nct.tictactoe.dto.Status;
import com.nct.tictactoe.dto.Turn;
import com.nct.tictactoe.enumeration.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final GridService gridService;

    public Status getStatus() {
        Player[][] grid = gridService.getCurrentGrid();
        Player winner = getWinner(grid);
        Player nextPlayer = winner != null ? null : getNextPlayer(grid);
        return Status.builder().grid(grid).nextPlayer(nextPlayer).winner(winner).build();
    }

    public void validatePlayer(Turn turn) {
        if (getNextPlayer(gridService.getCurrentGrid()) != turn.getPlayer()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid turn: " + turn.getPlayer() + " should make the next turn.");
        }
    }

    private Player getWinner(Player[][] grid) {
        for (Player player : Player.values()) {
            if (isVerticalWinner(grid, player) || isHorizontalWinner(grid, player) || isDiagonalWinner(grid, player)) {
                return player;
            }
        }
        return null;
    }

    private boolean isVerticalWinner(Player[][] grid, Player player) {
        if (grid[0][0] == player && grid[1][0] == player && grid[2][0] == player) {
            return true;
        } else if (grid[0][1] == player && grid[1][1] == player && grid[2][1] == player) {
            return true;
        } else if (grid[0][2] == player && grid[1][2] == player && grid[2][2] == player) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isHorizontalWinner(Player[][] grid, Player player) {
        if (grid[0][0] == player && grid[0][1] == player && grid[0][2] == player) {
            return true;
        } else if (grid[1][0] == player && grid[1][1] == player && grid[1][2] == player) {
            return true;
        } else if (grid[2][0] == player && grid[2][1] == player && grid[2][2] == player) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDiagonalWinner(Player[][] grid, Player player) {
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true;
        } else if (grid[2][0] == player && grid[1][1] == player && grid[0][2] == player) {
            return true;
        } else {
            return false;
        }
    }
    private Player getNextPlayer(Player[][] grid) {
        Map<Player, Long> playerCounters = Arrays.stream(grid)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Long playerXCounter = playerCounters.get(Player.PLAYER_X) != null ? playerCounters.get(Player.PLAYER_X) : 0;
        Long playerOCounter = playerCounters.get(Player.PLAYER_O) != null ? playerCounters.get(Player.PLAYER_O) : 0;

        if (playerXCounter + playerOCounter == GridService.GRID_SIZE * GridService.GRID_SIZE) {
            return null;
        }
        return playerXCounter <= playerOCounter ? Player.PLAYER_X : Player.PLAYER_O;
    }
}
