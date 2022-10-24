package com.nct.tictactoe.integration;

import com.nct.tictactoe.dto.Status;
import com.nct.tictactoe.enumeration.Player;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.nct.tictactoe.enumeration.Player.PLAYER_O;
import static com.nct.tictactoe.enumeration.Player.PLAYER_X;
import static org.assertj.core.api.Assertions.assertThat;

class ValidationTests extends TestBase {

    @Test
    void validJsonIsSupported() {
        startNewGame();
        String json = "{\"horizontalPosition\":1,\"verticalPosition\":2,\"player\":\"PLAYER_X\"}";
        ResponseEntity<Void> response = takeTurnWithJson(json);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void invalidPlayerIsDetected() {
        startNewGame();
        String json = "{\"horizontalPosition\":1,\"verticalPosition\":2,\"player\":\"PLAYER_Z\"}";
        ResponseEntity<Void> response = takeTurnWithJson(json);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void missingPlayerIsDetected() {
        startNewGame();
        String json = "{\"horizontalPosition\":1,\"verticalPosition\":2}";
        ResponseEntity<Void> response = takeTurnWithJson(json);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void statusBadRequestIsReturnedWhenHorizontalPositionIsTooHigh() {
        startNewGame();
        String json = "{\"horizontalPosition\":3,\"verticalPosition\":2,\"player\":\"PLAYER_X\"}";
        ResponseEntity<Void> response = takeTurnWithJson(json);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void statusBadRequestIsReturnedWhenHorizontalPositionIsTooLow() {
        startNewGame();
        String json = "{\"horizontalPosition\":-1,\"verticalPosition\":2,\"player\":\"PLAYER_X\"}";
        ResponseEntity<Void> response = takeTurnWithJson(json);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void statusBadRequestIsReturnedWhenVerticalPositionIsTooHigh() {
        startNewGame();
        String json = "{\"horizontalPosition\":1,\"verticalPosition\":3,\"player\":\"PLAYER_X\"}";
        ResponseEntity<Void> response = takeTurnWithJson(json);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void statusBadRequestIsReturnedWhenVerticalPositionIsTooLow() {
        startNewGame();
        String json = "{\"horizontalPosition\":1,\"verticalPosition\":-1,\"player\":\"PLAYER_X\"}";
        ResponseEntity<Void> response = takeTurnWithJson(json);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void statusBadRequestIsReturnedWhenAFieldIsMarkedTwice() {
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
                {null, null, null},
                {null, PLAYER_X, null},
                {null, null, null}};
        assertThat(status2.getGrid()).isEqualTo(expectedGrid2);
        assertThat(status2.getWinner()).isNull();
        assertThat(status2.getNextPlayer()).isEqualTo(PLAYER_O);

        ResponseEntity<Void> response = takeTurnAndGetResponse(1, 1, PLAYER_O);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void statusBadRequestIsReturnedWhenTheSamePlayerMakesTwoTurnsInARow() {
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
                {null, null, null},
                {null, PLAYER_X, null},
                {null, null, null}};
        assertThat(status2.getGrid()).isEqualTo(expectedGrid2);
        assertThat(status2.getWinner()).isNull();
        assertThat(status2.getNextPlayer()).isEqualTo(PLAYER_O);

        ResponseEntity<Void> response = takeTurnAndGetResponse(1, 0, PLAYER_X);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
