// In The Name Of GOD
public class Board extends Movements {
    private char[][] table;

    public Board() {
        super();
        table = new char[MAXN][MAXN];
        for (int i = 0; i < MAXN; i++)
            for (int j = 0; j < MAXN; j++)
                table[i][j] = '-';
        for (int i = 0; i < 8; i++) {
            table[i + 1][0] = (char) (i + '1');
            table[0][i + 1] = (char) ('A' + i);
        }
    }

    public int count(int x, int y, boolean col) {
        int counter = 1;
        for (int i = 0; i < 8; i++) {
            int xx = x + px[i], yy = y + py[i];
            int cnt = 0;
            while (valid(xx, yy) && table[xx][yy] == (col ? uniWhite : uniBlack)) {
                xx += px[i];
                yy += py[i];
                cnt++;
            }
            if (cnt > 0 && valid(xx, yy) && table[xx][yy] == (col ? uniBlack : uniWhite)) {
                xx = x + px[i];
                yy = y + py[i];
                while (valid(xx, yy) && table[xx][yy] == (col ? uniWhite : uniBlack)) {
                    counter++;
                    xx += px[i];
                    yy += py[i];
                }
            }
        }
        return counter;
    }

    public void clrScr() {
        for (int i = 0; i < 25; i++)
            System.out.println();
    }

    boolean isValid(int x, int y, boolean turn) {
        if (table[x][y] != '-')
            return false;
        for (int i = 0; i < 8; i++) {
            int xx = x + px[i], yy = y + py[i];
            int cnt = 0;
            while (valid(xx, yy) && table[xx][yy] == (turn ? uniWhite : uniBlack)) {
                xx += px[i];
                yy += py[i];
                cnt++;
            }
            if (cnt > 0 && valid(xx, yy) && table[xx][yy] == (turn ? uniBlack : uniWhite))
                return true;
        }
        return false;
    }

    void print(boolean col) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (table[i][j] == '-') {
                    if (isValid(i, j, col))
                        System.out.print(semiCol + "|");
                    else
                        System.out.print(" |");
                    continue;
                }
                System.out.print(table[i][j] + "|");
            }
            System.out.println();
        }
    }

    void update(int x, int y, boolean col) {
        table[x][y] = (col ? uniBlack : uniWhite);
        for (int i = 0; i < 8; i++) {
            int xx = x + px[i], yy = y + py[i];
            int cnt = 0;
            while (valid(xx, yy) && table[xx][yy] == (col ? uniWhite : uniBlack)) {
                xx += px[i];
                yy += py[i];
                cnt++;
            }
            if (cnt > 0 && valid(xx, yy) && table[xx][yy] == (col ? uniBlack : uniWhite)) {
                xx = x + px[i];
                yy = y + py[i];
                while (valid(xx, yy) && table[xx][yy] == (col ? uniWhite : uniBlack)) {
                    table[xx][yy] = (col ? uniBlack : uniWhite);
                    xx += px[i];
                    yy += py[i];
                }
            }
        }
    }

    public char get(int x, int y) {
        return table[x][y];
    }

    void set(int x, char y, boolean col) {
        table[x][y - 'A' + 1] = (col ? uniBlack : uniWhite);
    }
}
