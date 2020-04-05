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
        while (toBeContinued()) {
            board.print();
            System.out.print("Select a cell: ");
            int row = inp.nextInt();
            char column = inp.next().charAt(0);
            if (valid(row, column - 'A' + 1) && isValid(row, column - 'A' + 1, turn)) {
                board.update(row, column - 'A' + 1, turn);
            } else {
                System.out.println("Try Again");
            }
//            board.clrScr();
            Thread.sleep(3000);
            turn = !turn;
        }
    }

    private Boolean toBeContinued() {
        for (int i = 1; i < MAXN; i++)
            for (int j = 1; j < MAXN; j++)
                if (isValid(i, j, turn))
                    return true;
        return false;
    }

    private boolean isValid(int x, int y, boolean turn) {
        if (board.get(x, y) != '-')
            return false;
        for (int i = 0; i < 8; i++) {
            int xx = x + px[i], yy = y + py[i];
            int cnt = 0;
            while (valid(xx, yy) && board.get(xx, yy) == (turn ? uniWhite : uniBlack)) {
                xx += px[i];
                yy += py[i];
                cnt++;
            }
            if (cnt > 0 && valid(xx, yy) && board.get(xx, yy) == (turn ? uniBlack : uniWhite))
                return true;
        }
        return false;
    }

    public Game() {
        super();
        board = new Board();
    }

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        game.start();
    }
}
