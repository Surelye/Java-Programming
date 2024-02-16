package task5.C5;

public class Line extends AbstractFigure implements TwoDimFigure {

    private final double length;

    public Line(double length) {
        if (length <= 0.0) {
            throw new IncorrectRangeException("Длина прямой должна быть положительной");
        }
        this.length = length;
    }

    public Line() {
        this(1.0);
    }

    public double getLength() {
        return length;
    }

    @Override
    public String getNameOfFigure() {
        return "Прямая";
    }

    @Override
    public double getPerimeter() {
        return length;
    }

    @Override
    public double getSquare() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "Line{" +
                "length=" + length +
                '}';
    }
}
