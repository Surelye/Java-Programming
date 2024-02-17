package task6.C6;

public class LinuxGUIFactory implements GUIFactory {

    LinuxComponents linuxComponents = new LinuxComponents();

    public LinuxGUIFactory() {
        System.out.println("> Создание GUI-фабрики для Linux");
    }

    @Override
    public Interfaces.Button makeButton() {
        System.out.println("> Создание кнопки для Linux");
        return linuxComponents.new LinuxButton();
    }

    @Override
    public Interfaces.Select makeSelect() {
        System.out.println("> Создание поля выбора для Linux");
        return linuxComponents.new LinuxSelect();
    }

    @Override
    public Interfaces.TextField makeTextField() {
        System.out.println("> Создание текстового поля для Linux");
        return linuxComponents.new LinuxTextField();
    }
}