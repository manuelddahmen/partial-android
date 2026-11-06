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

import matrix.FilterPixM;
import matrix.PixM;

public class TrueHarris extends FilterPixM {

    public TrueHarris(PixM pix) {
        super(pix);
    }

    public double filter(double x, double y) {
        int i = (int) (float) x, j = (int) (float) y;
        double gx = get(i + 1, j) - get(i, j);
        double gy = get(i, j + 1) + get(i, j);
        double Sx2 = ((get(i + 1, j) - get(i, j)) - (get(i, j) - get(i - 1, j))) * get(i, j);
        double Sy2 = ((get(i, j + 1) - get(i, j)) - (get(i, j) - get(i, j - 1))) * get(i, j);
        double Ix = gx * get(i, j);
        double Iy = gy * get(i, j);
        double Ix2 = Ix * Ix;
        double Iy2 = Iy * Iy;
        double Ixy = Ix * Iy;
        double sSx2 = gx * Ix;
        double sSy2 = gy * Iy;
        double sSxy = Math.sqrt((gx + gy) / 2 * get(i, j));// Robert Collins

        //double r = (sSx2 * sSy2 - sSxy * sSxy);// / (sSx2 + sSy2);
        //double r = (Ix2 + Iy2 - Ix * Iy - Ixy) ;// / (sSx2 + sSy2);
        double r = Math.abs(0.5 - get(i, j));
        return r;
    }

    public double pow2(double a) {
        return a * a;
    }
}
