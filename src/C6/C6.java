package task6.C6;

import java.util.Scanner;

public class C6 {

    private void testFactory(GUIFactory guiFactory) {
        guiFactory.makeButton();
        guiFactory.makeSelect();
        guiFactory.makeTextField();
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            while (true) {
                System.out.println("""
                        > Введите код операционной системы:
                        > [1] -- Windows
                        > [2] -- Linux
                        > [3] -- Mac
                        > [0] -- Выход
                        """
                );
                System.out.print("> Вводите: ");
                input = scanner.nextLine().trim();
                if (input.equals("1")) {
                    testFactory(new WindowsGUIFactory());
                } else if (input.equals("2")) {
                    testFactory(new LinuxGUIFactory());
                } else if (input.equals("3")) {
                    testFactory(new MacGUIFactory());
                } else if (input.equals("0")) {
                    break;
                } else {
                    System.out.println("> Некорректный ввод! Попробуйте снова.");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.printf("> В ходе работы программы произошло исключение: %s", e.getMessage());
        }
    }

    public static void main(String[] args) {
        new C6().run();
    }
}
