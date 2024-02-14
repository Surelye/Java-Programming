import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class B2 {

    private enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
        SATURDAY, SUNDAY;

        private static final Map<Integer, Day> lookup = new HashMap<>();

        static {
            for (Day day : EnumSet.allOf(Day.class)) {
                lookup.put(day.ordinal(), day);
            }
        }

        public static Day get(int code) {
            return lookup.get(code);
        }

        @Override
        public String toString() {
            String name = this.name();
            return name.charAt(0) +
                    name.substring(1).toLowerCase();
        }
    }

    private final Day dayOfWeek;
    private final int nDaysToSkip;

    public B2(String input) {
        String[] splitInput = input.split("\\s+");
        if (splitInput.length != 2) {
            throw new RuntimeException("Некорректный формат входных данных");
        }
        this.dayOfWeek = Day.valueOf(splitInput[0].toUpperCase());
        this.nDaysToSkip = Integer.parseInt(splitInput[1]);
        if (nDaysToSkip < 0) {
            throw new RuntimeException("Некорректное количество дней");
        }
    }

    public String skipNDays() {
        return "%s".formatted(
                Day.get(
                        (dayOfWeek.ordinal() + nDaysToSkip) % 7
                )
        );
    }
}
