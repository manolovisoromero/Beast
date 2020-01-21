package entities;


import javax.persistence.*;

import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    public List<Usergame> getUsergames() {
        return usergames;
    }

    public void setUsergames(List<Usergame> usergames) {
        this.usergames = usergames;
    }

    @OneToMany(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Usergame> usergames = new ArrayList<>();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameXY, game.getGameXY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameXY);
    }


}
