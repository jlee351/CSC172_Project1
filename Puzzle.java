/*-
* AUTHOR: LEE, JOON HYUP
* Puzzle.java
* Puzzle defines the size and content of the puzzle.
*/


import java.util.Random;

public class Puzzle{

    //intializes puzzle; a 4x4 2D array with only 0's
    int [][] puzzle = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    private Random rand = new Random();
    boolean play;   //true if game is being played, false if player loses, or presses 'q'
    int border = 0; //"border" to which blocks can move with each valid move. explained in depth later
    int validmoves = 0; //number of valid moves given by user; w,s,a,d
    int score; //highest number in the game.

    //starts the game; sets play to true.
    public void gamestart(){
        play = true;
    }
    //ends the game; sets play to false.
    public void gameend(){
        play = false;
    }

    //resets the puzzle; all 0's
    public void reset(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                puzzle[i][j] = 0;
            }
        }
        makenum();
    }

    //prints puzzle
    public void printpuzzle(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        getscore();
        System.out.println("Current score: " + score);
        System.out.println("Current moves: " + validmoves);

        //for loop for changing 0's to stars, as to follow formatting shown in project description.
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(puzzle[i][j] == 0){
                    System.out.printf("%4s", "*");
                    continue;
                }
                System.out.printf("%4s", puzzle[i][j]); //formats the numbers so they remain equally apart
            }
            System.out.println("");
            System.out.println("");
        }
    }

    //randomly fills array's *'s; 2 or 4
    public void makenum(){
        int row = rand.nextInt(4);
        int col = rand.nextInt(4);
        int temp = rand.nextInt(9); //picks random number from 0 to 9. If equal or greater than 8, prints 4. Otherwise, 2.

        if(puzzle[row][col] == 0){
            if(temp < 8){
                puzzle[row][col] = 2;
            }
            else{
                puzzle[row][col] = 4;
            }
        }
        else{
            makenum();  //recursion for if makenum doesn't select an empty number; i.e. there is a number there already.
        }
    }

    //main play method; moves the block according to player move.
    public void play(char move){
        int temp;
        //if pressed 'w', shifts blocks upwards
        if(move == 'w'){
    		for (int i = 0; i < 4; i++) {
    			border = 0;
    			for (int j = 0; j < 4; j++) {
    				if (puzzle[j][i] != 0) {
    					if (border <= j) {
    						updown(j, i, false);
    					}
    				}
    			}
    		}
            validmoves++;
        }
        //if pressed 's', shifts blocks downwards
        else if(move == 's'){
            for (int i = 0; i < 4; i++) {
                border = 3;
			    for (int j = 3; j >= 0; j--) {
                    if (puzzle[j][i] != 0) {
                        if (border >= j) {
                            updown(j, i, true);
					    }
				    }
			    }
		    }
            validmoves++;
        }
        //if pressed 'a', shifts blocks leftwards
        else if(move == 'a'){
            for (int i = 0; i < 4; i++) {
    			border = 0;
    			for (int j = 0; j < 4; j++) {
    				if(puzzle[i][j] != 0){
    					if(border <= j){
    						leftright(i, j, false);
    					}
    				}
    			}
    		}
            validmoves++;
        }
        //if pressed 'd', shifts blocks rightwards
        else if(move == 'd'){
            for(int i = 0; i < 4; i++){
    			border = 3;
    			for(int j = 3; j >= 0; j--){
    				if(puzzle[i][j] != 0){
    					if(border >= j){
    						leftright(i, j, true);
    					}
    				}
    			}
    		}
            validmoves++;
        }
        //if pressed 'r', resets the game
        else if(move == 'r'){
            reset();
        }
        //if pressed 'q', quits the game
        else if(move == 'q'){
            getscore();
            play = false;
        }
        //if the puzzle is not full; i.e.) has a 0 in it, makes more numbers
        if(!isfull()){
            makenum();
        }

    }
    //sets "score" to highest number in the puzzle.
    public void getscore(){
        score = puzzle[0][0];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(score < puzzle[i][j]){
                score = puzzle[i][j];
                }
            }
        }
    }

    //prevents from the blocks from adding more than one time. e.g. can't add 2 2 4 into 8 but rather 4 4
    public void updown(int row, int col, boolean u) {
		if (puzzle[border][col] == 0 || puzzle[border][col] == puzzle[row][col]) {
			if (row > border || (u && (border > row))) {
				puzzle[border][col] += puzzle[row][col];
				puzzle[row][col] = 0;
			}
		} else {
			if (u) {
				border--;
			} else {
				border++;
			}
			updown(row, col, u);

		}
	}
    //prevents from the blocks from adding more than one time. e.g. can't add 2 2 4 into 8 but rather 4 4
    public void leftright(int row, int col, boolean l) {
		if (puzzle[row][border] == 0 || puzzle[row][border] == puzzle[row][col]) {
			if (col > border || (l && (border > col))) {
				puzzle[row][border] += puzzle[row][col];
				puzzle[row][col] = 0;
			}
		}
        else{
            if (l) {
			    border--;
			}
            else {
				border++;
			}
			leftright(row, col, l);
		}
	}

    //checks if the puzzle is full
    public boolean isfull(){
        boolean full = true;
        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++){
                if(puzzle[i][j]==0){
                    full = false;
                }
            }
        }
        return full;
    }

    //check if the player can move any more numbers.
    public boolean checklose(){
        boolean rowdif = false;
        boolean coldif = false;
        boolean lose = false;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if(puzzle[i][j]!=puzzle[i][j+1]){
                    rowdif = true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if(puzzle[i][j]!=puzzle[i+1][j]){
                    coldif = true;
                }
            }
        }
        if(rowdif && coldif){
            lose = true;
        }
        return lose;
    }

}
