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

package one.empty3.androidFeature;

import android.graphics.Color;

import matrix.FilterPixM;
import matrix.PixM;

public class IsleFilterPixM
        extends FilterPixM {
    private Color selColor;
    private Color oppositeColor;
    private double threshold;
    private float selComps[] = new float[4];
    private float oppComps[] = new float[4];

    public IsleFilterPixM(PixM pix) {
        super(pix);
    }

    public boolean selectPoint(int x, int y) {
        float[] sel = new float[3];
        getColor(x, y, sel);
        double d = 0;
        for (int i = 0; i < 3; i++)
            d += (sel[i] - selComps[i])
                    * (sel[i] - selComps[i]);
        return Math.sqrt(d) < threshold ? true : false;
    }

    public void setCValues(Color background,
                           Color sel, double threshold) {
        this.oppositeColor = background;
        this.selColor = sel;
        this.threshold = threshold;
        selColor.getComponents(this.selComps);
        oppositeColor.getComponents(this.oppComps);

    }

    public double filter(double x, double y) {
        return 0.0;
    }

    public void filter() {
    }
}
