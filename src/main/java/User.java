

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "Username", unique = true)
    private String username;

    @Column(name = "Password", nullable = false)
    private String password;



    public User(String username, String password){
        this.username = username;
        this.password = password;

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








}
