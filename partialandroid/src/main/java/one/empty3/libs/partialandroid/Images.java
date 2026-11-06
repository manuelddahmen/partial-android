/*
 * Licensed to End Users from Google Play Store and Apple App Store under the following terms:
 *
 * This app is provided "as is" without any warranties or guarantees of any kind, either express or implied. The developers and contributors of this app are not responsible for any damages, losses, or issues that may arise from the use of this app.
 * By downloading, installing, or using this app, you agree to the following terms:
 * 1. You may use this app for personal, or non-commercial purposes only or commercial purposes.
 * 2. You may not modify, distribute, or create derivative works of this app without
 *     prior written permission from the developers.
 * 3. You may not reverse engineer, decompile, or disassemble this app.
 *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 *
 * Copyright 2026-2026 Empty3 Benelux Trademark.
 */

package one.empty3.libs.partialandroid;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Images {
    private Bitmap image;

    public Images(Bitmap image) {
        this.image = image;
    }

    public Images(int x, int y) {
        this.image = Bitmap.createBitmap(x, y, Bitmap.Config.RGB_565);
    }

    public static void save(Images images, File path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path.getAbsolutePath());
            images.getImage().compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Images load(File path) {
        return new Images(BitmapFactory.decodeFile(path.getAbsolutePath()));
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getRgb(int x, int y) {
        return image.getPixel(x, y);
    }

    public void setRgb(int x, int y, int rgb) {
        image.setPixel(x, y, rgb);
    }
}