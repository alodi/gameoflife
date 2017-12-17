import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameOfLife game = new GameOfLifeImpl();
        long start = System.nanoTime();
        List<String> result = game.play("resources/input1000.txt");
        System.out.println((System.nanoTime() - start) / (1000_000) + " ms");
    }
}
