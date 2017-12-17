import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfLifeImpl implements GameOfLife {
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
        for (int i = 0; i < n; i++) {
            if (i%2 == 0) {
                doStep(a,b);
            } else {
                doStep(b,a);
            }
        }
        if (n%2 == 1) {
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

    public void doStep(int[][] before, int[][] after){
        for (int i = 0; i < after.length; i++) {
            for (int j = 0; j < after.length; j++) {
                int n = neighbours(before,i,j);
                if (before[i][j] == 0) {
                    if (n == 3) {
                        after[i][j] = 1;
                    } else {
                        after[i][j] = 0;
                    }
                } else if (n == 2 || n == 3) {
                    after[i][j] = 1;
                } else {
                    after[i][j] = 0;
                }
            }
        }
    }

    public int neighbours(int[][] a, int i, int j){
        int neighbours;
        int n = a.length;
        neighbours = a[(i+1)%n][(j+1)%n] + a[(i+1)%n][j] + a[(i+1)%n][(j-1+n)%n] +
                a[i][(j+1)%n] + a[i][(j-1+n)%n] + a[(i-1+n)%n][(j+1)%n] + a[(i-1+n)%n][j] + a[(i-1+n)%n][(j-1+n)%n];
        return neighbours;
    }
}
