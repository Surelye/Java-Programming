package task4;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class B4 {

    private long recursiveFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
        }
    }

    class FJPFibonacci extends RecursiveTask<Long> {

        private final int n;

        FJPFibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Long compute() {
            if (n <= 1) {
                return (long)n;
            }
            FJPFibonacci cur = new FJPFibonacci(n - 1);
            cur.fork();
            FJPFibonacci prev = new FJPFibonacci(n - 2);

            return prev.compute() + cur.join();
        }
    }

    private BigInteger dynamicFibonacci(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }
        BigInteger prev = BigInteger.ZERO;
        BigInteger cur = BigInteger.ONE;
        BigInteger temp;

        for (int i = 2; i <= n; ++i) {
            temp = cur;
            cur = cur.add(prev);
            prev = temp;
        }

        return cur;
    }

    class MatMulFibonacci {

        private final BigInteger[][] mat = new BigInteger[][]{
                new BigInteger[]{BigInteger.ZERO, BigInteger.ONE},
                new BigInteger[]{BigInteger.ONE, BigInteger.ONE}
        };

        private BigInteger[][] matMul(BigInteger[][] fM, BigInteger[][] sM) {
            BigInteger a1 = fM[0][0], a2 = fM[0][1], a3 = fM[1][0], a4 = fM[1][1];
            BigInteger b1 = sM[0][0], b2 = sM[0][1], b3 = sM[1][0], b4 = sM[1][1];

            return new BigInteger[][]{
                    new BigInteger[]{a1.multiply(b1).add(a2.multiply(b3)),
                            a1.multiply(b2).add(a2.multiply(b4))},
                    new BigInteger[]{a3.multiply(b1).add(a4.multiply(b3)),
                            a3.multiply(b2).add(a4.multiply(b4))}
            };
        }

        public BigInteger compute(int n) {
            if (n <= 1) {
                return BigInteger.valueOf(n);
            }
            BigInteger[][] result = new BigInteger[][]{
                    new BigInteger[]{BigInteger.ONE, BigInteger.ZERO},
                    new BigInteger[]{BigInteger.ZERO, BigInteger.ONE}
            };
            BigInteger[][] multer = mat;

            while (n != 0) {
                while ((n & 1) == 0) {
                    multer = matMul(multer, multer);
                    n >>= 1;
                }
                result = matMul(result, multer);
                n -= 1;
            }

            return result[1][0];
        }
    }

    private double getSecondsPassed(double start, double end) {
        return (end - start) / 1000.0;
    }

    private void
    printResults(BigInteger fib, double recTime, double fjpTime, double dynTime, double matTime) {
        System.out.printf("> Вычисленное число Фибоначчи: %s%n%n", fib);
        System.out.printf("> Время работы рекурсивного способа: %s%n", recTime);
        System.out.printf("> Время работы ForkJoinPool: %s%n", fjpTime);
        System.out.printf("> Время работы динамического метода: %s%n", dynTime);
        System.out.printf("> Время работы матричного метода: %s%n", matTime);
    }

    public void run() {
        double startTime, endTime;
        double recFibTime, fjpFibTime, dynamicFibTime, matMulFibTime;
        BigInteger nthFib;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("> Введите n: ");
            int n = scanner.nextInt();
            if (n < 0) {
                throw new RuntimeException("Некорректное значение n");
            }

            if (n > 45) {
                recFibTime = Double.POSITIVE_INFINITY;
                fjpFibTime = Double.POSITIVE_INFINITY;
            } else if (n > 43) {
                startTime = System.currentTimeMillis();
                System.out.println("> Начало вычислений рекурсивным способом");
                recursiveFibonacci(n);
                endTime = System.currentTimeMillis();
                recFibTime = getSecondsPassed(startTime, endTime);
                fjpFibTime = Double.POSITIVE_INFINITY;
            } else {
                startTime = System.currentTimeMillis();
                System.out.println("> Начало вычислений рекурсивным способом");
                recursiveFibonacci(n);
                endTime = System.currentTimeMillis();
                recFibTime = getSecondsPassed(startTime, endTime);
                try (ForkJoinPool fjp = new ForkJoinPool()) {
                    ForkJoinTask<Long> fibTask = new FJPFibonacci(n);
                    startTime = System.currentTimeMillis();
                    System.out.println("> Начало вычислений с помощью ForkJoinPool");
                    fjp.invoke(fibTask);
                    endTime = System.currentTimeMillis();
                }
                fjpFibTime = getSecondsPassed(startTime, endTime);
            }
            startTime = System.currentTimeMillis();
            dynamicFibonacci(n);
            endTime = System.currentTimeMillis();
            dynamicFibTime = getSecondsPassed(startTime, endTime);
            startTime = System.currentTimeMillis();
            nthFib = new MatMulFibonacci().compute(n);
            endTime = System.currentTimeMillis();
            matMulFibTime = getSecondsPassed(startTime, endTime);
            printResults(nthFib, recFibTime, fjpFibTime, dynamicFibTime, matMulFibTime);
        } catch (Exception e) {
            System.out.printf("> В ходе работы программы произошло исключение: %s",
                    e.getMessage());
        }
    }

    public static void main(String[] args) {
        new B4().run();
    }
}
