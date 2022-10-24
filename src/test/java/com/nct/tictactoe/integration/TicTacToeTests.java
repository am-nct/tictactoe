package com.nct.tictactoe.integration;

import com.nct.tictactoe.dto.Status;
import com.nct.tictactoe.enumeration.Player;
import org.junit.jupiter.api.Test;

import static com.nct.tictactoe.enumeration.Player.PLAYER_O;
import static com.nct.tictactoe.enumeration.Player.PLAYER_X;
import static org.assertj.core.api.Assertions.assertThat;

class TicTacToeTests extends TestBase {

    @Test
    void gameIsInitializedCorrectly() {
        startNewGame();
        Status status = getStatus();
        Player[][] expectedGrid = {
                {null, null, null},
                {null, null, null},
                {null, null, null}};
        assertThat(status.getGrid()).isEqualTo(expectedGrid);
        assertThat(status.getWinner()).isNull();
        assertThat(status.getNextPlayer()).isEqualTo(PLAYER_X);
    }

    @Test
    void gameIsRestartedCorrectly() {
        startNewGame();
        Status status = getStatus();
        Player[][] expectedGrid = {
                {null, null, null},
                {null, null, null},
                {null, null, null}};
        assertThat(status.getGrid()).isEqualTo(expectedGrid);
        assertThat(status.getWinner()).isNull();
        assertThat(status.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status2 = takeTurnAndGetStatus(1, 1, PLAYER_X);
        Player[][] expectedGrid2 = {
                {null, null,     null},
                {null, PLAYER_X, null},
                {null, null,     null}};
        assertThat(status2.getGrid()).isEqualTo(expectedGrid2);
        assertThat(status2.getWinner()).isNull();
        assertThat(status2.getNextPlayer()).isEqualTo(PLAYER_O);

        startNewGame();

        Status status3 = getStatus();
        Player[][] expectedGrid3 = {
                {null, null, null},
                {null, null, null},
                {null, null, null}};
        assertThat(status3.getGrid()).isEqualTo(expectedGrid3);
        assertThat(status3.getWinner()).isNull();
        assertThat(status3.getNextPlayer()).isEqualTo(PLAYER_X);
    }

    @Test
    void simulateMatchWithVerticalWinner() {
        startNewGame();

        Status status1 = getStatus();
        Player[][] expectedGrid1 = {
                {null, null, null},
                {null, null, null},
                {null, null, null}};
        assertThat(status1.getGrid()).isEqualTo(expectedGrid1);
        assertThat(status1.getWinner()).isNull();
        assertThat(status1.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status2 = takeTurnAndGetStatus(1, 1, PLAYER_X);
        Player[][] expectedGrid2 = {
                {null, null,     null},
                {null, PLAYER_X, null},
                {null, null,     null}};
        assertThat(status2.getGrid()).isEqualTo(expectedGrid2);
        assertThat(status2.getWinner()).isNull();
        assertThat(status2.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status3 = takeTurnAndGetStatus(0, 0, PLAYER_O);
        Player[][] expectedGrid3 = {
                {PLAYER_O, null,     null},
                {null,     PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status3.getGrid()).isEqualTo(expectedGrid3);
        assertThat(status3.getWinner()).isNull();
        assertThat(status3.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status4 = takeTurnAndGetStatus(1, 0, PLAYER_X);
        Player[][] expectedGrid4 = {
                {PLAYER_O, PLAYER_X, null},
                {null,     PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status4.getGrid()).isEqualTo(expectedGrid4);
        assertThat(status4.getWinner()).isNull();
        assertThat(status4.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status5 = takeTurnAndGetStatus(0, 1, PLAYER_O);

        Player[][] expectedGrid5 = {
                {PLAYER_O, PLAYER_X, null},
                {PLAYER_O, PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status5.getGrid()).isEqualTo(expectedGrid5);
        assertThat(status5.getWinner()).isNull();
        assertThat(status5.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status6 = takeTurnAndGetStatus(1, 2, PLAYER_X);
        Player[][] expectedGrid6 = {
                {PLAYER_O, PLAYER_X, null},
                {PLAYER_O, PLAYER_X, null},
                {null,     PLAYER_X, null}};
        assertThat(status6.getGrid()).isEqualTo(expectedGrid6);
        assertThat(status6.getWinner()).isEqualTo(PLAYER_X);
        assertThat(status6.getNextPlayer()).isNull();
    }

    @Test
    void simulateMatchWithHorizontalWinner() {
        startNewGame();

        Status status1 = getStatus();
        Player[][] expectedGrid1 = {
                {null, null, null},
                {null, null, null},
                {null, null, null}};
        assertThat(status1.getGrid()).isEqualTo(expectedGrid1);
        assertThat(status1.getWinner()).isNull();
        assertThat(status1.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status2 = takeTurnAndGetStatus(1, 1, PLAYER_X);
        Player[][] expectedGrid2 = {
                {null, null,     null},
                {null, PLAYER_X, null},
                {null, null,     null}};
        assertThat(status2.getGrid()).isEqualTo(expectedGrid2);
        assertThat(status2.getWinner()).isNull();
        assertThat(status2.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status3 = takeTurnAndGetStatus(0, 0, PLAYER_O);

        Player[][] expectedGrid3 = {
                {PLAYER_O, null,     null},
                {null,     PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status3.getGrid()).isEqualTo(expectedGrid3);
        assertThat(status3.getWinner()).isNull();
        assertThat(status3.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status4 = takeTurnAndGetStatus(0, 1, PLAYER_X);
        Player[][] expectedGrid4 = {
                {PLAYER_O, null,     null},
                {PLAYER_X, PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status4.getGrid()).isEqualTo(expectedGrid4);
        assertThat(status4.getWinner()).isNull();
        assertThat(status4.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status5 = takeTurnAndGetStatus(1, 0, PLAYER_O);
        Player[][] expectedGrid5 = {
                {PLAYER_O, PLAYER_O, null},
                {PLAYER_X, PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status5.getGrid()).isEqualTo(expectedGrid5);
        assertThat(status5.getWinner()).isNull();
        assertThat(status5.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status6 = takeTurnAndGetStatus(2, 2, PLAYER_X);
        Player[][] expectedGrid6 = {
                {PLAYER_O, PLAYER_O, null},
                {PLAYER_X, PLAYER_X, null},
                {null,     null,     PLAYER_X}};
        assertThat(status6.getGrid()).isEqualTo(expectedGrid6);
        assertThat(status6.getWinner()).isNull();
        assertThat(status6.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status7 = takeTurnAndGetStatus(2, 0, PLAYER_O);
        Player[][] expectedGrid7 = {
                {PLAYER_O, PLAYER_O, PLAYER_O},
                {PLAYER_X, PLAYER_X, null},
                {null,     null,     PLAYER_X}};
        assertThat(status7.getGrid()).isEqualTo(expectedGrid7);
        assertThat(status7.getWinner()).isEqualTo(PLAYER_O);
        assertThat(status7.getNextPlayer()).isNull();
    }

    @Test
    void simulateMatchWithDiagonalWinner() {
        startNewGame();

        Status status1 = getStatus();
        Player[][] expectedGrid1 = {
                {null, null, null},
                {null, null, null},
                {null, null, null}};
        assertThat(status1.getGrid()).isEqualTo(expectedGrid1);
        assertThat(status1.getWinner()).isNull();
        assertThat(status1.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status2 = takeTurnAndGetStatus(1, 1, PLAYER_X);
        Player[][] expectedGrid2 = {
                {null, null,     null},
                {null, PLAYER_X, null},
                {null, null,     null}};
        assertThat(status2.getGrid()).isEqualTo(expectedGrid2);
        assertThat(status2.getWinner()).isNull();
        assertThat(status2.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status3 = takeTurnAndGetStatus(1, 0, PLAYER_O);
        Player[][] expectedGrid3 = {
                {null, PLAYER_O, null},
                {null, PLAYER_X, null},
                {null, null,     null}};
        assertThat(status3.getGrid()).isEqualTo(expectedGrid3);
        assertThat(status3.getWinner()).isNull();
        assertThat(status3.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status4 = takeTurnAndGetStatus(0, 0, PLAYER_X);
        Player[][] expectedGrid4 = {
                {PLAYER_X, PLAYER_O, null},
                {null,     PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status4.getGrid()).isEqualTo(expectedGrid4);
        assertThat(status4.getWinner()).isNull();
        assertThat(status4.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status5 = takeTurnAndGetStatus(0, 1, PLAYER_O);
        Player[][] expectedGrid5 = {
                {PLAYER_X, PLAYER_O, null},
                {PLAYER_O, PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status5.getGrid()).isEqualTo(expectedGrid5);
        assertThat(status5.getWinner()).isNull();
        assertThat(status5.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status6 = takeTurnAndGetStatus(2, 2, PLAYER_X);
        Player[][] expectedGrid6 = {
                {PLAYER_X, PLAYER_O, null},
                {PLAYER_O, PLAYER_X, null},
                {null,     null,     PLAYER_X}};
        assertThat(status6.getGrid()).isEqualTo(expectedGrid6);
        assertThat(status6.getWinner()).isEqualTo(PLAYER_X);
        assertThat(status6.getNextPlayer()).isNull();
    }

    @Test
    void simulateMatchWithoutWinner() {
        startNewGame();

        Status status1 = getStatus();
        Player[][] expectedGrid1 = {
                {null, null, null},
                {null, null, null},
                {null, null, null}};
        assertThat(status1.getGrid()).isEqualTo(expectedGrid1);
        assertThat(status1.getWinner()).isNull();
        assertThat(status1.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status2 = takeTurnAndGetStatus(1, 1, PLAYER_X);
        Player[][] expectedGrid2 = {
                {null, null,     null},
                {null, PLAYER_X, null},
                {null, null,     null}};
        assertThat(status2.getGrid()).isEqualTo(expectedGrid2);
        assertThat(status2.getWinner()).isNull();
        assertThat(status2.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status3 = takeTurnAndGetStatus(1, 0, PLAYER_O);
        Player[][] expectedGrid3 = {
                {null, PLAYER_O, null},
                {null, PLAYER_X, null},
                {null, null,     null}};
        assertThat(status3.getGrid()).isEqualTo(expectedGrid3);
        assertThat(status3.getWinner()).isNull();
        assertThat(status3.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status4 = takeTurnAndGetStatus(0, 0, PLAYER_X);
        Player[][] expectedGrid4 = {
                {PLAYER_X, PLAYER_O, null},
                {null,     PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status4.getGrid()).isEqualTo(expectedGrid4);
        assertThat(status4.getWinner()).isNull();
        assertThat(status4.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status5 = takeTurnAndGetStatus(0, 1, PLAYER_O);
        Player[][] expectedGrid5 = {
                {PLAYER_X, PLAYER_O, null},
                {PLAYER_O, PLAYER_X, null},
                {null,     null,     null}};
        assertThat(status5.getGrid()).isEqualTo(expectedGrid5);
        assertThat(status5.getWinner()).isNull();
        assertThat(status5.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status6 = takeTurnAndGetStatus(1, 2, PLAYER_X);
        Player[][] expectedGrid6 = {
                {PLAYER_X, PLAYER_O, null},
                {PLAYER_O, PLAYER_X, null},
                {null,     PLAYER_X, null}};
        assertThat(status6.getGrid()).isEqualTo(expectedGrid6);
        assertThat(status6.getWinner()).isNull();
        assertThat(status6.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status7 = takeTurnAndGetStatus(2, 2, PLAYER_O);
        Player[][] expectedGrid7 = {
                {PLAYER_X, PLAYER_O, null},
                {PLAYER_O, PLAYER_X, null},
                {null,     PLAYER_X, PLAYER_O}};
        assertThat(status7.getGrid()).isEqualTo(expectedGrid7);
        assertThat(status7.getWinner()).isNull();
        assertThat(status7.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status8 = takeTurnAndGetStatus(2, 0, PLAYER_X);
        Player[][] expectedGrid8 = {
                {PLAYER_X, PLAYER_O, PLAYER_X},
                {PLAYER_O, PLAYER_X, null},
                {null,     PLAYER_X, PLAYER_O}};
        assertThat(status8.getGrid()).isEqualTo(expectedGrid8);
        assertThat(status8.getWinner()).isNull();
        assertThat(status8.getNextPlayer()).isEqualTo(PLAYER_O);

        Status status9 = takeTurnAndGetStatus(0, 2, PLAYER_O);
        Player[][] expectedGrid9 = {
                {PLAYER_X, PLAYER_O, PLAYER_X},
                {PLAYER_O, PLAYER_X, null},
                {PLAYER_O, PLAYER_X, PLAYER_O}};
        assertThat(status9.getGrid()).isEqualTo(expectedGrid9);
        assertThat(status9.getWinner()).isNull();
        assertThat(status9.getNextPlayer()).isEqualTo(PLAYER_X);

        Status status10 = takeTurnAndGetStatus(2, 1, PLAYER_X);
        Player[][] expectedGrid10 = {
                {PLAYER_X, PLAYER_O, PLAYER_X},
                {PLAYER_O, PLAYER_X, PLAYER_X},
                {PLAYER_O, PLAYER_X, PLAYER_O}};
        assertThat(status10.getGrid()).isEqualTo(expectedGrid10);
        assertThat(status10.getWinner()).isNull();
        assertThat(status10.getNextPlayer()).isNull();
    }
}

