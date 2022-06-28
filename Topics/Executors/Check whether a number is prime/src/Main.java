import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newCachedThreadPool(); // assign an object to it

        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            executor.submit(()->{
                Thread t2 = new Thread(new PrintIfPrimeTask(number));
                t2.start();
            });
        }
        executor.shutdown();
    }
}

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    private static boolean isPrime(int number) {
        int counter = 0;
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                counter++;
            }
        }
        if (counter == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {

        if (isPrime(number)) {
            System.out.println(number);
        }
    }
}