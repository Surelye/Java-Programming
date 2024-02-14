import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        label:
        while (true) {
            System.out.print("""
                    > Введите номер задания:
                    [1] -- Определить количество прошедших дней
                    [2] -- Определить день недели через n дней
                    [3] -- Тест производительности реализации строки
                    [0] -- Выход
                                        
                    """);
            System.out.print("> Вводите: ");
            input = scanner.nextLine();

            try {
                switch (input) {
                    case "0":
                        break label;
                    case "1":
                        System.out.print("> Введите первую дату: ");
                        String firstDate = scanner.nextLine();
                        System.out.print("> Введите вторую дату: ");
                        String secondDate = scanner.nextLine();
                        System.out.printf("> Между датами прошло %d дней.%n",
                                new A2(firstDate, secondDate)
                                        .getDaysBetween());
                        break;
                    case "2":
                        System.out.print("> Введите день недели и число n >= 0: ");
                        String paramsString = scanner.nextLine();
                        System.out.printf("> Через n дней будет %s.%n",
                                new B2(paramsString).skipNDays());
                        break;
                    case "3":
                        new C2().testStringImplementations();
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
