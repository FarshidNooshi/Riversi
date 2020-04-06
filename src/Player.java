import java.util.Scanner;

public class Player extends Movements {
    protected Board board;

    /**
     *
     * @param board is the initial board of the game and for the players to use
     */
    public Player(Board board) {
        this.board = board;
    }

    /**
     * this method is for the player to play one move
     * it get's the input parameters and if it was a valid move then it'll make the move and
     * update the Board otherwise it'll print "Try Again".
     * this method is for human player the PC player is an override version of this method
     * @param inp is the input scanner
     * @param turn is telling us whose turn it is. true -> Black, false -> white
     * @return true if it played the move or false if it wasn't valid
     */
    protected boolean play(Scanner inp, boolean turn) {
        board.print(turn);
        System.out.print("Player " + (turn ? "Black" : "White") + " Select a cell: ");
        int row = inp.nextInt();
        char column = inp.next().charAt(0);
        if (valid(row, column - 'A' + 1) && board.isValid(row, column - 'A' + 1, turn)) {
            board.update(row, column - 'A' + 1, turn);
            return true;
        }
        System.out.println("Try Again");
        return false;
    }
}
