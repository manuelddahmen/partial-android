package one.empty3.libs;

import one.empty3.libs.commons.IColorMp;

public class Color extends android.graphics.Color implements IColorMp {
    private android.graphics.Color colorObject;


    public Color(android.graphics.Color color) {
        this.colorObject = color;
    }

    @Override
    public IColorMp getColorObject() {
        if(colorObject instanceof Color)
            return (Color)colorObject;
        else
            return new Color(colorObject);
    }

    public int getColor() {
        return colorObject.toArgb();
    }

    public void setColor(int i) {
        setRGB(i);
    }

    public void setColor(android.graphics.Color color) {
        this.colorObject = color;
    }

    public void setRGB(int rgb) {
        this.colorObject = android.graphics.Color.valueOf(rgb);
    }

    public void setRGB(int r, int g, int b) {
        this.colorObject = android.graphics.Color.valueOf(r, g, b);
    }

    public void setRgb(int rgb) {
        this.colorObject = android.graphics.Color.valueOf(rgb);
    }
    public int getRGB() {
        return colorObject.toArgb();
    }
    public int getRed() {
        return (int)(colorObject.red()/256);
    }
    public int getGreen() {
        return (int)(colorObject.green()/256);
    }
    public int getBlue() {
        return (int) (colorObject.blue() / 256);
    }
    public int getAlpha() {
        return (int) (colorObject.alpha() / 256);
    }
}
