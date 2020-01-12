package entities;


import javax.persistence.*;

import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;


@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name = "game", uniqueConstraints = {
        @UniqueConstraint(columnNames = "gameID"),
        @UniqueConstraint(columnNames = "x"),
        @UniqueConstraint(columnNames = "y"),

})
public class Game implements Serializable {


    public Game(GameXY gameXY, boolean value) {
        this.gameXY = gameXY;
        this.value = value;
    }

    public Game() {
    }

    public GameXY getGameXY() {
        return gameXY;
    }

    public void setGameXY(GameXY gameXY) {
        this.gameXY = gameXY;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @EmbeddedId
    private GameXY gameXY;

    @Column(name = "value",  nullable = false, length = 1)
    private boolean value;




}
