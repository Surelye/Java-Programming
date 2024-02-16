package task5.A5;

import java.util.Objects;

public class Star extends SpaceObject implements Comparable<Star>, Luminous {

    private Temperature surfaceTemperature;
    private Luminosity luminosity;
    private Speed speed;
    private Composition composition;

    public Star() {
        super(
                "Солнце",
                Mass.SUN_MASS(),
                Age.SUN_AGE()
        );
        surfaceTemperature = Temperature.SUN_SURFACE_TEMPERATURE();
        luminosity = Luminosity.SUN_LUMINOSITY();
        speed = new Speed(0.027);
        composition = Composition.ZERO_COMPOSITION();
        composition.setHelium(new Mass(5.21E29));
        composition.setHydrogen(new Mass(1.47E30));
    }

    public Star(String name, Mass mass, Age age, Temperature surfaceTemperature,
                Luminosity luminosity, Speed speed, Composition composition) {
        super(name, mass, age);
        this.surfaceTemperature = surfaceTemperature;
        this.luminosity = luminosity;
        this.speed = speed;
        this.composition = composition;
    }

    public Star(Mass mass) {
        super(
                "Безымянная звезда",
                mass,
                Age.SUN_AGE()
        );
        surfaceTemperature = Temperature.SUN_SURFACE_TEMPERATURE();
        luminosity = Luminosity.SUN_LUMINOSITY();
        speed = Speed.ZERO_SPEED();
        double heliumPart = mass.getKilogram() / 4;
        double hydrogenPart = heliumPart * 3;
        composition = Composition.ZERO_COMPOSITION();
        composition.setHelium(new Mass(heliumPart));
        composition.setHydrogen(new Mass(hydrogenPart));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return Objects.equals(surfaceTemperature, star.surfaceTemperature) &&
                Objects.equals(luminosity, star.luminosity) &&
                Objects.equals(speed, star.speed) &&
                Objects.equals(composition, star.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surfaceTemperature, luminosity, speed, composition);
    }

    @Override
    public String toString() {
        return "Звезда{название: %s, %s, %s, %s}"
                .formatted(getName(), getAge(), getMass(), getLuminosity());
    }

    @Override
    public int compareTo(Star o) {
        return luminosity.compareTo(o.getLuminosity());
    }

    @Override
    public void nuclearFusion(double hydrogenFused) {
        double newHydrogenMass = composition.getHydrogen().getKilogram() - hydrogenFused;
        try {
            composition.setHydrogen(new Mass(newHydrogenMass));
        } catch (Exception e) {
            composition.setHydrogen(new Mass(0.));
        }
        composition.setHelium(new Mass(composition.getHelium().getKilogram() + hydrogenFused));
    }

    @Override
    public void emitLight() {
        double massHydrogen = composition.getHydrogen().getKilogram();
        double hydrogenFusedPerIteration = massHydrogen / 5;
        System.out.printf("> Состав звезды \"%s\" до ядерного синтеза: %s%n"
                .formatted(getName(), getComposition()));
        for (int i = 0; i < 5; ++i) {
            nuclearFusion(hydrogenFusedPerIteration);
            System.out.printf("> Состав звезды \"%s\" после %d-%cй итерации ядерного синтеза: "
                    .formatted(getName(), i + 1, (i == 2 ? 'е' : 'о')) +
                    "%s%n".formatted(getComposition()));
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("[ERROR] Не удалось приостановить поток");
            }
        }
    }

    @Override
    public SpaceObject collapse() {
        double solarMass = getMass().getSolarMass();
        if (solarMass > 40.0) {
            double blackHoleMass = getMass().getKilogram() / 2;
            return new BlackHole(
                    "Чёрная дыра звёздной массы",
                    new Mass(blackHoleMass),
                    Age.ZERO_AGE(),
                    new Radius(
                            (2 * 6.67E-20 * blackHoleMass) /
                                    (Speed.LIGHT_SPEED * Speed.LIGHT_SPEED)
                    ),
                    new Temperature(-273.),
                    Speed.ZERO_SPEED()
            );
        } else if (solarMass > 12.) {
            Composition neutronStarComposition = Composition.ZERO_COMPOSITION();
            neutronStarComposition.setHelium(new Mass(8.99E29));
            neutronStarComposition.setHydrogen(new Mass(2.88E30));
            return new Star(
                    "Нейтронная звезда",
                    new Mass(3.78E30),
                    Age.ZERO_AGE(),
                    new Temperature(379641.),
                    new Luminosity(3.08E24),
                    Speed.ZERO_SPEED(),
                    neutronStarComposition
            );
        } else {
            double heliumMass = getComposition().getHelium().getKilogram();
            Composition redGiantComposition = Composition.ZERO_COMPOSITION();
            redGiantComposition.setHelium(new Mass(heliumMass));
            return new Star(
                    "Красный гигант",
                    new Mass(heliumMass),
                    getAge(),
                    new Temperature(3500.),
                    new Luminosity(getLuminosity().getSolarLuminosity() * 10E3),
                    Speed.ZERO_SPEED(),
                    redGiantComposition
            );
        }
    }

    public SpaceObject lifecycle() {
        emitLight();
        return collapse();
    }

    public Temperature getSurfaceTemperature() {
        return surfaceTemperature;
    }

    public void setSurfaceTemperature(Temperature surfaceTemperature) {
        this.surfaceTemperature = surfaceTemperature;
    }

    public Luminosity getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(Luminosity luminosity) {
        this.luminosity = luminosity;
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
}
