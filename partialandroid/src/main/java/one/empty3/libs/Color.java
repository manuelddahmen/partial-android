package one.empty3.libs;

import java.lang.annotation.Annotation;

import one.empty3.libs.partialandroid.Colors;

public class Color {
    private Colors color;

    public Color(Colors color) {
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }
}
