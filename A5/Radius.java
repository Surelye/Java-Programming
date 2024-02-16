package task5.A5;

public class Radius implements Comparable<Radius> {

    private double MilkyWayRadius;
    private double parsec;
    private double lightYear;
    private double astronomicalUnit;
    private double solarRadius;
    private double lunarDistance;
    private double lightSecond;
    private double JupiterRadius;
    private double EarthRadius;
    private double MoonRadius;
    private double kilometer;
    private double meter;
    private double centimeter;
    private double millimeter;

    public static Radius ZERO_RADIUS() {
        return new Radius(0.);
    }

    public Radius(double radius) {
        setNewRadii(radius);
    }

    private void setNewRadii(double radius) {
        if (radius >= 0.) {
            MilkyWayRadius   = radius / (5.20E17);
            parsec           = radius / (3.08E13);
            lightYear        = radius / (9.46E12);
            astronomicalUnit = radius / (1.49E8);
            solarRadius      = radius / 696_340;
            lunarDistance    = radius / 384_400;
            lightSecond      = radius / 299_792;
            JupiterRadius    = radius / 69_911;
            EarthRadius      = radius / 6371;
            MoonRadius       = radius / 1737;
            kilometer        = radius;
            meter            = radius * 1E3;
            centimeter       = radius * 1E5;
            millimeter       = radius * 1E6;
        } else {
            throw new RuntimeException("Некорректное значение радиуса");
        }
    }

    public double getMilkyWayRadius() {
        return MilkyWayRadius;
    }

    public double getParsec() {
        return parsec;
    }

    public double getLightYear() {
        return lightYear;
    }

    public double getAstronomicalUnit() {
        return astronomicalUnit;
    }

    public double getSolarRadius() {
        return solarRadius;
    }

    public double getLunarDistance() {
        return lunarDistance;
    }

    public double getLightSecond() {
        return lightSecond;
    }

    public double getJupiterRadius() {
        return JupiterRadius;
    }

    public double getEarthRadius() {
        return EarthRadius;
    }

    public double getMoonRadius() {
        return MoonRadius;
    }

    public double getKilometer() {
        return kilometer;
    }

    public void setKilometer(double radius) {
        setNewRadii(radius);
    }

    public double getMeter() {
        return meter;
    }

    public double getCentimeter() {
        return centimeter;
    }

    public double getMillimeter() {
        return millimeter;
    }

    @Override
    public int compareTo(Radius o) {
        return Double.compare(EarthRadius, o.getEarthRadius());
    }

    @Override
    public String toString() {
        return "радиус: %6.2e км".formatted(kilometer);
    }
}
