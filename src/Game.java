// In The Name Of GOD

import java.util.Scanner;

/**
 * this class is the class which has the main method inside it.
 * extending movement class for this class is for using the constant information's and the common method for both
 * game and board class.
 * in this class turn boolean is for knowing the turn and the user who is playing
 * I also have a Board for my game to playing on.
 * this class plays the game and I implemented PC mode in this class.
 */
public class Game extends Movements {

    private boolean turn; // turn = 1 -> black : white
    private Board board;
    private Player[] player;

    /**
     * @throws InterruptedException it's for stopping the main Thread, for seeing the table before clearing the console I copy/pasted it from https://www.java67.com/2015/07/how-to-stop-thread-in-java-example.html
     *                              the throw is for thread.sleep(1000)
     *                              this method starts the game and play it
     */
    private void start(boolean isSingle, Scanner inp) throws InterruptedException {
        board.set(4, 'D', false);
        board.set(4, 'E', true);
        board.set(5, 'D', true);
        board.set(5, 'E', false);
        if (isSingle)
            singlePlay(inp);
        else
            multiPlay(inp);
    }

    /**
     * this method works for single player mode for playing against computer
     * computer in each turn chooses the cell that gives him the most number of returned cells from it's opponent
     * means that it'll choose that specific available cell to giving him the maximum number of cells from it's opponent for reversing
     *
     * @param inp is the scanner for getting the input
     * @throws InterruptedException is the same like the start method
     */
    private void singlePlay(Scanner inp) throws InterruptedException {
        while (toBeContinued()) {
            if (turn) {
                if (!player[0].play(inp, true))
                    turn = false;
            } else
                player[1].play(inp, false);
            turn = !turn;
        }
    }

    /**
     * this method is for playing in multiPlayer mode
     * if we couldn't continue the game the method will return.
     * otherwise it'll print the table and asks specific user to choose a cell for the move
     * if it is available and valid the move will be changed otherwise it'll say try again
     *
     * @param inp is the input scanner
     * @throws InterruptedException is here for the same reason as start method
     */
    private void multiPlay(Scanner inp) throws InterruptedException {
        while (toBeContinued()) {
            board.print(turn);
            boolean can = player[(turn ? 1 : 0)].play(inp, turn);
            if (!can) {
                turn = !turn;
                System.out.println("Try Again");
            }
            turn = !turn;
        }
    }

    /**
     * this method checks if we should pass the current player or the game is over
     * first it check if the first player does have at least one move
     * if he doesn't have any valid move then it check for it's opponent, if he has then it print's pass and continue to
     * the game otherwise it will return false and the game will be over
     *
     * @return true if we can continue our game and false if we can't
     */
    private Boolean toBeContinued() throws InterruptedException {
        Thread.sleep(1000);
        board.clrScr();
        for (int i = 1; i < MAXN; i++)
            for (int j = 1; j < MAXN; j++)
                if (board.isValid(i, j, turn))
                    return true;
        turn = !turn;
        for (int i = 1; i < MAXN; i++)
            for (int j = 1; j < MAXN; j++)
                if (board.isValid(i, j, turn)) {
                    System.out.println("pass");
                    return true;
                }
        return false;
    }

    /**
     * the constructor as you can see
     */
    public Game(boolean isSingle) {
        super();
        board = new Board();
        player = new Player[2];
        player[0] = new Player(board);
        if (isSingle)
            player[1] = new PC(board);
        else
            player[1] = new Player(board);
    }

    /**
     * it's the method for ending the game and showing the winner and loser with their number of cells
     * or it'll tell that the game ends in tie
     */
    private void end() {
        int cnt1 = 0, cnt2 = 0;
        for (int i = 1; i < MAXN; i++)
            for (int j = 1; j < MAXN; j++)
                if (board.get(i, j) == uniBlack)
                    cnt1++;
                else if (board.get(i, j) == uniWhite)
                    cnt2++;
        System.out.println("Player Black has " + cnt1 + " cells.");
        System.out.println("Player White has " + cnt2 + " cells.");
        if (cnt1 != cnt2)
            System.out.println("The winner is " + (cnt1 < cnt2 ? "White." : "Black."));
        else
            System.out.println("The game ends in tie.");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("In The Name Of GOD");
        Game game;
        Scanner inp = new Scanner(System.in);
        System.out.println("Do you want to play against Computer ?\n[y]yes [n]no");
        char c = inp.next().charAt(0);
        game = new Game(c == 'y');
        game.start(c == 'y', inp);
        game.end();
    }
}
