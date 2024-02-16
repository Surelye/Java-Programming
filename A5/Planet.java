package task5.A5;

import java.util.Objects;

public class Planet extends SpaceObject {

    private Radius radius;
    private Temperature averageTemperature;
    private Speed speed;
    private Composition composition;
    private SpaceObject orbiting;

    public Planet() {
        super(
                "Земля",
                new Mass(5.97E24),
                new Age(4.5E9)
        );
        radius = new Radius(6371.);
        averageTemperature = new Temperature(7.53);
        speed = new Speed(29.6);
        composition = new Composition(
                new Mass(1.47E24),
                new Mass(4.50E24),
                new Mass(4.81E16),
                Mass.ZERO_MASS(),
                new Mass(1.08E18),
                new Mass(2.15E15),
                new Mass(1.43E21),
                new Mass(4.02E18),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS(),
                Mass.ZERO_MASS()
        );
        orbiting = new Star();
    }

    public Planet(String name, Mass mass, Age age, Radius radius, Temperature temperature,
                  Speed speed, SpaceObject orbiting) {
        super(name, mass, age);
        this.radius = radius;
        this.averageTemperature = temperature;
        this.speed = speed;
        this.orbiting = orbiting;
    }

    @Override
    public String toString() {
        return "Планета{название: %s, %s, %s, %s}"
                .formatted(getName(), getAge(), getMass(), getRadius());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(radius, planet.radius) &&
                Objects.equals(averageTemperature, planet.averageTemperature) &&
                Objects.equals(speed, planet.speed) &&
                Objects.equals(orbiting, planet.orbiting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, averageTemperature, speed, orbiting);
    }

    public Radius getRadius() {
        return radius;
    }

    public void setRadius(Radius radius) {
        this.radius = radius;
    }

    public Temperature getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Temperature temperature) {
        averageTemperature = temperature;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Composition getComposition() {
        return composition;
    }

    public void setComposition(Composition composition) {
        this.composition = composition;
    }

    public SpaceObject getOrbiting() {
        return orbiting;
    }

    public void setOrbiting(SpaceObject spaceObject) {
        orbiting = spaceObject;
    }
}
