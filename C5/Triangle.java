package task5.C5;

public class Triangle extends AbstractFigure implements TwoDimFigure {

    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {
        if (a <= 0.0 || b <= 0.0 || c <= 0.0) {
            throw new IncorrectRangeException("Стороны треугольника должны быть положительными");
        } else if (!isCorrectTriangle(a, b, c)) {
            throw new IncorrectRangeException("Для заданных сторон: %s, %s, %s не выполняется"
                    .formatted(a, b, c) + " неравенство треугольника");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle(double a) {
        this(a, a, a);
    }

    public Triangle() {
        this(1.0, 1.0, 1.0);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    private boolean isCorrectTriangle(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    @Override
    public String getNameOfFigure() {
        return "Треугольник";
    }

    @Override
    public double getPerimeter() {
        return (a + b + c);
    }

    @Override
    public double getSquare() {
        double p = (a + b + c) / 2;
        return Math.sqrt(
                p * (p - a) * (p - b) * (p - c)
        );
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
