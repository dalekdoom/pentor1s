/*
package com.group4;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.group4.Constants.cols;
import static com.group4.Constants.pieces;
import static com.group4.Constants.rows;


public class TetrisGame extends Game {
	Pentominos pentominos;
	ShapeRenderer shapeRenderer;
	private int row=8;
	private int col=2;
	private int dif=0;
	//private long lastTime=0;
	//private long time;
	private int[][] board=new int[rows][cols];
	private Pentomino pentomino;

	@Override
	public void create () {
		//Gdx.graphics.setContinuousRendering(false);
		shapeRenderer = new ShapeRenderer();
		pentominos= new Pentominos();
		board=pentominos.getBoard();
		setScreen(new GameScreen());
	}

	public void run(){
		boolean dead = false;
		while(!dead) {
			//time = System.currentTimeMillis();
			//if (time - lastTime >= 1000) {
			//	lastTime = System.currentTimeMillis();
			//}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Then we start our shapeRenderer batch, this time with ShapeType.Line
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		// A Simple white line
		int width= Gdx.graphics.getWidth();
		int height= Gdx.graphics.getHeight();
		for(int i=1;i<12;i++){
			shapeRenderer.setColor(Color.BLUE);
			shapeRenderer.line(0, i*(height/12), width, i*(height/12));}
		for(int i=1;i<5;i++){
			shapeRenderer.line( i*(width/5),0, i*(width/5),height);}
		// The last interesting thing we can do is draw a bunch of connected line segments using polyline
		// First we set up the list of vertices, where the even positions are x coordinates, and the odd positions are the y coordinate
		shapeRenderer.end();
		row--;
		dif = 0;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if (col < 4)
				col++;
			dif++;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if (col > 0)
				col--;
			dif--;
		}
		if(pentomino.move(row,col,dif))
			for(int i=0;i<board.length-1;i++){
				for(int j=0;j<board[0].length-1;j++){
					if(board[i][j]!=0){
						shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
						shapeRenderer.setColor(Color.ROYAL);
						shapeRenderer.rect(j*(width/5), i*(height/12), (width/5),(height/12));
						shapeRenderer.end();
					}
				}
			}
		else{row=8;col=2;pentomino= new Pentomino((int)(Math.random()*12));}
	}

	public boolean collision(int x, int y){
		if(y<=0||board[y][x]!=0)
			return true;
		return false;
	}

	public void remove(int p) {
		board[row][col] = 0;
		for (int i = 1; i < 8; i += 2) {
			board[row + pieces[p][i]][col + pieces[p][i + 1]] = 0;
		}
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
		super.dispose();
	}
}
*/