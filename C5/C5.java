package task5.C5;

import java.util.ArrayList;
import java.util.List;

public class C5 {

    public static void main(String[] args) {
        List<TwoDimFigure> twoDimFigures = new ArrayList<>(
                List.of(
                        new Line(),
                        new Circle(),
                        new Rectangle(),
                        new Triangle()
                )
        );
        for (TwoDimFigure figure : twoDimFigures) {
            System.out.printf("> Фигура \"%s\" имеет периметр: %s и площадь: %s.%n",
                    figure, figure.getPerimeter(), figure.getSquare()
            );
        }
    }
}
