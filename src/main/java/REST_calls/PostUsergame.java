package REST_calls;

public class PostUsergame {

    public PostUsergame() {
    }

    public boolean[][] getPlayerInput() {
        return playerInput;
    }

    public void setPlayerInput(boolean[][] playerInput) {
        this.playerInput = playerInput;
    }

    public boolean[][] getGameField() {
        return gameField;
    }

    public void setGameField(boolean[][] gameField) {
        this.gameField = gameField;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    private boolean [][] playerInput;
    private boolean [][] gameField;
    private int userID;

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    private int gameID;

}
