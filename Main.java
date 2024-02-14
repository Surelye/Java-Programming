import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        label:
        while (true) {
            System.out.print("""
                    > Введите номер задания:
                    [1] -- Сортировка данных в файле
                    [2] -- TODO
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
                        }
                        A3 taskClass = new A3(filename);
                        System.out.println("> Отсортированные строки файла: ");
                        taskClass.printEntries();
                        break;
                    case "2":
                        System.out.println("> Тут пока что ничего нет...");
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
