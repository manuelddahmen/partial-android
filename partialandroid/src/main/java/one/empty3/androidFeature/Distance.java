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

/*
 * Programmed by Shephalika Shekhar
 * class containing methods to calculate distance between two points with features
 * based on distance metric
 */
public class Distance {
    public static float[] r;

    public Distance(float x, float y) {
        float rx = 1f / (float) x;
        float ry = 1f / (float) y;
        r = new float[]{1f / 10f * x, 1f / 10f * y, 1f, 1f, 1f};
    }

    public static double eucledianDistance(double[] point1, double[] point2) {
        double sumColor = 0.01;
        double sumSpace = 0.01;
        if (point1.length < 5) {
            System.out.println("kmeans distance error point1" + point1.length);
            System.exit(-1);
        }
        if (point2.length < 5) {
            System.out.println("kmeans distance error point2" + point2.length);

            System.exit(-1);

        }
        double k = 0.05;
        /*for (int i = 0; i < 2; i++) {
            double comp = Math.exp(-(point1[i] - point2[i]) * (point1[i] - point2[i]));
            if (Double.isNaN(comp)
                    || Double.isInfinite(comp))
                comp = 0;
            sumSpace += comp;
        }

        for (int i = 2; i < 5; i++) {
            sumColor += ((point1[i] - point2[i]) * (point1[i] - point2[i]));
        }*/
        sumSpace = 0.0;
        for (int i = 0; i < 3; i++) {
            //double comp = Math.exp(-(point1[i] - point2[i]) * (point1[i] - point2[i]));
            double comp = (point1[i + 2] - point2[i + 2]) * (point1[i + 2] - point2[i + 2]);
            sumSpace += comp;
        }
        return Math.sqrt(sumSpace /** sumColor*/);
    }

    public static double manhattanDistance(double[] point1, double[] point2) {
        double sum = 0.0;
        for (int i = 2; i < 5; i++) {
            sum += (Math.abs(point1[i] - point2[i]));//* r[i] * r[i];
        }
        return sum;
    }


}
