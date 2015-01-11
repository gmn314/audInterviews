package com.gmn.interview;

/*
 * Given an NxN matrix, rotate the outer ring of values by 90 degrees
 * ** What to watch out for ** 
 * - Remember, when using n as indices, it should be n-1!!
 * - Note how the numbers that were shifted off of each side are added back using [n-1][n-2]
 * - When rotating 90, iterate from - to matrix[0].length-1 !!
 */
public class RotateMatrixOuterRing {


	public void rotate90(int[][]matrix) {
		for( int ctr = 0; ctr < matrix[0].length-1; ctr++ ) {
			rotate(matrix);
		}
	}
	
	public void rotate(int[][] matrix) {
		
		int n = matrix[0].length;
		
		int topShelf = matrix[0][n-1];
		// top
		for( int ctr = n-1; ctr > 0 ; ctr-- ) {
			matrix[0][ctr] = matrix[0][ctr-1];
		}

		int rightShelf = matrix[n-1][n-1];
		// Right
		for( int ctr = n-1; ctr > 1; ctr-- ) {
			matrix[ctr][n-1] = matrix[ctr-1][n-1];
		}
		matrix[1][n-1] = topShelf;
		
		int bottomShelf = matrix[n-1][0];
		// Bottom
		for( int ctr = 0; ctr < n-2; ctr++ ) {
			matrix[n-1][ctr] = matrix[n-1][ctr+1];
		}
		matrix[n-1][n-2] = rightShelf;
		
		// Left
		for( int ctr = 0; ctr < n-1; ctr++ ) {
			matrix[ctr][0] = matrix[ctr+1][0];
		}
		matrix[n-2][0] = bottomShelf;
	}
	
	public void printMatrix(int[][]matrix) {
		StringBuilder sb = new StringBuilder();
		for( int x = 0; x < matrix[0].length; x++ ) {
			boolean first = true;
			for( int y = 0; y < matrix[0].length; y++ ) {
				if( first ) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(matrix[x][y]);
			}
			sb.append(System.lineSeparator());
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		int n = 4;
		int[][]matrix = new int[n][n];
		int value = 0;
		for( int x = 0; x < n; x++ ) {
			for( int y = 0; y < n; y++ ) {
				matrix[x][y] = value++; 
			}
		}
		
		RotateMatrixOuterRing rotate = new RotateMatrixOuterRing();
		rotate.printMatrix(matrix);
		rotate.rotate90(matrix);
		System.out.println("After...");
		rotate.printMatrix(matrix);
	}
}
