package task5.A5;

public class Speed implements Comparable<Speed> {

    private double lightspeed;
    private double kilometerPerSecond;
    private double meterPerSecond;
    private double kilometerPerHour;

    public static Speed ZERO_SPEED() {
        return new Speed(0.);
    }

    static final double LIGHT_SPEED = 299_792.458;

    public Speed(double speed) {
        setNewSpeeds(speed);
    }

    private void setNewSpeeds(double speed) {
        if (speed >= 0.) {
            lightspeed         = speed / 299_792;
            kilometerPerSecond = speed;
            meterPerSecond     = speed * 1E3;
            kilometerPerHour   = speed * 3600;
        } else {
            throw new RuntimeException("Некорректное значение скорости");
        }
    }

    public double getLightspeed() {
        return lightspeed;
    }

    public double getKilometerPerSecond() {
        return kilometerPerSecond;
    }

    public void setKilometerPerSecond(double speed) {
        setNewSpeeds(speed);
    }

    public double getMeterPerSecond() {
        return meterPerSecond;
    }

    public double getKilometerPerHour() {
        return kilometerPerHour;
    }

    @Override
    public int compareTo(Speed o) {
        return Double.compare(kilometerPerSecond, o.getKilometerPerSecond());
    }
}
