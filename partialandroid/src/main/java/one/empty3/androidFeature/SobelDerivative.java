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

import one.empty3.ImageIO;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import matrix.PixM;


import java.io.File;
import java.util.Objects;

/*
sobel. 3×3 ou plus. 1*2+1
|p1 -p2| (/ n/n)?
*/
public class SobelDerivative extends one.empty3.androidFeature.ProcessFiles {
    private final boolean isX;
    private int lines = 3;
    private int columns = 3;
    double[] sobelX = new double[]{-1, -2, -1, 0, 0, 0
            , 1, 2, 1};
    double[] sobelY = new double[]{-1, 0, -1,
            -2, 0, 2
            , 1, 0, 1};

    public SobelDerivative() {
        this.isX = true;
    }

    private void fill() {

    }

    public double x(int i, int j) {
        return sobelX[i * lines + j];
    }

    public double y(int i, int j) {
        return sobelY[i * lines + j];
    }

    public void theta(int i, int j) {

    }

    public double filter(PixM p, int x, int y) {
        int dy = (int) (lines / 2);
        int dX = (int) (columns / 2);
        double sumX = 0, sumY = 0;
        for (int c = 0; c < 3; c++) {
            for (int l = 0; l < 3; l++) {
                sumX += sobelX[l * columns + c] * p.get(x + c - 1, y + l - 1);
                sumY += sobelY[l * columns + c] * p.get(x + c - 1, y + l - 1);
            }
        }
        return Math.sqrt(sumX * sumX + sumY * sumY);
    }

    @Override
    public boolean process(File in, File out) {
        PixM p = PixM.getPixM(Objects.requireNonNull(ImageIO.read(in)), maxRes);
        PixM pOut = p.copy();
        for (int j = 0; j < p.getLines(); j++) {
            for (int i = 0; i < p.getColumns(); i++) {
                for (int c = 0; c < 3; c++) {
                    p.setCompNo(c);
                    pOut.setCompNo(c);
                    double filter = filter(p, i, j);
                    pOut.set(i, j, filter);
                }
            }
        }
        Image.saveFile(pOut.normalize(0, 1).getImage(), "jpg", out, shouldOverwrite);
        return true;
    }
}
