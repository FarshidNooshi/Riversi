import java.util.Scanner;

public class Game extends Movements {

    private boolean turn; // turn = 1 -> black : white
    private Board board;

    private void start() throws InterruptedException {
        board.set(4, 'D', false);
        board.set(4, 'E', true);
        board.set(5, 'D', true);
        board.set(5, 'E', false);
        Scanner inp = new Scanner(System.in);
        while (true) {
            if (!toBeContinued()) {
                System.out.println("pass");
                turn = !turn;
            }
            if (!toBeContinued()) {
                return;
            }
            board.print(turn);
            System.out.print("Player " + (turn ? "Black" : "White") + " Select a cell: ");
            int row = inp.nextInt();
            char column = inp.next().charAt(0);
            if (valid(row, column - 'A' + 1) && board.isValid(row, column - 'A' + 1, turn)) {
                board.update(row, column - 'A' + 1, turn);
            } else {
                System.out.println("Try Again");
                turn = !turn;
            }
//            board.clrScr();
//            Thread.sleep(3000);
            turn = !turn;
        }
    }

    private Boolean toBeContinued() {
        for (int i = 1; i < MAXN; i++)
            for (int j = 1; j < MAXN; j++)
                if (board.isValid(i, j, turn))
                    return true;
        return false;
    }

    public Game() {
        super();
        board = new Board();
    }

    void end() {
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
        Game game = new Game();
        game.start();
        game.end();
    }
}
