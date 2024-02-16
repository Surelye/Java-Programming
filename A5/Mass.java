package task5.A5;

import java.util.Objects;

public class Mass implements Comparable<Mass> {

    private double MilkyWayMass;
    private double solarMass;
    private double JupiterMass;
    private double EarthMass;
    private double MoonMass;
    private double EarthOceanMass;
    private double EarthAtmosphereMass;
    private double MarsAtmosphereMass;
    private double kilogram;
    private double gram;
    private double milligram;

    public static Mass ZERO_MASS() {
        return new Mass(0.);
    }

    public static Mass SUN_MASS() {
        return new Mass(1.99E30);
    }

    public Mass(double mass) {
        setNewMasses(mass);
    }

    private void setNewMasses(double mass) {
        if (mass >= 0.) {
            MilkyWayMass        = mass / (2.98E42);
            solarMass           = mass / (1.99E30);
            JupiterMass         = mass / (1.90E27);
            EarthMass           = mass / (5.97E24);
            MoonMass            = mass / (7.35E22);
            EarthOceanMass      = mass / (1.34E21);
            EarthAtmosphereMass = mass / (5.20E18);
            MarsAtmosphereMass  = mass / (2.50E16);
            kilogram            = mass            ;
            gram                = mass * 10E3     ;
            milligram           = mass * 10E6     ;
        } else {
            throw new RuntimeException("Некорректное значение массы: %s"
                    .formatted(mass));
        }
    }

    @Override
    public int compareTo(Mass o) {
        return Double.compare(EarthMass, o.getEarthMass());
    }

    @Override
    public String toString() {
        return "масса: %6.2e кг".formatted(kilogram);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mass mass = (Mass) o;
        return Double.compare(EarthMass, mass.EarthMass) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(EarthMass);
    }

    public double getMilkyWayMass() {
        return MilkyWayMass;
    }

    public double getSolarMass() {
        return solarMass;
    }

    public double getJupiterMass() {
        return JupiterMass;
    }

    public double getEarthMass() {
        return EarthMass;
    }

    public double getMoonMass() {
        return MoonMass;
    }

    public double getEarthOceanMass() {
        return EarthOceanMass;
    }

    public double getEarthAtmosphereMass() {
        return EarthAtmosphereMass;
    }

    public double getMarsAtmosphereMass() {
        return MarsAtmosphereMass;
    }

    public double getKilogram() {
        return kilogram;
    }

    public void setKilogram(double mass) {
        setNewMasses(mass);
    }

    public double getGram() {
        return gram;
    }

    public double getMilligram() {
        return milligram;
    }
}
