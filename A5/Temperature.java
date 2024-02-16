package task5.A5;

public class Temperature implements Comparable<Temperature> {

    private double kelvin;
    private double degreeFahrenheit;
    private double degreeCelsius;

    public static Temperature ABSOLUTE_ZERO() {
        return new Temperature(-273.15);
    }

    public static Temperature SUN_SURFACE_TEMPERATURE() {
        return new Temperature(5506.);
    }

    public Temperature(double temperature) {
        setNewTemperatures(temperature);
    }

    private void setNewTemperatures(double temperature) {
        if (temperature >= -273.15) {
            kelvin = temperature + 273.15;
            degreeFahrenheit = (temperature * 9 / 5) + 32;
            degreeCelsius = temperature;
        } else {
            throw new RuntimeException("Некорректное значение температуры");
        }
    }

    public double getKelvin() {
        return kelvin;
    }

    public double getDegreeFahrenheit() {
        return degreeFahrenheit;
    }

    public double getDegreeCelsius() {
        return degreeCelsius;
    }

    public void setDegreeCelsius(double temperature) {
        setNewTemperatures(temperature);
    }

    @Override
    public int compareTo(Temperature o) {
        return Double.compare(kelvin, o.getKelvin());
    }
}
