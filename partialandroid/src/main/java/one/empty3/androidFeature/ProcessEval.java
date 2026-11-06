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

import java.io.File;
import java.util.ArrayList;

public abstract class ProcessEval {
    private int width = 24;
    private int height = 24;
    private ArrayList<File> files = new ArrayList<>();
    private ArrayList<matrix.PixM> pixMaps = new ArrayList<>();
    private ArrayList<double[]> features = new ArrayList<>();
    private double[] x;
    private double[] y;
    private double[] w;
    private double[] w1;
    private double[] b;
    private double[][] candidatesVectors;
    private double[][] detectedAsPositives;
    private double[][] detectedAsNegatives;

    public ProcessEval(int width, int height) {

    }
    // y values  -1 à 1
    // xi = pixels

    /***
     * f(x) = w . x  + b // Hyperplane separating POS and NEG
     * w'= SOMME ( d'i yi xi)
     *f(x) = SOMME (d'yi * (xi . x ) + b
     * w' = SOMME (d'i yi xi)
     *
     * f(x) = SOMME (d'i yi (phi(xi) . phi(x)) + B
     *
     *
     */
    public void addFile(File file) {
        files.add(file);
    }

    public void addFiles(File[] files) {
        for (int i = 0; i < files.length; i++) {
            this.files.add(files[i]);
        }
    }

    public void setFeature(double[] f) {
        this.features.add(f);
    }

    private double[] add(double[] a, double[] b) {
        double[] c = new double[x.length];
        for (int i = 0; i < a.length; i++)
            c[i] = a[i] + b[i];
        return a;
    }

    private double[] dotOuter(double[] x, double[] w) {
        double[] a = new double[x.length];
        for (int i = 0; i < a.length; i++)
            a[i] = x[i] * w[i];
        return a;
    }

    public double matchFeature(double[] x, double[] f) {
        double sumFeaturing = 0;
        for (int i = 0; i < x.length; i++) {
            sumFeaturing += f[i] * x[i];
        }
        double sumWithoutFeaturing = 0;
        for (int i = 0; i < x.length; i++) {
            sumWithoutFeaturing += f[i] * x[i];
        }
        return sumFeaturing - sumWithoutFeaturing;
    }
}
