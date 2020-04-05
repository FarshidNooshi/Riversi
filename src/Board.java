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

    public void clrScr() {
        for (int i = 0; i < 25; i++)
            System.out.println();
    }

    void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (table[i][j] == '-') {
                    System.out.print(" |");
                    continue;
                }
                System.out.print((char) table[i][j] + "|");
            }
            System.out.println();
        }
    }

    public void update(int x, int y, boolean col) {
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

    public void set(int x, char y, boolean col) {
        table[x][y - 'A' + 1] = (col ? uniBlack : uniWhite);
    }
}
