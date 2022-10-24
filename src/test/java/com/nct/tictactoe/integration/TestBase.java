package com.nct.tictactoe.integration;

import com.nct.tictactoe.dto.Status;
import com.nct.tictactoe.dto.Turn;
import com.nct.tictactoe.enumeration.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestBase {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    void startNewGame() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/newGame", HttpMethod.PUT, HttpEntity.EMPTY, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    ResponseEntity<Void> takeTurnWithJson(String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        return restTemplate.postForEntity("http://localhost:" + port + "/takeTurn", request, Void.class);
    }

    Status takeTurnAndGetStatus(int horizontalPosition, int verticalPosition, Player player) {
        takeTurn(horizontalPosition, verticalPosition, player);
        return getStatus();
    }

    void takeTurn(int horizontalPosition, int verticalPosition, Player player) {
        Turn turn = Turn.builder().horizontalPosition(horizontalPosition).verticalPosition(verticalPosition).player(player).build();
        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:" + port + "/takeTurn", turn, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    ResponseEntity<Void> takeTurnAndGetResponse(int horizontalPosition, int verticalPosition, Player player) {
        Turn turn = Turn.builder().horizontalPosition(horizontalPosition).verticalPosition(verticalPosition).player(player).build();
        return restTemplate.postForEntity("http://localhost:" + port + "/takeTurn", turn, Void.class);
    }

    Status getStatus() {
        ResponseEntity<Status> response = restTemplate.getForEntity("http://localhost:" + port + "/status",
                Status.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        return response.getBody();
    }

}
