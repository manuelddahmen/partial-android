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

package one.empty3.libs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.os.Build;

import org.jetbrains.annotations.NotNull;
import one.empty3.libs.commons.IImageMp;

import java.io.*;
import java.util.Objects;

public class Image extends BitmapDrawable implements IImageMp {


    public Image(@NotNull Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setBitmap(bitmap.copy(Bitmap.Config.RGB_565, true));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setBitmap(bitmap);
        }
    }

    public Image(File file) {
        this(Objects.requireNonNull(getOrientationCorrectedBitmap(file)));
    }

    private static Bitmap getOrientationCorrectedBitmap(File file) {
        try {
            // Décoder l'image d'abord
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (bitmap == null) {
                return null;
            }

            // Lire les métadonnées EXIF
            ExifInterface exif = new ExifInterface(file.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            // Créer une matrice pour appliquer la rotation
            Matrix matrix = new Matrix();
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    matrix.postRotate(90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    matrix.postRotate(180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    matrix.postRotate(270);
                    break;
                case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    matrix.preScale(-1.0f, 1.0f);
                    break;
                case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    matrix.preScale(1.0f, -1.0f);
                    break;
                default:
                    // Pas de transformation nécessaire
                    return bitmap.copy(Bitmap.Config.RGB_565, true);
            }

            // Appliquer la transformation
            Bitmap correctedBitmap = Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            // Libérer la mémoire de l'image d'origine si une nouvelle a été créée
            if (correctedBitmap != bitmap) {
                bitmap.recycle();
            }
            correctedBitmap = correctedBitmap.copy(Bitmap.Config.RGB_565, true);

            return correctedBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // ... le reste de la classe reste inchangé

    public Image(int la, int ha, int[] pixels) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setBitmap(Bitmap.createBitmap(la, ha, Bitmap.Config.RGB_565));
        }
        for (int i = 0; i < pixels.length; i++) {
            int x = i % la;
            int y = i / la;
            int rgb = pixels[i];
            setRgb(x, y, rgb);
        }
    }
    public Image(int width, int height) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setBitmap(Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565));
        }
    }

    public Image(int width, int height, int nothing) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            setBitmap(Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565));
        }
    }

    public static Image getFromInputStream(InputStream stream) {
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        bitmap = bitmap.copy(Bitmap.Config.RGB_565, true);
        return bitmap != null ? new Image(bitmap) : null;
    }

    public static boolean saveFile(Image image, String jpg, File out, boolean shouldOverwrite) {
        if (out.exists() && !shouldOverwrite)
            return false;
        if (!out.getParentFile().exists())
            out.getParentFile().mkdirs();
        return image.saveFile(out);

    }

    @Override
    public boolean toOutputStream(OutputStream stream) {
        return false;
    }

    public static Image getFromFile(File file) {
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bitmap != null ? new Image(bitmap) : null;
    }

    @Override
    public boolean saveToFile(String path) {
        return saveFile(new File(path));
    }

    public boolean saveFile(File path) {
        switch(path.getName().substring(path.getName().lastIndexOf(".") + 1).toLowerCase()) {
            case "jpeg":
            case "jpg" : return saveFile(path, Bitmap.CompressFormat.JPEG, 100);
            case "png" : return saveFile(path, Bitmap.CompressFormat.PNG, 100);
            case "webp" : return saveFile(path, Bitmap.CompressFormat.WEBP, 100);
        }
        return saveFile(new File(path.getAbsoluteFile()+".jpg"), Bitmap.CompressFormat.JPEG, 100); // Default JPEG, quality 100
    }


    public boolean saveFile(File path, Bitmap.CompressFormat format, int quality) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            getBitmap().compress(format, quality, fos);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Error saving image: " + e.getMessage(), e);
        }
    }

    @Override
    public int getWidth() {
        return getBitmap().getWidth(); // Or handle null Bitmap
    }

    @Override
    public int getHeight() {
        return getBitmap().getHeight(); // Or handle null Bitmap
    }

    public int getRgb(int x, int y) {
        if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
            return getBitmap().getPixel(x, y) | 0xFF000000; // Or handle out-of-bounds
        }
        return -1;
    }

    @Override
    public void setImageToMatrix(int[][] imagetoMatrix) {

    }

    @Override
    public int[][] getMatrix() {
        return new int[0][];
    }

    public void setRgb(int x, int y, int rgb) {
        if (x >= 0 && y >= 0 && x < getWidth() && y < getHeight()) {
            getBitmap().setPixel(x, y, rgb|0xFF000000); // Or handle out-of-bounds
        }
    }

    // Optional: Add Color-based methods
    public Color getColor(int x, int y) {
        return new Color(getBitmap().getPixel(x, y)); // Or handle out-of-bounds
    }

    public void setColor(int x, int y, Color color) {
        setRgb(x, y, color.getRgb());
    }

    @NotNull
    public final Bitmap getImage() {
        return getBitmap();
    }
}