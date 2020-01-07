package logic;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Assert;

import java.util.Arrays;


class GameHandlerTest {

    GameHandler gameHandler = GameHandler.getInstance();


    @BeforeEach
     void setUp(){
        gameHandler.createLabels(game);
        gameHandler.leftLabels(game);
    }

    private boolean [][] game = new boolean[][]{
            new boolean[]{true,true,false,false,false},
            new boolean[]{true,true,false,true,true},
            new boolean[]{false,true,false,true,false},
            new boolean[]{true,false,true,false,false},
            new boolean[]{true,false,false,false,true}
    };


    @Test
    void create_Upper_Labels() {

        //Arrange
        int [][] expected = new int[][]{
                new int[]{0,0,0,0,0},
                new int[]{0,0,0,0,0},
                new int[]{0,0,0,0,0},
                new int[]{2,0,0,0,1},
                new int[]{2,3,1,2,1},
        };

        //Act
        int [][] actual = gameHandler.gameField.getUpperLabels();


        //Assert
        System.out.println("Expected: \n");
        System.out.println(Arrays.deepToString(expected).replace("], ", "]\n"));
        System.out.println("Actual: \n");
        System.out.println(Arrays.deepToString(actual).replace("], ", "]\n"));
        Assert.assertEquals(expected,gameHandler.gameField.getUpperLabels());


    }

    @Test
    void create_Left_Labels() {
        //Arrange
        int [][] actual = gameHandler.gameField.getLeftLabels();

        //Act
        int [][] expected = new int[][]{
                new int[]{0,0,0,0,2},
                new int[]{0,0,0,2,2},
                new int[]{0,0,0,1,1},
                new int[]{0,0,0,1,1},
                new int[]{0,0,0,1,1},
        };

        //Assert
        System.out.println("Expected: \n");
        System.out.println(Arrays.deepToString(expected).replace("], ", "]\n"));
        System.out.println("Actual: \n");
        System.out.println(Arrays.deepToString(actual).replace("], ", "]\n"));
        Assert.assertEquals(expected,actual);

    }



}
