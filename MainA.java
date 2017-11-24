import java.util.*;
public class MainA{
	//shifting method
	//dlete line method

	/*
	public boolean collision(int x, int y){
		if(y==0||board[y][x]!=0){
			return true;
		}
	}
	*/
	public static final int[][] pieces = { // DO WE NEED THIS LIST TOO?
			{ 1, 1,0,2,0,3,0,4,0 },  // I in its default orientation.
			{ 2, 1,-1,1,0,1,1,2,0 }, // X in its only orientation.
			{ 3, 0,1,1,1,2,1,2,2 }, // Z in its default orientation
			{ 4, 1,0,2,0,2,1,2,2 }, // V in its default orientation
			{ 5, 0,1,0,2,1,1,2,1 }, // T in its default orientation
			{ 6, 1,0,1,1,2,1,2,2 }, // W in its default orientation
			{ 7, 0,2,1,0,1,1,1,2 }, // U in its default orientation
			{ 8, 1,0,2,0,3,0,3,1 }, // L in its default orientation
			{ 9, 1,-1,1,0,2,-1,3,-1 }, // N in its default orientation
			{ 10, 1,-1,1,0,2,0,3,0 }, // Y in its default orientation
			{ 11, 0,1,1,-1,1,0,2,0 }, // F in its default orientation
			{ 12, 0,1,1,0,1,1,2,0 } // P in its default orientation
		};

	public static void main(String[] args){
		int[][] board= new int[12][5];
		int row=0;
		int col=0;
		int rows=board.length;
		int cols=board[0].length;
		int p=0;
		boolean nope=false;
		for (int i = 1; i < 8; i += 2) {
			// i is the row, i+1 is the column
			if (row+pieces[p][i] < 0 || //if it is above the board
					row+pieces[p][i] >= rows || //if it is below the board
					col+pieces[p][i+1] < 0 || //if it is to the left of the board
					col+pieces[p][i+1] >= cols //if it is to the right of the board
					||board[row+pieces[p][i]][col+pieces[p][i+1]] != 0)  // checks if one of the squares needed is already occupied
			{nope=true;break;}
		}
		if(!nope){
			board[row][col] = pieces[p][0];
			for (int i = 1; i < 8; i += 2)
				board[row+pieces[p][i]][col+pieces[p][i+1]] = pieces[p][0];}
		System.out.print(Arrays.deepToString(board));
	}
}