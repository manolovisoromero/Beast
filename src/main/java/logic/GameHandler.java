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

    public GameField createLabels(boolean[][] game){
        GameField gameField = new GameField();
        gameField.setLeftLabels( leftLabels(game));
        gameField.setUpperLabels(upperLabels(game));

        return gameField;

    }

    public int [][] leftLabels(boolean [][] game){
        int [][] left = new int [5][5];
        int counter = 0;

        for(int i = 0; i < 5; i++){
            for(int j = 4; j >= 0;j--){
                if(game[i][j]){
                    counter++;
                }else{
                    for(int a = 4; a >= 0; a--){
                        if(left[i][a] == 0 ){
                            left[i][a] = counter;
                            counter = 0;
                        }
                    }
                    left[i][j] = counter;
                    counter = 0;
                }
                if(j == 0 && counter > 0){
                    for(int a = 4; a >= 0; a--){
                        if(left[i][a] == 0 ){
                            left[i][a] = counter;
                            counter = 0;
                        }
                    }
                }
            }
        }


        return left;
    }

    private int [][] upperLabels(boolean [][]game){
        int [][] upper = new int [5][5];
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
        return upper;
    }
}
