public class LineThread implements Runnable {
    private int[][] a;
    private int[][] b;
    private int i;
    private int step;

    public LineThread(int[][] before, int[][] after, int lineNumber, int step) {
        this.a = before;
        this.b = after;
        this.i = lineNumber;
        this.step = step;
    }

    @Override
    public void run() {
        if (step % 2 == 0) {
            for (int j = 0; j < b.length; j++) {
                int n = neighbours(a, i, j);
                if (a[i][j] == 0) {
                    if (n == 3) {
                        b[i][j] = 1;
                    } else {
                        b[i][j] = 0;
                    }
                } else if (n == 2 || n == 3) {
                    b[i][j] = 1;
                } else {
                    b[i][j] = 0;
                }
            }
        } else {
            for (int j = 0; j < a.length; j++) {
                int n = neighbours(b, i, j);
                if (b[i][j] == 0) {
                    if (n == 3) {
                        a[i][j] = 1;
                    } else {
                        a[i][j] = 0;
                    }
                } else if (n == 2 || n == 3) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = 0;
                }
            }
        }

    }

    private int neighbours(int[][] a, int i, int j) {
        int neighbours;
        int n = a.length;
        neighbours = a[(i + 1) % n][(j + 1) % n] + a[(i + 1) % n][j] + a[(i + 1) % n][(j - 1 + n) % n] +
                a[i][(j + 1) % n] + a[i][(j - 1 + n) % n] + a[(i - 1 + n) % n][(j + 1) % n] + a[(i - 1 + n) % n][j] + a[(i - 1 + n) % n][(j - 1 + n) % n];
        return neighbours;
    }

}
