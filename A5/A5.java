package task5.A5;

import java.util.ArrayList;
import java.util.List;

public class A5 {

    public void run() {
        List<SpaceObject> spaceObjects = new ArrayList<>(
                List.of(
                        new Planet(),
                        new Star(),
                        new BlackHole()
                )
        );
        for (SpaceObject spaceObject : spaceObjects) {
            System.out.printf("> %s%n", spaceObject);
        }
        System.out.printf("%n> Тестирование поверхностного и глубокого копирования%n");
        System.out.println("> ----------------------------------------------------");
        System.out.println("> Создадим новый объект и поверхностно скопируем его: ");
        SpaceObject Earth = new Planet();
        System.out.printf("> %s%n", Earth);
        SpaceObject shallowEarthCopy = Earth;
        shallowEarthCopy.setMass(new Mass(2.45E26));
        System.out.println("> Внесение изменения в копию отражается на оригинале: ");
        System.out.printf("> %s%n", Earth);
        System.out.printf("> %s%n", shallowEarthCopy);
        System.out.println("> ----------------------------------------------------");
        System.out.println("> Создадим новый объект и \"глубоко\" скопируем его: ");
        SpaceObject anotherEarth = new Planet();
        System.out.printf("> %s%n", anotherEarth);
        SpaceObject deepEarthCopy = anotherEarth.clone();
        deepEarthCopy.setAge(new Age(3.44E6));
        System.out.println("> Внесение изменения в копию не отражается на оригинале: ");
        System.out.printf("> %s%n", anotherEarth);
        System.out.printf("> %s%n%n", deepEarthCopy);

        System.out.println("> Тестирование интерфейса Luminous");
        System.out.println("> --------------------------------");
        SpaceObject redGiant = new Star().lifecycle();
        System.out.printf("> Получившийся объект: %s%n%n", redGiant);

        SpaceObject neutronStar = new Star(new Mass(Mass.SUN_MASS().getKilogram() * 20.)).lifecycle();
        System.out.printf("> Получившийся объект: %s%n%n", neutronStar);

        SpaceObject blackHole = new Star(new Mass(Mass.SUN_MASS().getKilogram() * 45.)).lifecycle();
        System.out.printf("> Получившийся объект: %s%n", blackHole);
    }

    public static void main(String[] args) {
        new A5().run();
    }
}
