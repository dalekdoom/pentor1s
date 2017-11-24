import java.util.*;
public class Pentominoes2 {

	private static int rotation=0;

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
	
	public static int[][] rotations(int piece){
		if (piece ==1){
			int[][] rotations = {
			{ 1, 1,0,2,0,3,0,4,0 },  // I in its default orientation.
			{ 1, 0,1,0,2,0,3,0,4 },  // Describes piece 1 (the "I" pentomino) in its horizontal orientation.
			};
			return rotations;
		}
		else if (piece ==2){
			int[][] rotations = {
			{ 2, 1,-1,1,0,1,1,2,0 }, // X in its only orientation.
			};
			return rotations;
		}
		else if (piece ==3){
			int[][] rotations = {
			{ 3, 0,1,1,1,2,1,2,2 }, // Z in its default orientation
			{ 3, 0,1,1,0,2,-1,2,0 }, // Z 90 degrees rotated to the right
			
			{ 3, 1,-2,1,-1,1,0,2,-2 }, // Z reflected
			{ 3, 1,0,1,1,1,2,2,2 }, // Z 90 degrees rotated to the right and reflected
			};
			return rotations;
		}
		else if (piece ==4){
			int[][] rotations = {
			{ 4, 1,0,2,0,2,1,2,2 }, // V in its default orientation
			{ 4, 0,1,0,2,1,0,2,0 }, // V 90 degrees rotated to the right
			{ 4, 0,1,0,2,1,2,2,2 }, // V 180 degrees rotated
			{ 4, 1,0,2,-2,2,-1,2,0 }, // V 90 degrees rotated to the left
			};
			return rotations;
		}
		else if (piece ==5){
			int[][] rotations = {
			{ 5, 0,1,0,2,1,1,2,1 }, // T in its default orientation
			{ 5, 1,-2,1,-1,1,0,2,0 }, // T 90 degrees rotated to the right
			{ 5, 1,0,2,-1,2,0,2,1 }, // T 180 degrees rotated
			{ 5, 1,0,1,1,1,2,2,0 }, // T 90 degrees rotated to the left
			};
			return rotations;
		}
		else if (piece ==6){
			int[][] rotations = {
			{ 6, 1,0,1,1,2,1,2,2 }, // W in its default orientation
			{ 6, 0,1,1,-1,1,0,2,-1 }, // W 90 degrees rotated to the right
			{ 6, 0,1,1,1,1,2,2,2 }, // W 180 degrees rotated
			{ 6, 1,-1,1,0,2,-2,2,-1 }, // W 90 degrees rotated to the left
			};
			return rotations;
		}
		else if (piece ==7){
			int[][] rotations = {
			{ 7, 0,2,1,0,1,1,1,2 }, // U in its default orientation
			{ 7, 0,1,1,0,2,0,2,1 }, // U 90 degrees rotated to the right
			{ 7, 0,1,0,2,1,0,1,2 }, // U 180 degrees rotated
			{ 7, 0,1,1,1,2,0,2,1 }, // U 90 degrees rotated to the left
			};
			return rotations;
		}
		else if (piece ==8){
			int[][] rotations = {
			{ 8, 1,0,2,0,3,0,3,1 }, // L in its default orientation
			{ 8, 0,1,0,2,0,3,1,0 }, // L 90 degrees rotated to the right
			{ 8, 0,1,1,1,2,1,3,1 }, // L 180 degrees rotated
			{ 8, 1,-3,1,-2,1,-1,1,0 }, // L 90 degrees rotated to the left
			
			{ 8, 1,0,1,1,1,2,1,3 }, // L 90 degrees rotated to the right and reflected horizontally
			{ 8, 1,0,2,0,3,-1,3,0 }, // L reflected vertically
			{ 8, 0,1,0,2,0,3,1,3 }, // L 90 degrees rotated to the left and reflected horizontally
			{ 8, 0,1,1,0,2,0,3,0 }, // L reflected horizontally
			};
			return rotations;
		}
		else if (piece ==9){
			int[][] rotations = {
			{ 9, 1,-1,1,0,2,-1,3,-1 }, // N in its default orientation
			{ 9, 0,1,0,2,1,2,1,3 }, // N 90 degrees rotated to the right
			{ 9, 1,0,2,-1,2,0,3,-1 }, // N 180 degrees rotated
			{ 9, 0,1,1,1,1,2,1,3 }, // N 90 degrees rotated to the left
			
			{ 9, 0,1,1,-2,1,-1,1,0 }, // N 90 degrees rotated to the right and reflected horizontally
			{ 9, 1,0,1,1,2,1,3,1 }, // N reflected vertically
			{ 9, 0,1,0,2,1,-1,1,0 }, // N 90 degrees rotated to the left and reflected horizontally
			{ 9, 1,0,2,0,2,1,3,1 }, // N 180 degrees rotated and reflected vertically
			};
			return rotations;
		}
		else if (piece ==10){
			int[][] rotations = {
			{ 10, 1,-1,1,0,2,0,3,0 }, // Y in its default orientation
			{ 10, 1,-2,1,-1,1,0,1,1 }, // Y 90 degrees rotated to the right
			{ 10, 1,0,2,0,2,1,3,0 }, // Y 180 degrees rotated
			{ 10, 0,1,0,2,0,3,1,1 }, // Y 90 degrees rotated to the left
			
			{ 10, 0,1,0,2,0,3,1,2 }, // Y 90 degrees rotated to the right and reflected horizontally
			{ 10, 1,0,1,1,2,0,3,0 }, // Y reflected vertically
			{ 10, 1,-1,1,0,1,1,1,2 }, // Y 90 degrees rotated to the left and reflected horizontally
			{ 10, 1,0,2,-1,2,0,3,0 }, // Y reflected horizontally
			};
			return rotations;
		}
		else if (piece ==11){
			int[][] rotations = {
			{ 11, 0,1,1,-1,1,0,2,0 }, // F in its default orientation
			{ 11, 1,-1,1,0,1,1,2,1 }, // F 90 degrees rotated to the right
			{ 11, 1,0,1,1,2,-1,2,0 }, // F 180 degrees rotated
			{ 11, 1,0,1,1,1,2,2,1 }, // F 90 degrees rotated to the left
			
			{ 11, 1,-2,1,-1,1,0,2,-1 }, // F 90 degrees rotated to the right and reflected horizontally
			{ 11, 0,1,1,1,1,2,2,1 }, // F reflected vertically
			{ 11, 1,-1,1,0,1,1,2,-1 }, // F 90 degrees rotated to the left and reflected horizontally
			{ 11, 1,-1,1,0,2,0,2,1 }, // F 180 degrees rotated and reflected vertically
    		};
    		return rotations;
		}
		else {
			int[][] rotations = {
			{ 12, 0,1,1,0,1,1,2,0 }, // P in its default orientation
			{ 12, 0,1,0,2,1,1,1,2 }, // P 90 degrees rotated to the right
			{ 12, 1,-1,1,0,2,-1,2,0 }, // P 180 degrees rotated
			{ 12, 0,1,1,0,1,1,1,2 }, // P 90 degrees rotated to the left
			
			{ 12, 0,1,1,0,1,1,2,1 }, // P reflected vertically
		    { 12, 0,1,0,2,1,0,1,1 }, // P 90 degrees rotated to the left and reflected horizontally
			{ 12, 1,0,1,1,2,0,2,1 }, // P 180 degrees rotated and reflected vertically
			{ 12, 0,1,1,-1,1,0,1,1 }, // P 90 degrees rotated to the right and reflected horizontally
			};
			return rotations;
		}		
	}
	
	public static void main(String[] args){
		//GENERATE RANDOM NUMBER [1, 12] AND THIS IS PIECE
		int piece = (int) (Math.random()*12 +1);
		System.out.println(Arrays.deepToString(rotations(4)));
		System.out.println(Arrays.toString(rotate(4, false)));
	}

	public static int[] rotate(int piece, boolean up){
		if(up){
			return rotations(piece)[Math.abs(++rotation)%rotations(piece).length];
		}
		return rotations(piece)[Math.abs(--rotation)%rotations(piece).length];
	}
}	