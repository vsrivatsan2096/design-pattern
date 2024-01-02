package design.pattern.behavioural;

import common.helper.UIElement;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link Mediator} is used to decouple the communication between the objects and
 *              let mediator object to handle the communication between group of objects
 * */
public interface Mediator {}

class UIElementMediator implements Mediator {
    private final List<UIElement> registeredUIElements = new ArrayList<>();
    public void valueChanged(UIElement element) {
        registeredUIElements.stream().filter(e-> !e.equals(element)).forEach(e -> e.valueChanged(element));
    }

    public void registerUIElement(UIElement element) {
        registeredUIElements.add(element);
    }

    public static void main(String[] args) {
        UIElementMediator mediator = new UIElementMediator();

        TextBoxUIElement element1 = new TextBoxUIElement(mediator);
        LabelUIElement element2 = new LabelUIElement();

        mediator.registerUIElement(element1);
        mediator.registerUIElement(element2);

        System.out.println(element1);
        System.out.println(element2);

        element1.setValue("new value");


        System.out.println(element1);
        System.out.println(element2);
    }
}

class TextBoxUIElement extends UIElement {
    private final UIElementMediator mediator;

    public TextBoxUIElement(UIElementMediator mediator) {
        super("TextBox");
        super.value = "Sample text box";
        this.mediator = mediator;
    }

    public void setValue(String value) {
        super.value = value;
        mediator.valueChanged(this);
    }

    @Override
    public void valueChanged(UIElement element) {
        super.value = element.getValue();
    }

    @Override
    public String toString() {
        return getName() + " " + super.value;
    }
}

class LabelUIElement extends UIElement {

    public LabelUIElement() {
        super("Label");
        super.value = "Sample label";
    }

    @Override
    public void valueChanged(UIElement element) {
        super.value = element.getValue();
    }

    @Override
    public String toString() {
        return getName() + " " + super.value;
    }
}
