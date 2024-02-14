import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        label:
        while (true) {
            System.out.print("""
                    > Введите номер задания:
                    [1] -- Вывод "Hello, World!"
                    [2] -- Пример использования BigInteger и BigDecimal
                    [3] -- Ввод/вывод из файла
                    [0] -- Выход
                    
                    """);
            System.out.print("> Вводите: ");
            input = scanner.nextLine();

            switch (input) {
                case "0":
                    break label;
                case "1":
                    A1.printHelloWorld();
                    break;
                case "2":
                    B1.binaryOp();
                    break;
                case "3":
                    C1.fileInputOutput();
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
                    break;
            }
            System.out.println();
        }
    }
}
