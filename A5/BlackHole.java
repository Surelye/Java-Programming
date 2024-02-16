package task5.A5;

import java.util.Objects;

public class BlackHole extends SpaceObject implements Comparable<BlackHole> {

    private Radius SchwarzschildRadius;
    private Temperature surfaceTemperature;
    private Speed speed;

    public BlackHole() {
        super(
                "Sagittarius A*",
                new Mass(8.55E36),
                new Age(4.86E8)
        );
        this.SchwarzschildRadius = new Radius(1.27E7);
        this.surfaceTemperature = new Temperature(-273.);
        this.speed = Speed.ZERO_SPEED();
    }

    public BlackHole(String name, Mass mass, Age age, Radius SchwarzschildRadius,
                     Temperature surfaceTemperature, Speed speed) {
        super(name, mass, age);
        this.SchwarzschildRadius = SchwarzschildRadius;
        this.surfaceTemperature = surfaceTemperature;
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackHole blackHole = (BlackHole) o;
        return Objects.equals(SchwarzschildRadius, blackHole.SchwarzschildRadius) &&
                Objects.equals(surfaceTemperature, blackHole.surfaceTemperature) &&
                Objects.equals(speed, blackHole.speed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(SchwarzschildRadius, surfaceTemperature, speed);
    }

    @Override
    public String toString() {
        return "Чёрная дыра{название: %s, %s, %s, гравитационный %s}"
                .formatted(getName(), getAge(), getMass(), getSchwarzschildRadius());
    }

    @Override
    public int compareTo(BlackHole o) {
        return getMass().compareTo(o.getMass());
    }

    public Radius getSchwarzschildRadius() {
        return SchwarzschildRadius;
    }

    public void setSchwarzschildRadius(Radius SchwarzschildRadius) {
        this.SchwarzschildRadius = SchwarzschildRadius;
    }
    public Temperature getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public void setSurfaceTemperature(Temperature surfaceTemperature) {
        this.surfaceTemperature = surfaceTemperature;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }
}
