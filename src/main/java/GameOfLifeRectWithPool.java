import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfLifeRectWithPool implements GameOfLife {
    @Override
    public List<String> play(String inputFile) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int dim = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.nextLine();
        int[][] a = readData(dim, scanner);
        int[][] b = new int[dim][dim];
        int threadNumber;
        if (dim < 10) threadNumber = 3;
        else threadNumber = 50;
        ExecutorsPool pool = new ExecutorsPool(threadNumber);
        int l = dim / threadNumber;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < threadNumber; i++) {
                pool.execute(new RectThread(a, b, k, i, l, threadNumber));
            }
            pool.waitAllTasks();
        }
        pool.shutdown();
        if (n % 2 == 1) {
            List<String> lines = transformData(b);
            return lines;
        } else {
            List<String> lines = transformData(a);
            return lines;
        }
    }


    public int[][] readData(int dim, Scanner scanner) {
        int[][] a = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            String str = scanner.nextLine();
            for (int j = 0; j < dim; j++) {
                a[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        return a;
    }

    public List<String> transformData(int[][] a) {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            String str = "";
            for (int j = 0; j < a.length; j++) {
                str = str + Integer.toString(a[i][j]);
            }
            lines.add(str);
        }
        return lines;
    }
}
