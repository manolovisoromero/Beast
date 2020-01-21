package entities;

import javax.persistence.*;

import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import java.util.Objects;


@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name = "Usergame"
//        , uniqueConstraints = {
//        @UniqueConstraint(columnNames = "gameID"),
//        @UniqueConstraint(columnNames = "userID")}
        )
public class Usergame implements Serializable {

    public Usergame() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @MapsId("userID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameID")
    private Game game;

    public Usergame(User user, Game game){
        this.user = user;
        this.game = game;
        this.usergameid = new usergameID(user.getUserID(),game.getGameXY().getGameID());
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



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Usergame that = (Usergame) o;
        return Objects.equals(game, that.game) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, user);
    }



}
