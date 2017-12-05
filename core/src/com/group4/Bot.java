package com.group4;

/**
 * Created by Tobias on 01/12/2017.
 */

public class Bot extends GameLogic{
    private Pentomino pentomino;

    public Bot (Pentomino pentomino){
        this.pentomino=pentomino;
    }

    public void playBot(){
        pentomino.removeAim();
        while(pentomino.movePentominoRight()){
            pentomino.aimDrop();
            pentomino.drawAim();
            pentomino.removeAim();}
        pentomino.aimDrop();
        pentomino.drawAim();
        pentomino.removeAim();
        while(pentomino.movePentominoLeft()){
            pentomino.aimDrop();
            pentomino.drawAim();
            pentomino.removeAim();}
        pentomino.aimDrop();
        pentomino.drawAim();
        pentomino.removeAim();
        pentomino.rotate();
        pentomino.aimDrop();
        pentomino.drawAim();
        pentomino.removeAim();
        while(pentomino.movePentominoRight()){
            pentomino.aimDrop();
            pentomino.drawAim();
            pentomino.removeAim();}
        pentomino.aimDrop();
        pentomino.drawAim();
    }
}
