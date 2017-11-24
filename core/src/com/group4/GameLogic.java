package com.group4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

import static com.group4.Constants.COLS;
import static com.group4.Constants.ROWS;
import static com.group4.GameScreen.game;

/**
 * Created by Tobias on 07/11/2017.
 */

public abstract class GameLogic {
    int[] piece=new int[9];
    int[][] board= new int[ROWS][COLS];
    private int maxRow=0;
    private int maxCol=0;
    private int minCol=0;
    private Pentomino pentomino1;
    private int r;
    private int p;
    private Pentomino pentomino2;
    private Pentomino pentomino;
    private boolean run=true;

    private static Pentomino[] pentos=new Pentomino[2];
    private static int highscore;
    private static int[][] listOfClumps = new int[ROWS][COLS];
    private static int clumpNumber;
    private static int fullLines;

    public GameLogic() {}

    public boolean init() {
        if(pentos[0]==null){
            p=(int) (Math.random()*11);
            pentos[0]=pentomino1=new Pentomino(p,0);
            p=(int) (Math.random()*11);
            pentos[1]=pentomino2=new Pentomino(p,0);}
        else{
            pentos[0]=pentos[1];
            p=(int) (Math.random()*11);
            pentos[1]=pentomino1=new Pentomino(p,0);
        }
        pentomino=pentos[0];
        if(!pentomino.initPento())
            return false;
        aimDrop();
        board=pentomino.getBoard();
        Gdx.input.setInputProcessor(pentomino);
        return true;
    }

    public int[] displayNext() {
        return pentos[1].getPiece();
    }
    public void addScore(int n){
        highscore+=n*(n*100);
    }
    public int getScore(){
        return highscore;
    }

    public void remove() {
        pentomino.removePosition();
    }

    public boolean getRun(){
        return run;
    }
    public void setRun(boolean run){
        this.run=run;
    }

    public void reset() {
        run=false;
        highscore=0;
        //pentos[0].resetPento();
        listOfClumps = new int[ROWS][COLS];
        pentos=new Pentomino[2];
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.setScreen(new GameScreen(game));
    }

    public void aimDrop() {
        pentomino.aimDrop();
        pentomino.drawAim();
    }


    public void moveRight() {
        pentomino.movePentominoLeft();
        aimDrop();
    }

    public void moveLeft() {
        pentomino.movePentominoRight();
        aimDrop();
    }

    public boolean fall() {
        return pentomino.fall();
    }
    public int[][] getBoard(){
        return board;
    }
    /**
     Removes completely filled horizontal lines of the board.
     @param : the number of cells of that row that is occupied by a pentomino
     (@param r: the current row of the board
     @param: the current column of the board)?
     */
    public void removeLine(){
        int cellsFull;
        int r = ROWS-1; //lowest row of the board
        fullLines=0;
        do{
            cellsFull=0;
            for (int c=0; c<COLS; c++)
                if (board[r][c]!=0)
                    cellsFull++;
            if (cellsFull==COLS){
                fullLines++;
                moveBoard(r);
                r++;
            }r--; //next check row above
        }while(cellsFull!=0 && r>=0); //break when a row is complete empty: no full row can come above
        if(fullLines>0){
            identifyClumps();
            //realisticFall();
            ArrayList<Integer> possibleClumps=new ArrayList<Integer>();
            for(int i=1;i<=clumpNumber;i++)
                possibleClumps.add(i);
            realisticFall(possibleClumps);
            addScore(fullLines);}
    }

    public void realisticFall(ArrayList<Integer> possibleClumps) {
        if(possibleClumps.size()>0){
            ArrayList<Integer> newPossible= new ArrayList<Integer>();
            for(Integer i:possibleClumps) {
                newPossible.add(i);
                outerloop:
                for (int c = 0; c < COLS; c++)
                    for (int r = ROWS - 1; r >= 0; r--)
                        if (listOfClumps[r][c] == i)
                            if (r != ROWS - 1)
                                if (board[r + 1][c] > 0) {
                                    newPossible.remove(i);
                                    break outerloop;
                                } else {
                                    break;
                                }
                            else {
                                newPossible.remove(i);
                                break outerloop;
                            }
            }
            System.out.println(possibleClumps);
            System.out.println(newPossible);
            for(Integer i:newPossible){
                for(int r=ROWS-1;r>=0;r--)
                    for (int c=0; c<COLS; c++)
                        if (listOfClumps[r][c]==i){
                            board[r+1][c]=board[r][c];
                            board[r][c]=0;
                            listOfClumps[r+1][c]=listOfClumps[r][c];
                            listOfClumps[r][c]=0;
                        }
                }
            realisticFall(newPossible);
        }
    }

    public void identifyClumps() {
        clumpNumber=0;
        for(int i=ROWS-1;i>=0;i--)
            for(int j=0;j<COLS;j++)
                if(board[i][j]>0)
                    if(!inClumps(i,j)){
                        clumpNumber++;
                        identifyClump(j,i,clumpNumber);}
    }

    private void identifyClump(int col,int row,int piece) {
        if(row>=0 && row<ROWS && col>=0 && col<COLS){
            // if (col,row) has already been checked, do nothing
            if (listOfClumps[row][col]!=0||board[row][col]<=0){
                return;}
            else {
                listOfClumps[row][col]=piece;
                identifyClump(col+1,row,piece);
                identifyClump(col-1,row,piece);
                identifyClump(col,row-1,piece);
                identifyClump(col,row+1,piece);
                return;
            }
        }
        else
            return;
    }

    private boolean inClumps(int i,int j) {
        if(listOfClumps[i][j]>0)
                return true;
        return false;
    }

    /**
     Moves all pentominoes in the board above the row to remove one line down.
     @param remove: the line that should be removed
     (@param r: the current row of the board
     @param: the current column of the board)?
     */
    public void moveBoard(int remove){
        for (int r=remove; r>=0; r--)
            for (int c=0; c<board[r].length; c++){
                if (r==0) //row 0 is not replaced by the row above (there is no row above), but is a new empty row
                    board[r][c]=0;
                else
                    board[r][c]=board[r-1][c]; //replace row with row above
            }
    }

    public int getFullLines(){
        return fullLines;
    }

    public String toString(int x){
        return ""+x;
    }
}
