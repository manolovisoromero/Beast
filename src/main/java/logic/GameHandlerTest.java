package logic;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.Assert;

import java.util.Arrays;


class GameHandlerTest {

    GameHandler gameHandler = GameHandler.getInstance();

    private boolean [][] game = new boolean[][]{
            new boolean[]{true,true,false,false,false},
            new boolean[]{true,true,false,true,true},
            new boolean[]{false,true,false,true,false},
            new boolean[]{true,false,true,false,false},
            new boolean[]{true,false,false,false,true}
    };




    public void printer(int[][] expected, int [][]actual){
        System.out.println("Expected: \n");
        System.out.println(Arrays.deepToString(expected).replace("], ", "]\n"));
        System.out.println("Actual: \n");
        System.out.println(Arrays.deepToString(actual).replace("], ", "]\n"));
    }



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
        int [][] actual = gameHandler.upperLabels(game);


        //Assert
        printer(expected,actual);
        Assert.assertEquals(expected,actual);


    }

    @Test
    void create_Left_Labels() {
        //Arrange
        int [][] actual = gameHandler.leftLabels(game);

        //Act
        int [][] expected = new int[][]{
                new int[]{0,0,0,0,2},
                new int[]{0,0,0,2,2},
                new int[]{0,0,0,1,1},
                new int[]{0,0,0,1,1},
                new int[]{0,0,0,1,1},
        };

        //Assert
        printer(expected,actual);
        Assert.assertEquals(expected,actual);

    }



}
