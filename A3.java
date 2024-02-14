import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A3 {

    private final String filename;
    private List<FileEntry> uniqueEntries;

    static class FileEntry {

        private final String surname;
        private final String name;
        private final String patronymic;
        private final String companyName;
        private final int companyRating;

        public FileEntry(String surname, String name, String patronymic, String companyName,
                      int companyRating) {
            this.surname = surname;
            this.name = name;
            this.patronymic = patronymic;
            this.companyName = companyName;
            this.companyRating = companyRating;
        }

        public FileEntry(String surname, String name, String companyName, int companyRating) {
            this(surname, name, "", companyName, companyRating);
        }

        public FileEntry(String surname, String companyName, int companyRating) {
            this(surname, "", "", companyName, companyRating);
        }

        public String getSurname() {
            return surname;
        }

        public String getName() {
            return name;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public int getCompanyRating() {
            return companyRating;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof FileEntry other)) {
                return false;
            }

            if (surname == null && other.surname == null ||
                    surname != null && surname.equals(other.surname)) {
                if (name == null && other.name == null ||
                        name != null && name.equals(other.name)) {
                    if (patronymic == null && other.patronymic == null ||
                            patronymic != null && patronymic.equals(other.patronymic)) {
                        if (companyName == null && other.companyName == null ||
                                companyName != null && companyName.equals(other.companyName)) {
                            return companyRating == other.companyRating;
                        }
                    }
                }
            }

            return false;
        }

        @Override
        public int hashCode() {
            return 31 + (surname == null ? 0 : surname.hashCode()) +
                    (name == null ? 0 : name.hashCode()) +
                    (patronymic == null ? 0 : patronymic.hashCode()) +
                    (companyName == null ? 0 : companyName.hashCode()) +
                    companyRating;
        }

        @Override
        public String toString() {
            return "Surname: '" + this.surname + "'" +
                    (name.isEmpty() ? "" : ", Name: '" + this.name + "'") +
                    (patronymic.isEmpty() ? "" : ", Patronymic: '" + this.patronymic + "'") +
                    ", CompanyName: '" + this.companyName + "'" +
                    ", CompanyRating: '" + this.companyRating + "'";
        }
    }

    public A3(String filename) {
        this.filename = filename;
        readFileEntries();
        Comparator<FileEntry> comparator = Comparator.comparing(FileEntry::getCompanyRating)
                .reversed()
                .thenComparing(FileEntry::getSurname)
                .thenComparing(FileEntry::getName)
                .thenComparing(FileEntry::getPatronymic);
        uniqueEntries.sort(comparator);
    }

    private FileEntry parseParams(String[] params) {
        int len = params.length;
        if (len < 3 || len > 5) {
            throw new RuntimeException("Некорректный формат данных");
        }
        String surname = params[0];
        int companyRating = Integer.parseInt(params[len - 1]);
        String companyName = params[len - 2];
        FileEntry fileEntry = null;

        switch (len) {
            case 3 -> fileEntry = new FileEntry(surname, companyName, companyRating);
            case 4 -> {
                String name = params[1];
                fileEntry = new FileEntry(surname, name, companyName, companyRating);
            }
            case 5 -> {
                String name = params[1];
                String patronymic = params[2];
                fileEntry = new FileEntry(surname, name, patronymic, companyName, companyRating);
            }
        }

        return fileEntry;
    }

    private void readFileEntries() {
        String[] params;
        Set<FileEntry> uniqueFileEntries = new HashSet<>();

        try (Scanner fileScanner = new Scanner(new File(filename))) {
            while (fileScanner.hasNext()) {
                params = fileScanner.nextLine().split("\\s+");
                uniqueFileEntries.add(parseParams(params));
            }
            this.uniqueEntries = new ArrayList<>(uniqueFileEntries);
        } catch (FileNotFoundException fnfe) {
            System.out.println("[ERROR] Указанный файл не найден.");
        }
    }

    public void printEntries() {
        for (FileEntry fileEntry : uniqueEntries) {
            System.out.printf("> %s%n", fileEntry);
        }
    }
}
