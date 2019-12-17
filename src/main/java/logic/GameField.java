package logic;

public class GameField {



    private boolean[][] Playfield = new boolean[5][5];
    private int[][] UpperLabels = new int[5][5];
    private int[][] LeftLabels = new int[5][5];


    public boolean[][] getPlayfield() {
        return Playfield;
    }

    public void setPlayfield(boolean[][] playfield) {
        Playfield = playfield;
    }

    public int[][] getUpperLabels() {
        return UpperLabels;
    }

    public void setUpperLabels(int[][] upperLabels) {
        UpperLabels = upperLabels;
    }

    public int[][] getLeftLabels() {
        return LeftLabels;
    }

    public void setLeftLabels(int[][] leftLabels) {
        LeftLabels = leftLabels;
    }
    
}
