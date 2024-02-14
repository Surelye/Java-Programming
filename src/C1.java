import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class C1 {

    private static final String pattern = "[А-ЯЁA-Z&&[^ЪЬ]][а-яёa-z]";
    private static final Pattern surnameMatcher = Pattern
            .compile("%s*.*".formatted(pattern));
    private static final Pattern surnameNameMatcher = Pattern
            .compile("%s* %s*.*".formatted(pattern, pattern));
    private static final Pattern surnameNamePatronymicMatcher = Pattern
            .compile("%s* %s* %s*.*".formatted(pattern, pattern, pattern));

    private static String constructString(String params) {
        String[] splitParams = params.split("\\s+");

        if (surnameNamePatronymicMatcher.matcher(params).matches()) {
            String surname = splitParams[0];
            String name = splitParams[1];
            String patronymic = splitParams[2];
            return "%s %s %s -> %s %c. %c.".formatted(surname, name, patronymic, name,
                    patronymic.charAt(0), surname.charAt(0));
        } else if (surnameNameMatcher.matcher(params).matches()) {
            String surname = splitParams[0];
            String name = splitParams[1];
            return "%s %s -> %s %c.".formatted(surname, name, name, surname.charAt(0));
        } else if (surnameMatcher.matcher(params).matches()) {
            String surname = splitParams[0];
            return "%s -> %c.".formatted(surname, surname.charAt(0));
        } else {
            return " -> ";
        }
    }

    public static void fileInputOutput() {
        Scanner scanner = new Scanner(System.in);
        String fileName;
        String params;

        System.out.println("> Для выхода при вводе имени файла напишите '/exit'.\n");
        while (true) {
            System.out.print("> Введите имя файла (по умолчанию input.txt): ");
            fileName = scanner.nextLine();
            if (fileName.isBlank()) {
                fileName = "C:\\Users\\sgnot\\Downloads\\input.txt";
            } else if (fileName.equals("/exit")) {
                break;
            }

            try (Scanner fileScanner = new Scanner(new File(fileName))) {
                while (fileScanner.hasNext()) {
                    params = fileScanner.nextLine();
                    System.out.println(constructString(params));
                }
                System.out.println();
            } catch (FileNotFoundException fnfe) {
                System.out.println("[ERROR] Указанный файл не найден.");
            }
        }
        scanner.close();
    }
}
