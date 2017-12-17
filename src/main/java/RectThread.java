public class RectThread implements Runnable {
    private int[][] a;
    private int[][] b;
    private int step;
    private int rectNumber;
    private int length;
    private int threads;

    public RectThread(int[][] a, int[][] b, int step, int rectNumber, int length, int threads) {
        this.a = a;
        this.b = b;
        this.step = step;
        this.rectNumber = rectNumber;
        this.length = length;
        this.threads = threads;
    }

    @Override
    public void run() {
        int indexMin = rectNumber * length;
        int indexMax;
        if (rectNumber == threads - 1) indexMax = a.length;
        else indexMax = (rectNumber + 1) * length ;
        if (step % 2 == 0){
            for (int i = 0; i < b.length; i++) {
                for (int j = indexMin; j < indexMax; j++) {
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
            }
        } else {
            for (int i = 0; i < a.length; i++) {
                for (int j = indexMin; j < indexMax; j++) {
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
    }

    private int neighbours(int[][] a, int i, int j) {
        int neighbours;
        int n = a.length;
        neighbours = a[(i + 1) % n][(j + 1) % n] + a[(i + 1) % n][j] + a[(i + 1) % n][(j - 1 + n) % n] +
                a[i][(j + 1) % n] + a[i][(j - 1 + n) % n] + a[(i - 1 + n) % n][(j + 1) % n] + a[(i - 1 + n) % n][j] + a[(i - 1 + n) % n][(j - 1 + n) % n];
        return neighbours;
    }
}
