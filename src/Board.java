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

    public char get(int x, int y) {
        return table[x][y];
    }

    public void set(int x, char y, boolean col) {
        table[x][y - 'A' + 1] = (col ? uniBlack : uniWhite);
    }
}
