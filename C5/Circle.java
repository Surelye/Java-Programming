package task5.C5;

public class Circle extends AbstractFigure implements TwoDimFigure {

    private final double radius;

    public Circle(double radius) {
        if (radius <= 0.0) {
            throw new IncorrectRangeException("Радиус окружности должен быть положительным");
        }
        this.radius = radius;
    }

    public Circle() {
        this(1.0);
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getNameOfFigure() {
        return "Окружность";
    }

    @Override
    public double getPerimeter() {
        return 2.0 * Math.PI * radius;
    }

    @Override
    public double getSquare() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }
}
