/*-
* AUTHOR: LEE, JOON HYUP
* Main.java
* Main method to the puzzle
*/

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);
        //makes puzzle and immediately puts two numbers (2 or a 4) in random places.
        Puzzle newpuzzle = new Puzzle();
        newpuzzle.makenum();
        newpuzzle.makenum();
        newpuzzle.gamestart();
        //keeps playing the game while the player hasn't lost
        while(newpuzzle.play){
            newpuzzle.printpuzzle();
            System.out.print("Enter move: ");
            char move = scnr.next().charAt(0);

            if(move == 'w' || move == 'a' || move == 's' || move == 'd' || move == 'q' || move == 'r' ){
                newpuzzle.play(move);
                if(newpuzzle.isfull() && newpuzzle.checklose()){
                    System.out.println("LOST");
                    newpuzzle.getscore();
                    newpuzzle.reset();
                    break;
                }
                System.out.println(move + " is a valid move.");
            }
            else{
                System.out.println(move + " is an invalid move.");
                continue;
            }
        }
        //prints out number of moves and current score.
        System.out.println("Highest Number: " + newpuzzle.score);
        System.out.println("Number of moves: " + newpuzzle.validmoves);

    }

}
