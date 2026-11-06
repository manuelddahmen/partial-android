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
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;
import one.empty3.libs.Color;

public class SelectPointColorMassAglo extends FilterPixM {
    private double[] tmpColor = new double[3];
    private double[] rgb;

    public SelectPointColorMassAglo(Image image) {
        super(image);
    }


    public double[] getColor(int x, int y) {
        //double [] cs = new double[3];
        for (int c = 0; c < 3; c++) {
            setCompNo(c);
            tmpColor[c] = get(x, y);
        }
        return tmpColor;
    }

    public double[] getMean(int x, int y, int width, int height) {
        //double [] cs = new double[3];
        double[] mean = new double[]{0, 0, 0};

        for (int c = 0; c < 3; c++) {
            setCompNo(c);
            mean[c] += get(x, y);
        }
        for (int c = 0; c < 3; c++) {

            mean[c] /= ((double) 1.0 / width) / height;

        }
        return mean;
    }


    public double averageCountMeanOf(int x, int y, int width, int height, double threshold) {
        double count = 0;
        rgb = getMean(x, y, width, height);
        for (int i = x - width / 2; i < x + width / 2; i++) {
            for (int j = y - height / 2; j < y + height / 2; j++) {
                tmpColor = getColor(i, j);
                int col = 0;
                for (int c = 0; c < 3; c++) {
                    if (tmpColor[c] >= rgb[c] - threshold
                            && tmpColor[c] <= rgb[c] + threshold
                            && tmpColor[c] > 0.2) {
                        col++;
                    }
                    if (col == 3) {
                        count++;

                    }
                }

            }

        }
        return count / width / height;
    }

    @Override
    public double filter(double x, double y) {
        return averageCountMeanOf((int) x, (int) y, 10, 10, 0.2);
    }

    public Color getTmpColor() {
        return Color.newCol((tmpColor[0]), (tmpColor[1]), (tmpColor[2]));
    }

    public void setTmpColor(android.graphics.Color tmpColor) {
        this.tmpColor = new double[]{tmpColor.red(), tmpColor.green(), tmpColor.blue()};
    }
}
