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

public class GaussFilterPixM extends FilterPixM {
    private final PixM in;
    public double sigma = 0.8;

    public GaussFilterPixM() {
        super(3, 3);
        in = null;
    }

    public GaussFilterPixM(PixM in, int squareSize) {
        super(squareSize, squareSize);
        this.in = in;
    }

    @Override
    public double filter(double x, double y) {
        double ret = 0.0;
        for (double i = x - in.getLines() / 2; i < x + in.getLines() / 2; i++)
            for (double j = y - in.getColumns() / 2; j < y + in.getColumns() / 2; j++) {
                ret += in.get((int) i, (int) j) * Math.exp(
                        -((x - i) * (x - i) + (y - j) * (y - j))
                                / 2 / sigma / sigma) / 2 / Math.PI / sigma / sigma;
            }
        return ret;
    }

    /*
     * Gaussian filter Matrix
     * @param halfSquareSizeMinus1 n*n square distribution
     * @param sigma gauss parameter
     * @param matrix.PixM image
     */
    public GaussFilterPixM(PixM pixM, int halfSquareSizeMinus1, double sigma) {
        this(pixM, halfSquareSizeMinus1 * 2 + 1);
        this.sigma = sigma;
        for (int comp = 0; comp < getCompCount(); comp++) {
            setCompNo(comp);
            fill();
        }
        setCompNo(0);
    }

    public void fill() {
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < lines; j++) {
                set(i, j, filter(i - columns / 2,
                        j - lines / 2)
                );
            }
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }
}
