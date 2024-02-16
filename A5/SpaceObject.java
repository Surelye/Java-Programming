package task5.A5;

public abstract class SpaceObject implements Cloneable {

    private String name;
    private Mass mass;
    private Age age;

    abstract public boolean equals(Object o);
    abstract public int hashCode();
    abstract public String toString();

    public SpaceObject(String name, Mass mass, Age age) {
        this.name = name;
        this.mass = mass;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mass getMass() {
        return mass;
    }

    public void setMass(Mass mass) {
        this.mass = mass;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    @Override
    public SpaceObject clone() {
        try {
            return (SpaceObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("[ERROR] Клонирование не удалось");
        }
    }
}
