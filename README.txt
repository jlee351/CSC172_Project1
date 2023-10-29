// Project 1 by Lee, Joon Hyup

// email: jlee351@u.rochester.edu

// Explanation:

Main.java
This is the main method for the project. It takes in individual characters from the user and uses it as the input for the game. The while loop ensures the game runs as long as the player has not lost; i.e. still can move and/or has 0's on the puzzle. The method also prints out the current score and number of moves the player currently has.

Puzzle.java
The class that defines the structure and content of the 2048 game. "Puzzle.java" initializes the puzzle - a 4x4 2D matrix - and defines the methods that are required for the function to run. gamestart() and gameend() respectively starts and ends the game. reset() clears and re-sets the game to its initial state; all 0's. printpuzzle() prints the puzzle using two nested for loops. makenum() utilizes the random library and spawns 2 or a 4 on a random empty spot on the puzzle. play() takes in a character and uses it to determine which move the player makes. getscore() simply parses through the 2D array and finds the largest number i.e. the current "score". leftright and updown ensures that the numbers don't add more than once. For example, 2 2 4 doesn't instantly add up to 8, but rather 4 4. checklose() checks if the player can slide blocks; has either a same number adjacent or an empty space.


Content:
Main.java
Puzzle.java
README.txt
