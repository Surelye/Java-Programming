package task5.C5;

public class Rectangle extends AbstractFigure implements TwoDimFigure {

    private final double a;
    private final double b;

    public Rectangle(double a, double b) {
        if (a <= 0.0 || b <= 0.0) {
            throw new IncorrectRangeException("Стороны прямоугольника должны быть положительными");
        }
        this.a = a;
        this.b = b;
    }

    public Rectangle(double a) {
        this(a, a);
    }

    public Rectangle() {
        this(1.0, 1.0);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public String getNameOfFigure() {
        return "Прямоугольник";
    }

    @Override
    public double getPerimeter() {
        return 2.0 * (a + b);
    }

    @Override
    public double getSquare() {
        return a * b;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
