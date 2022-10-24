package com.nct.tictactoe.unit;

import com.nct.tictactoe.dto.Status;
import com.nct.tictactoe.enumeration.Player;
import com.nct.tictactoe.service.GridService;
import com.nct.tictactoe.service.StatusService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.nct.tictactoe.enumeration.Player.PLAYER_O;
import static com.nct.tictactoe.enumeration.Player.PLAYER_X;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StatusServiceTests {

    @Mock
    private GridService gridService;

    @InjectMocks
    private StatusService statusService;

    @Test
    void winnersAreDetected() {
        Player[][] grid = {
                {null, null, null},
                {null, null, null},
                {null, null, null}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isEqualTo(PLAYER_X);
        assertThat(status.getWinner()).isNull();
    }

    @Test
    void diagonalWinnersAreDetected1() {
        Player[][] grid = {
                {PLAYER_X, null,     null},
                {null,     PLAYER_X, null},
                {PLAYER_O, PLAYER_O, PLAYER_X}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isEqualTo(PLAYER_X);
    }

    @Test
    void diagonalWinnersAreDetected2() {
        Player[][] grid = {
                {PLAYER_X, PLAYER_X, PLAYER_O},
                {null,     PLAYER_O, PLAYER_X},
                {PLAYER_O, PLAYER_O, PLAYER_X}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isEqualTo(PLAYER_O);
    }

    @Test
    void horizontalWinnersAreDetectedInFirstRow() {
        Player[][] grid = {
                {PLAYER_X, PLAYER_X, PLAYER_X},
                {null,     PLAYER_O, PLAYER_O},
                {PLAYER_O, PLAYER_O, PLAYER_X}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isEqualTo(PLAYER_X);
    }

    @Test
    void horizontalWinnersAreDetectedInSecondRow() {
        Player[][] grid = {
                {null,     PLAYER_O, PLAYER_O},
                {PLAYER_X, PLAYER_X, PLAYER_X},
                {PLAYER_O, PLAYER_O, PLAYER_X}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getGrid()).isEqualTo(grid);
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isEqualTo(PLAYER_X);
    }

    @Test
    void horizontalWinnersAreDetectedInThirdRow() {
        Player[][] grid = {
                {null,     PLAYER_O, PLAYER_O},
                {PLAYER_O, PLAYER_O, PLAYER_X},
                {PLAYER_X, PLAYER_X, PLAYER_X}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isEqualTo(PLAYER_X);
    }

    @Test
    void verticalWinnersAreDetectedInFirstColumn() {
        Player[][] grid = {
                {PLAYER_O, null,     PLAYER_O},
                {PLAYER_O, PLAYER_X, PLAYER_X},
                {PLAYER_O, PLAYER_X, PLAYER_X}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isEqualTo(PLAYER_O);
    }

    @Test
    void verticalWinnersAreDetectedInSecondColumn() {
        Player[][] grid = {
                {null,     PLAYER_O, PLAYER_O},
                {PLAYER_X, PLAYER_O, PLAYER_X},
                {PLAYER_X, PLAYER_O, PLAYER_X}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isEqualTo(PLAYER_O);
    }

    @Test
    void verticalWinnersAreDetectedThirdColumn() {
        Player[][] grid = {
                {null,     PLAYER_O, PLAYER_O},
                {PLAYER_X, PLAYER_X, PLAYER_O},
                {PLAYER_X, PLAYER_X, PLAYER_O}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isEqualTo(PLAYER_O);
    }

    @Test
    void gameEndsWithoutWinner() {
        Player[][] grid = {
                {PLAYER_X, PLAYER_O, PLAYER_X},
                {PLAYER_O, PLAYER_O, PLAYER_X},
                {PLAYER_X, PLAYER_X, PLAYER_O}};
        given(gridService.getCurrentGrid()).willReturn(grid);
        Status status = statusService.getStatus();
        assertThat(status.getNextPlayer()).isNull();
        assertThat(status.getWinner()).isNull();
    }

}
