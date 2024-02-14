import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class A2 {

    private final LocalDate firstDate;
    private final LocalDate secondDate;

    private String pad(String num) {
        return "%s%s".formatted(
                (num.length() == 1) ? "0" : "",
                num
        );
    }

    private LocalDate craftDate(String date) {
        String[] splitDate = date.split("\\s+");
        if (splitDate.length != 3) {
            throw new RuntimeException("Некорректный формат даты");
        }
        String year = splitDate[0],
                month = splitDate[1],
                day = splitDate[2];

        return LocalDate.parse("%s-%s-%s"
                .formatted(year, pad(month), pad(day)));
    }

    public A2(String firstDate, String secondDate) {
        this.firstDate = craftDate(firstDate);
        this.secondDate = craftDate(secondDate);
    }

    public long getDaysBetween() {
        return Math.abs(ChronoUnit.DAYS.between(firstDate, secondDate));
    }
}
