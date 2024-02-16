package task5.A5;

public class Age implements Comparable<Age> {

    private double gigayear;
    private double megayear;
    private double year;
    private double month;
    private double day;
    private double hour;
    private double min;
    private double sec;

    public static Age ZERO_AGE() {
        return new Age(0.);
    }

    public static Age SUN_AGE() {
        return new Age(4.66E9);
    }

    public Age(double age) {
        setNewAges(age);
    }

    private void setNewAges(double age) {
        if (age >= 0.) {
            year     = age             ;
            megayear = year / 1_000_000;
            gigayear = megayear / 1_000;
            month    = year * 12       ;
            day      = month * 31      ;
            hour     = day * 24        ;
            min      = hour * 60       ;
            sec      = min * 60        ;
        } else {
            throw new RuntimeException("Некорректное значение возраста");
        }
    }

    @Override
    public int compareTo(Age o) {
        return Double.compare(megayear, o.getMegayear());
    }

    @Override
    public String toString() {
        return "возраст: %6.2e лет".formatted(year);
    }

    public double getGigayear() {
        return gigayear;
    }

    public double getMegayear() {
        return megayear;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        setNewAges(year);
    }

    public double getMonth() {
        return month;
    }

    public double getDay() {
        return day;
    }

    public double getHour() {
        return hour;
    }

    public double getMin() {
        return min;
    }

    public double getSec() {
        return sec;
    }
}
