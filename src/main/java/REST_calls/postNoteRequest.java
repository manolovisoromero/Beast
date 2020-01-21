package REST_calls;

public class postNoteRequest {

    private String content;
    private int userID;

    public postNoteRequest() {
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
