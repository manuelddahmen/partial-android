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

package matrix;

import android.graphics.Bitmap;

import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

public abstract class FilterPixM extends PixM {
    public final static int NORM_NONE = 0;
    public final static int NORM_MEAN = 1;
    public final static int NORM_MAX = 2;
    public final static int NORM_FLOOR_0 = 4;
    public final static int NORM_FLOOR_1 = 8;
    public final static int NORM_CUSTOM = 16;

    public int getNormalize() {
        return normalize;
    }

    public FilterPixM setNormalize(int normalize) {
        this.normalize = normalize;
        return this;
    }

    public Image getBitmap() {
        return super.getBitmap();
    }

    private int normalize = NORM_NONE;

    public FilterPixM(int l, int c) {
        super(l, c);
    }

    public FilterPixM(Image image) {
        super(image);
    }

    public FilterPixM(PixM image) {
        super(image.getImage());
    }

    public abstract double filter(double i, double i1);

}
