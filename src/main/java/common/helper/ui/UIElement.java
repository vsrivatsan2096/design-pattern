package common.helper.ui;

public abstract class UIElement {
    private final String name;
    protected String value;

    public UIElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public abstract void valueChanged(UIElement element);
}
