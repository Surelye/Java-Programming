# Задача №5
## Задание №1. Классы и интерфейсы

Выбрать объект из реального мира и реализовать его в в виде класса. У него должны быть хранимые состояния и методы, регулирующие поведение объекта.

Класс реализует интерфейсы, и от него наследуются классы, расширяющие поведение объекта.

## Пример

interface 2DFigure {getPerimeter(); getSquare();}

abstract class AbstractFigure {abstract String getNameOfFigure();}

class Line extends AbstractFigure implements 2DFigure
class Circle extends AbstractFigure implements 2DFigure
class Triangle extends AbstractFigure implements 2DFigure
class Rectangle extends AbstractFigure implements 2DFigure

## Задание №2. Работа с @Override

Реализовать в выбранном классе методы hashCode(), compareTo(), toString(), методы поверхностной и глубокой копии.

Ветка не содержит отдельного каталога для решения данного задания, т.к. оно фигурирует при решении A5 и C5.

## Задание №3. Полиморфизм

Продемонстрировать работу полиморфизма на реализованных классах. В примере классы Line, Circle, Rectangle, Triangle должны реализовать методы getPerimeter() и getSquare() для подсчета периметра и площади. 

Соответственно, если сложить их в контейнер List<2DFigure> list и для каждого элемента figure вызвать figure.getPerimeter() и figure.getSquare(), то должны получиться разные результаты.
