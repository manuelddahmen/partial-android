package one.empty3.libs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;

import one.empty3.libs.commons.IImageMp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Image extends BitmapDrawable implements IImageMp {
    public Image(Bitmap image) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setBitmap(image);
        }
    }

    public int getRgb(int x, int y) {
        return getBitmap().getPixel(x, y);
    }

    @Override
    public void setImageToMatrix(int[][] ints) {

    }

    @Override
    public int[][] getMatrix() {
        return new int[0][];
    }

    @Override
    public int getWidth() {
        return getBitmap().getWidth();
    }

    @Override
    public int getHeight() {
        return getBitmap().getHeight();
    }

    public void setRgb(int x, int y, int rgb) {
        getBitmap().setPixel(x, y, rgb);
    }
    public static Image loadFile(File path) {
        Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(path));
        return new Image(bitmap);
    }
    public void saveFile(File path) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            try {
                getBitmap().compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(path));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}