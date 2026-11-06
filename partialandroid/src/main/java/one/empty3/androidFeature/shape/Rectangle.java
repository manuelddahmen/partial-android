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

package one.empty3.androidFeature.shape;

import one.empty3.library.Point3D;
import one.empty3.library.core.nurbs.ParametricCurve;

public class Rectangle extends ParametricCurve {
    private final double height;
    private final double width;
    private final double y;
    private final double x;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setIncrU(.2 / (4 + width + height));
    }

    public Point3D calculerPoint3D(double t) {
        Point3D point3D = new Point3D(
                (t >= 0 && t < 0.25) ? x + t * width * 4 : (t >= 0.25 && t < 0.50) ? x + width : (t >= 0.50 && t < 0.75) ? x + width + (0.50 - t) * width * 4
                        : (t >= 0.75 && t < 1) ? x : Double.NaN,

                (t >= 0 && t < 0.25) ? y : (t >= 0.25 && t < 0.5) ? y + height * (t - 0.25) * 4 : (t >= 0.5 && t < 0.75) ? y + height :
                        (t >= 0.75 && t < 1) ? y + height + height * (0.75 - t) * 4 : Double.NaN,
                0.0
        );
        if (point3D.get(0).equals(Double.NaN) || point3D.get(1).equals(Double.NaN))
            throw new UnsupportedOperationException("Double NaN Rectangle calculerPoint3D");
        return point3D;
    }

    public double getX() {
        return x;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getY() {
        return y;
    }
}
