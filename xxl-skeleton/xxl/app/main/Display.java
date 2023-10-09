package xxl.app.main;

import java.util.Collection;

public class Display {
    private StringBuilder message;

    public Display() {
        this.message = new StringBuilder();
    }

    public Display add(Object obj) {
        message.append(obj.toString());
        return this;
    }

    public Display addLine(String text) {
        message.append(text).append("\n");
        return this;
    }

    public Display addAll(Collection<?> items) {
        for (Object item : items) {
            addLine(item.toString());
        }
        return this;
    }

    public void clear() {
        message.setLength(0);
    }

    public void popup(Object obj) {
        System.out.println(obj.toString());
    }

    public void display() {
        System.out.print(message.toString());
        clear();
    }
}
