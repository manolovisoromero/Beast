package entities;


import javax.persistence.*;

import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userID")})
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userID", nullable = false)
    private int userID;

    @Column(name="username", nullable = false, length = 20)
    private String username;

    @Column(name="password", nullable = false, length = 200)
    private String password;

    @Column(name="token", nullable = true, length = 20)
    private String token;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Usergame> usergames = new ArrayList<>();

    public void addGame(Game game){
        Usergame usergame = new Usergame(this,game);
        usergames.add(usergame);
        game.getUsergames().add(usergame);
    }

    public void removeGame(Game game){
        for (Iterator<Usergame> iterator = usergames.iterator(); iterator.hasNext();){
            Usergame usergame = iterator.next();

            if(usergame.getUser().equals(this) && usergame.getGame().equals(game)){
                iterator.remove();
                usergame.getGame().getUsergames().remove(usergame);
                usergame.setUser(null);
                usergame.setGame(null);
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;
        return Objects.equals(username, user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
