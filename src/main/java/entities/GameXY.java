package entities;

import javax.persistence.*;

import java.io.Serializable;

@Embeddable
public class GameXY implements Serializable{




    @Column(name = "gameID", nullable = false)
    private int gameID;

    @Column(name = "x", nullable = false)
    private int x;

    @Column(name = "y", nullable = false)
    private int y;




    public GameXY(){
    }

    public GameXY(int gameID, int x, int y) {
        this.gameID = gameID;
        this.x = x;
        this.y = y;
    }


    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }








}
