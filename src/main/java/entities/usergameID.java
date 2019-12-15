package entities;

import javax.persistence.*;

import java.io.Serializable;

@Embeddable
public class usergameID implements Serializable{


    @Column(name = "userID", nullable = false)
    private int userID;

    @Column(name = "gameID", nullable = false)
    private int gameID;

    public usergameID(){
    }

    public usergameID(int userID, int gameID){
        this.gameID = gameID;
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }






}
