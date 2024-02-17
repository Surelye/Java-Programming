package task6.C6;

public class MacGUIFactory implements GUIFactory {

    MacComponents macComponents = new MacComponents();

    public MacGUIFactory() {
        System.out.println("> Создание GUI-фабрики для Mac");
    }

    @Override
    public Interfaces.Button makeButton() {
        System.out.println("> Создание кнопки для Mac");
        return macComponents.new MacButton();
    }

    @Override
    public Interfaces.Select makeSelect() {
        System.out.println("> Создание поля выбора для Mac");
        return macComponents.new MacSelect();
    }

    @Override
    public Interfaces.TextField makeTextField() {
        System.out.println("> Создание текстового поля для Mac");
        return macComponents.new MacTextField();
    }
}
