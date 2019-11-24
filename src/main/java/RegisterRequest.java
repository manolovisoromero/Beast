public class RegisterRequest {

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        username = Username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    private String username, password;

    @Override
    public String toString(){
        return "Username: "+ this.username+", password: "+this.password;
    }
}
