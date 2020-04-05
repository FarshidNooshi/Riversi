public class Game extends Movements{

    private boolean turn; // turn = 1 -> black : white
    private Board board;

    private void start() {
        board.set(4, 'D', false);
        board.set(4, 'E', true);
        board.set(5, 'D', true);
        board.set(5, 'E', false);
        board.print();
        board.clrScr();
        while (toBeContinued()) {

        }
    }

    private Boolean toBeContinued() {
        for (int i = 1; i < MAXN; i++)
            for (int j = 1; j < MAXN; j++)
                if (isValid(i, j, turn))
                    return false;
        return true;
    }

    public boolean valid(int x, int y) {
        return x > 0 && y > 0 && x <= 8 && y <= 8;
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

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
