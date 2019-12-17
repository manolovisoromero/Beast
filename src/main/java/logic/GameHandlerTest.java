package logic;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

import java.util.Arrays;


class GameHandlerTest {

    GameHandler gameHandler = GameHandler.getInstance();

    @Test
    void createLabels() {

        boolean [][] game = new boolean[][]{
                new boolean[]{true,true,false,false,false},
                new boolean[]{true,true,false,true,true},
                new boolean[]{false,true,false,true,false},
                new boolean[]{true,false,true,false,false},
                new boolean[]{true,false,false,false,true}
        };

        int [][] expected = new int[][]{
                new int[]{0,0,0,0,0},
                new int[]{0,0,0,0,0},
                new int[]{0,0,0,0,0},
                new int[]{2,0,0,0,1},
                new int[]{2,3,1,2,1},
        };

        gameHandler.createLabels(game);

        System.out.println(Arrays.deepToString(expected));
        System.out.println(Arrays.deepToString(gameHandler.gameField.getUpperLabels()));
        Assert.assertEquals(expected,gameHandler.gameField.getUpperLabels());


    }



}
