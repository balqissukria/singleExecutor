import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static int count;

    public static synchronized void count() {
        count++;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input i: ");
        int i = scanner.nextInt();
        scanner.close();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int j = 1; j <= i; j++) {
            final int threadNumber = j;
            executorService.execute(() -> {
                for (int k = 1; k <= 3; k++) {
                    count();
                    System.out.println("pool-" + threadNumber + "-thread-1: " + count);
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }

        System.out.println("Total = " + count);
    }
}
