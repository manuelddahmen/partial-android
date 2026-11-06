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

public class DefineFilter extends FilterPixM {


    public DefineFilter(double[][] matrix, double divider) {
        super(matrix.length, matrix[0].length);
        fill(matrix);
    }

    @Override
    public double filter(double x, double y) {
        return 1.0 * get((int) x - columns / 2, (int) y - lines / 2);
    }

    /*
     * Gaussian filter Matrix
     * @param matrix 2d double
     */


    public void fill(double[][] matrix) {
        for (int comp = 0; comp < getCompCount(); comp++) {
            setCompNo(comp);
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < lines; j++) {
                    set(i, j, matrix[j][i]);
                }
            }
        }

    }
}
