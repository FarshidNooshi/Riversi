import java.util.Scanner;

/**
 * it's the pc class for computer mode
 * it plays as a computer
 */

public class PC extends Player {
    public PC(Board board) {
        super(board);
    }

    /**
     * this method is working for computer moves
     * in one move it'll pick the cell that gives computer the largest amount of points
     * meaning that the computer move is always that cell which reverses the largest amount of opponent cells.
     *
     * @param inp  is the input scanner
     * @param turn is telling us whose turn it is. true -> Black, false -> white
     * @return always true cuz the computer always make valid moves
     */
    @Override
    protected boolean play(Scanner inp, boolean turn) {
        board.print(turn);
        System.out.print("Player " + (turn ? "Black" : "White") + " Select a cell: ");
        int mx = -1, x = 0, y = 0;
        for (int i = 1; i < MAXN; i++)
            for (int j = 1; j < MAXN; j++)
                if (board.isValid(i, j, turn) && mx < board.count(i, j, turn)) {
                    mx = board.count(i, j, turn);
                    x = i;
                    y = j;
                }
        System.out.println(x + " " + (char) (y + 'A' - 1));
        board.update(x, y, turn);
        return true;// cuz it's always true, it was checked in toBeContinued()
    }
}
