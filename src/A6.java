package task6;

import java.util.Scanner;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class A6 {

    private boolean isEven(int num) {
        return ((num & 1) == 0);
    }

    private void selectPredicate(IntStream intStream) {
        System.out.println(
                """
                > Выберите условие обработки
                > [1] -- Оставить чётные
                > [2] -- Оставить нечётные
                > [3] -- Оставить равные заданному числу
                > [4] -- Оставить меньшие заданного числа
                > [5] -- Оставить большие заданного числа
                """
        );
        System.out.print("> Вводите: ");
        try (Scanner scanner = new Scanner(System.in)) {
            int option = scanner.nextInt();
            IntStream filtered = switch (option) {
                case 1 -> intStream.filter(this::isEven);
                case 2 -> {
                    IntPredicate isEven = this::isEven;
                    yield intStream.filter(isEven.negate());
                }
                case 3, 4, 5 -> {
                    System.out.print("> Введите целое число: ");
                    int arg = scanner.nextInt();
                    if (option == 3) {
                        yield intStream.filter(n -> n == arg);
                    } else if (option == 4) {
                        yield intStream.filter(n -> n < arg);
                    } else {
                        yield intStream.filter(n -> n > arg);
                    }
                }
                default -> throw new RuntimeException("Некорректное условие обработки");
            };
            System.out.printf("> Результат фильтрации: %s", filtered
                    .boxed()
                    .toList());
        }
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("> Введите нестрогие границы стрима через пробел: ");
            String[] splitInput = scanner.nextLine().split("\\s+");
            int leftBorder = Integer.parseInt(splitInput[0]),
                    rightBorder = Integer.parseInt(splitInput[1]);
            if (leftBorder > rightBorder) {
                throw new RuntimeException("Левая граница превышает значение правой границы");
            }
            selectPredicate(IntStream.range(leftBorder, rightBorder + 1));
        } catch (Exception e) {
            System.out.printf("> В ходе работы программы было получено исключение: %s%n",
                    e.getMessage());
        }
    }

    public static void main(String[] args) {
        new A6().run();
    }
}
