public class Movements {
    final int[] px = {0, 0, 1, 1, 1, -1, -1, -1};
    final int[] py = {1, -1, 0, 1, -1, 1, 0, -1};
    final int MAXN = 9;
    final char uniWhite = (char)(9679);
    final char uniBlack = (char)(9675);
    final char semiCol = (char)(9676);

    boolean valid(int x, int y) {
        return x > 0 && y > 0 && x <= 8 && y <= 8;
    }

}