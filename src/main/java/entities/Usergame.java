package entities;

import javax.persistence.*;

import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;


@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name = "Usergame", uniqueConstraints = {
        @UniqueConstraint(columnNames = "gameID"),
        @UniqueConstraint(columnNames = "userID")})
public class Usergame implements Serializable {

    public Usergame() {
    }


    @EmbeddedId
    private usergameID usergameid;

    @Column(name="win", nullable= false)
    private boolean win;


    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public usergameID getUsergameid() {
        return usergameid;
    }

    public void setUsergameid(usergameID usergameid) {
        this.usergameid = usergameid;
    }



    @Override
    public String toString(){
        return "GameID: "+usergameid.getGameID()+" userID: "+ usergameid.getUserID()+" win: "+isWin();
    }



}
