# Tic Tac Toe

## API Documentation

After starting the app locally, the full documentation can be found at
http://localhost:8080/swagger-ui/index.html

## Sample curl calls for all endpoints

### Start a new game

```
curl -X PUT 'http://localhost:8080/newGame'
```

### Get the status of the game

```
curl -X GET 'http://localhost:8080/status'
```

### Take a turn

```
curl -X POST 'http://localhost:8080/takeTurn' \
--header 'Content-Type: application/json' \
--data-raw '{
"horizontalPosition": 1,
"verticalPosition": 2,
"player": "PLAYER_X"
}'
```