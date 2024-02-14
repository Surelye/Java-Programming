import task3.A3;
import task3.B3;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        String input;

        label:
        while (true) {
            System.out.print("""
                    > Введите номер задания:
                    [1] -- Сортировка данных в файле
                    [2] -- Рекурсивное архивирование
                    [0] -- Выход

                    """);
            System.out.print("> Вводите: ");
            input = scanner.nextLine();

            try {
                switch (input) {
                    case "0":
                        break label;
                    case "1":
                        System.out.print("> Введите имя файла (по умолчанию input.txt в текущем каталоге): ");
                        String filename = scanner.nextLine();
                        if (filename.isBlank()) {
                            filename = "C:\\Users\\sgnot\\IdeaProjects\\Java Programming\\input.txt";
                        } else if (!new File(filename).exists()) {
                            throw new RuntimeException("Указанный файл не существует");
                        }
                        A3 taskClass = new A3(filename);
                        System.out.println("> Отсортированные строки файла: ");
                        taskClass.printEntries();
                        break;
                    case "2":
                        System.out.print("> Введите относительный путь архивируемой директории: ");
                        String path = scanner.nextLine();
                        System.out.print("> Введите целевую строку: ");
                        String targetString = scanner.nextLine();
                        System.out.println("> Начало архивации");
                        if (new B3(path, targetString).zipEntitiesWithNameContainingTargetString()) {
                            System.out.println("> Архивация прошла успешно");
                        }
                        break;
                    default:
                        System.out.println("[ERROR] Некорректный ввод. Попробуйте снова.");
                        break;
                }
                System.out.println();
            } catch (Exception e) {
                System.out.printf("> В ходе работы программы было получено исключение: %s.%n%n",
                        e.getMessage());
            }
        }
        scanner.close();
    }
}