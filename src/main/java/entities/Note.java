package entities;


import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name = "note", uniqueConstraints = {
        @UniqueConstraint(columnNames = "noteID")})
public class Note {


    public Note(){}

    public Note(int noteID, String content, int userID){
        this.content = content;
        this.noteID = noteID;
        this.userID = userID;

    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="noteID", nullable = false)
    private int noteID;

    @Column(name="content", nullable = false, length = 100)
    private String content;

    @Column(name="userID", nullable = false, length = 10)
    private int userID;


    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
