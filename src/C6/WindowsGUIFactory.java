package task6.C6;

public class WindowsGUIFactory implements GUIFactory {

    WindowsComponents windowsComponents = new WindowsComponents();

    public WindowsGUIFactory() {
        System.out.println("> Создание GUI-фабрики для Windows");
    }

    @Override
    public Interfaces.Button makeButton() {
        System.out.println("> Создание кнопки для Windows");
        return windowsComponents.new WindowsButton();
    }

    @Override
    public Interfaces.Select makeSelect() {
        System.out.println("> Создание поля выбора для Windows");
        return windowsComponents.new WindowsSelect();
    }

    @Override
    public Interfaces.TextField makeTextField() {
        System.out.println("> Создание текстового поля для Windows");
        return windowsComponents.new WindowsTextField();
    }
}
