package task5.A5;

public class Luminosity implements Comparable<Luminosity> {

    private double solarLuminosity;
    private double watt;

    public static Luminosity ZERO_LUMINOSITY() {
        return new Luminosity(0.);
    }

    public static Luminosity SUN_LUMINOSITY() {
        return new Luminosity(3.85E26);
    }

    @Override
    public int compareTo(Luminosity o) {
        return Double.compare(solarLuminosity, o.getSolarLuminosity());
    }

    @Override
    public String toString() {
        return "яркость: %6.2e ватт".formatted(watt);
    }

    public Luminosity(double luminosity) {
        setNewLuminosities(luminosity);
    }

    private void setNewLuminosities(double luminosity) {
        if (luminosity >= 0.) {
            solarLuminosity = luminosity / (3.85E26);
            watt = luminosity;
        }
    }

    public double getSolarLuminosity() {
        return solarLuminosity;
    }

    public double getWatt() {
        return watt;
    }

    public void setWatt(double watt) {
        setNewLuminosities(watt);
    }
}
