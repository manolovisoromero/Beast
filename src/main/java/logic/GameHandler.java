package logic;

import entities.User;

public class GameHandler {

    private User user;

    public GameField gameField;



    //Singleton
    private static GameHandler gameHandler = new GameHandler();
    private GameHandler(){}
    public static GameHandler getInstance(){
        return gameHandler;
    }


    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public void createLabels(boolean[][] game){
        int [][] upper = new int[5][5];
        int [][] left = new int [5][5];
        int counter = 0;

        for(int j = 0; j < 5; j++){
            for(int i = 4; i >= 0;i--){
                if(game[i][j]){
                    counter++;
                }else{
                    for(int a = 4; a >= 0; a--){
                        if(upper[a][j] == 0 ){
                            upper[a][j] = counter;
                            counter = 0;
                        }
                    }
                    upper[i][j] = counter;
                    counter = 0;
                }
                if(i == 0 && counter > 0){
                    for(int a = 4; a >= 0; a--){
                        if(upper[a][j] == 0 ){
                            upper[a][j] = counter;
                            counter = 0;
                        }

                    }

                }
        }
        }
        gameField = new GameField();
        gameField.setUpperLabels(upper);
    }
}
