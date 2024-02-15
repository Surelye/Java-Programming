package task4;

import java.util.concurrent.Semaphore;

public class A4 {

    private int i = 0;

    private void inc(int times) {
        for (int j = 0; j < times; ++j) {
            ++i;
        }
    }

    class IncThread extends Thread {

        IncThread(String name) {
            super(name);
        }

        IncThread() {
            this("incThread");
        }

        @Override
        public void run() {
            inc(100_000);
        }
    }

    class IncRunnable implements Runnable {

        @Override
        public void run() {
            inc(100_000);
        }
    }

    private void startThread(Thread thread) {
        System.out.printf("> Начало работы потока %s%n", thread.getName());
        thread.start();
    }

    private void finishThread(Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.printf("[WARNING] Поток %s был прерван%n", thread.getName());
            return;
        }
        System.out.printf("> Завершение работы потока %s%n", thread.getName());
    }

    private void testThreads()  {
        IncThread incThread = new IncThread();
        Thread incRunnable = new Thread(new IncRunnable());
        incRunnable.setName("incRunnable");
        Thread lambdaRunnable = new Thread(() -> inc(100_000));
        lambdaRunnable.setName("lambdaRunnable");

        startThread(incThread);
        startThread(incRunnable);
        startThread(lambdaRunnable);

        System.out.println();
        finishThread(incThread);
        finishThread(incRunnable);
        finishThread(lambdaRunnable);

        System.out.printf("%n> Значение i после завершения работы всех потоков: %d%n%n", i);
    }

    private void correctInc(int times, Semaphore semaphore) {
        try {
            semaphore.acquire();
            inc(times);
        } catch (InterruptedException e) {
            System.out.println("[WARNING] Захват значения семафора не удался%n");
            return;
        }
        semaphore.release();
    }

    class IncCorrectThread extends Thread {

        private final Semaphore semaphore;

        IncCorrectThread(String name, Semaphore semaphore) {
            super(name);
            this.semaphore = semaphore;
        }

        IncCorrectThread(Semaphore semaphore) {
            this("incCorrectThread", semaphore);
        }

        IncCorrectThread(String name) {
            this(name, new Semaphore(1));
        }

        IncCorrectThread() {
            this("incCorrectThread", new Semaphore(1));
        }

        public void run() {
            correctInc(100_000, semaphore);
        }
    }

    class IncCorrectRunnable implements Runnable {

        private final Semaphore semaphore;

        IncCorrectRunnable(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        IncCorrectRunnable() {
            this(new Semaphore(1));
        }

        @Override
        public void run() {
            correctInc(100_000, semaphore);
        }
    }

    private void testCorrectThreads() {
        Semaphore semaphore = new Semaphore(1);
        IncCorrectThread incCorrectThread = new IncCorrectThread(semaphore);
        Thread incCorrectRunnable = new Thread(new IncCorrectRunnable(semaphore));
        incCorrectRunnable.setName("incCorrectRunnable");
        Thread correctLambdaRunnable = new Thread(() -> correctInc(100_000, semaphore));
        correctLambdaRunnable.setName("correctLambdaRunnable");

        startThread(incCorrectThread);
        startThread(incCorrectRunnable);
        startThread(correctLambdaRunnable);

        System.out.println();
        finishThread(incCorrectThread);
        finishThread(incCorrectRunnable);
        finishThread(correctLambdaRunnable);

        System.out.printf("%n> Значение i после завершения работы всех потоков: %d", i);
    }

    public void run() {
        testThreads();
        i = 0;
        testCorrectThreads();
    }

    public static void main(String[] args) {
        new A4().run();
    }
}
