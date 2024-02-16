package task6;

import java.util.*;
import java.util.stream.IntStream;

public class B6 {

    private int[] generateIntArray(int n) {
        int bound = (int)10e6;
        Random random = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; ++i) {
            arr[i] = random.nextInt(bound);
        }

        return arr;
    }

    private int findThirdSmallest(int[] arr) {
        return Arrays.stream(arr)
                .sorted()
                .skip(2)
                .toArray()[0];
    }

    private int findSecondLargest(int[] arr) {
        return Arrays.stream(arr)
                .sorted()
                .skip(arr.length - 2)
                .toArray()[0];
    }

    private int[] sort(int[] arr) {
        return Arrays.stream(arr)
                .sorted()
                .toArray();
    }

    private void findRequiredNumbers(int[] arr) {
        int thirdSmallest, secondLargest;
        if (arr.length > 2) {
            thirdSmallest = findThirdSmallest(arr);
            secondLargest = findSecondLargest(arr);
        } else if (arr.length > 1) {
            thirdSmallest = Integer.MIN_VALUE;
            secondLargest = findSecondLargest(arr);
        } else {
            thirdSmallest = Integer.MIN_VALUE;
            secondLargest = Integer.MAX_VALUE;
        }
        System.out.printf((thirdSmallest == Integer.MIN_VALUE) ?
                "> Массив слишком мал, чтобы найти в нём третий наименьший элемент.%n" :
                "> Третий наименьший элемент: %d.%n", thirdSmallest);
        System.out.printf((secondLargest == Integer.MAX_VALUE) ?
                "> Массив слишком мал, чтобы найти в нём второй наибольший элемент.%n" :
                "> Второй наибольший элемент: %d.%n", secondLargest);
        if (arr.length < 7) {
            System.out.printf("> Отсортированные элементы массива: %s.%n",
                    Arrays.toString(Arrays.stream(arr).sorted().toArray()));
        } else {
            int[] someSmallest = Arrays.stream(arr).sorted().limit(3).toArray();
            int[] someLargest = Arrays.stream(arr).sorted().skip(arr.length - 3).toArray();
            System.out.printf("> Несколько первых наименьших элементов: %s.%n",
                    Arrays.toString(someSmallest));
            System.out.printf("> Несколько первых наибольших элементов: %s.%n",
                    Arrays.toString(someLargest));
        }
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("> Введите количество чисел (n): ");
            int n = scanner.nextInt();
            int[] arr = generateIntArray(n);
            findRequiredNumbers(arr);
        } catch (InputMismatchException e) {
            System.out.println("[ERROR] Некорректный ввод.");
        } catch (NegativeArraySizeException e) {
            System.out.println("[ERROR] Массив не может иметь отрицательное число элементов.");
        }
    }

    public static void main(String[] args) {
        new B6().run();
    }
}
