// In The Name Of GOD

/**
 * this class is for the board of the game
 * for each method i talked about the actions or the informations that it send.
 * we have a 2D array of characters to represent the game's table
 * I inherited board from movements because the information and the method in movements is same for all classes and
 * by changing some constants in that class we can change our program or maybe interrupt it's working.
 */
public class Board extends Movements {
    private char[][] table;

    /**
     * it's the constructor of this class that sets the array to a particular sized array.
     * actually '-' means the cell is empty and in the left side of the array we have the number of each row
     * and on the top of the table we have characters starting from 'A' to 'H' which means the specified character of their column.
     */
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

    /**
     *
     * @param x is the row number
     * @param y is the column number
     * @param col if true means it's Black's turn and false means it's white's turn
     * @return the number of cells that will change their faces if we choose the cell (x, y) for user(col)
     */
    int count(int x, int y, boolean col) {
        int counter = 1;
        for (int i = 0; i < 8; i++) {
            int xx = x + px[i];
            int yy = y + py[i];
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

    /**
     * this method just prints 25 new lines to clear the console for the new turns
     */
    public void clrScr() {
        for (int i = 0; i < 25; i++)
            System.out.println();
    }

    /**
     *
     * @param x is a row number
     * @param y is a column number
     * @param turn if true means it's Black's turn and false means it's white's turn
     * @return if it's valid to use cell (x, y) as a move for the user whose turn is.
     */
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

    /**
     * this method is for printing the table for the users
     * @param col tells that, it's which user's turn.
     */
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

    /**
     * this method is for updating the table for user(col) if he/she chose the cell (x, y) as his/her move.
     * for this method it's guaranteed that making a move in cell(x, y) for user(col) is valid.
     * @param x is a row number
     * @param y is a column number
     * @param col tells the which user called the method/ true -> Black, false -> white
     */
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

    /**
     *
     * @param x is a row number
     * @param y is a column number
     * @return the character in the cell (x, y)
     */
    public char get(int x, int y) {
        return table[x][y];
    }

    /**
     * this method is for setting a specific cell in the table for a user in other word marking it as white or black for the user
     * @param x is a row number
     * @param y is a column number
     * @param col tells which user called this method / true -> black, false -> white
     */
    void set(int x, char y, boolean col) {
        table[x][y - 'A' + 1] = (col ? uniBlack : uniWhite);
    }
}
