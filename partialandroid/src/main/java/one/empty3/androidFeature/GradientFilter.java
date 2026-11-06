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

import matrix.FilterMatPixM;
import matrix.M3;

public class GradientFilter extends FilterMatPixM {

    protected int columnsIn = 2;
    protected int linesIn = 2;
    protected int columns;
    protected int lines;
    // Local minima; maxima
    private double[][][][] gNormalize;
    int incrOK = 0;
    private double[][][] mean;


    public GradientFilter(int imageColumns, int imageLines) {
        this.columns = imageColumns;
        this.lines = imageLines;
        initGNormalise();
    }


    @Override
    public M3 filter(M3 source) {
        initGNormalise();
        M3 copy = source.copy();
        for (int j = 0; j < copy.getLines(); j++) {
            for (int i = 0; i < copy.getColumns(); i++) {
                for (int ii = 0; ii < copy.getColumnsIn(); ii++) {
                    for (int ij = 0; ij < copy.getLinesIn(); ij++) {
                        for (int c = 0; c < copy.getCompCount(); c++) {


                            copy.setCompNo(c);
                            source.setCompNo(c);
                            element(source, copy, i, j, ii, ij);
                        }

                    }
                }
            }
        }
        return norm(copy, copy.copy());
    }

    /*
     * Computes gradient element p(x,y)
     * @param source 2x2 inner image matrix
     * @param copy 2x2 inner image gradient matrix
     * @param i x cordinates of p(x, y)
     * @param j y cordinates of p(x, y)
     * @param ii (0, 1) gradX, gradY
     * @param ij (0, 1) difference, angle (delta phase)
     *
     */
    @Override
    public void element(M3 source, M3 copy, int i, int j, int ii, int ij) {
        double d = 1.0;
        double v = source.get(i, j, ii, ij);
        if (ii == 0 && ij == 0) {
            d = (-source.get(i - 1, j, ii, ij) + v);
        }
        if (ii == 0 && ij == 1) {
            d = Math.atan(((-source.get(i, j - 1, ii, ij) + v) /
                    (-source.get(i - 1, j, ii, ij) + v)));
        }
        if (ii == 1 && ij == 0) {
            d = (-source.get(i, j - 1, ii, ij) + v);
        }
        if (ii == 1 && ij == 1) {
            d = Math.atan(1 / (
                    (source.get(i, j + 1, ii, ij) - v) /
                            (source.get(i + 1, j, ii, ij) - v)));
        }
        if (ii >= 0 && ii < 2 && ij >= 0 && ij < 2)
            copy.set(i, j, ii, ij, d);
        else
            System.exit(-3);
        incrOK++;

        if (v < gNormalize[source.getCompNo()][ii][ij][0])
            gNormalize[source.getCompNo()][ii][ij][0] = v;
        if (v > gNormalize[source.getCompNo()][ii][ij][1])
            gNormalize[source.getCompNo()][ii][ij][1] = v;
        mean[source.getCompNo()][ii][ij] += v;
    }


    /*
     * Norme linéaire
     * Autre exemple : histogramme de valeurs = échelle pondérée
     *
     * @param image M3 image array
     * @return copy norm
     */

    public M3 norm(M3 image, M3 copy) {
        for (int i = 0; i < image.getColumns(); i++)
            for (int j = 0; j < image.getLines(); j++) {
                for (int ii = 0; ii < image.getColumnsIn(); ii++)
                    for (int ij = 0; ij < image.getLinesIn(); ij++) {
                        for (int c = 0; c < image.getCompCount(); c++) {
                            image.setCompNo(c);
                            copy.setCompNo(c);
                            double v = image.get(i, j, ii, ij);
                            v = (v - gNormalize[c][ii][ij][0]) /
                                    (gNormalize[c][ii][ij][1] - gNormalize[c][ii][ij][0]);
                            if (Double.isInfinite(v) || Double.isNaN(v)) {
                                v = 1.0;
                            }
                            copy.set(i, j, ii, ij, v);
                        }
                    }
            }
        return copy;
    }

    public M3 mean(M3 image, M3 copy) {
        double[] sum = new double[copy.getCompCount()];
        for (int i = 0; i < image.getColumns(); i++)
            for (int j = 0; j < image.getLines(); j++) {
                for (int ii = 0; ii < image.getColumnsIn(); ii++)
                    for (int ij = 0; ij < image.getLinesIn(); ij++) {
                        for (int c = 0; c < image.getCompCount(); c++) {
                            image.setCompNo(c);
                            copy.setCompNo(c);
                            double v = image.get(i, j, ii, ij);
                            v = ((v - mean[c][ii][ij]) * (v - mean[c][ii][ij]));
                            if (Double.isInfinite(v) || Double.isNaN(v)) {
                                v = 1.0;
                            }
                            sum[c] += v;
                            copy.set(i, j, ii, ij, v);
                        }

                    }
            }
        for (int i = 0; i < image.getColumns(); i++)
            for (int j = 0; j < image.getLines(); j++) {
                for (int ii = 0; ii < image.getColumnsIn(); ii++)
                    for (int ij = 0; ij < image.getLinesIn(); ij++) {
                        for (int c = 0; c < image.getCompCount(); c++) {
                            copy.setCompNo(c);
                            double v = copy.get(i, j, ii, ij);
                            copy.set(i, j, ii, ij, v / sum[c]);
                        }

                    }
            }

        return copy;
    }

    public void initGNormalise() {
        gNormalize = new double[4][columnsIn][linesIn][2];
        for (int c = 0; c < 4; c++) {
            for (int ii = 0; ii < columnsIn; ii++)
                for (int ij = 0; ij < linesIn; ij++) {

                    gNormalize[c][ii][ij][0] = Double.MAX_VALUE;
                    gNormalize[c][ii][ij][1] = -Double.MAX_VALUE;
                }

        }
        mean = new double[4][columnsIn][linesIn];
        for (int c = 0; c < 4; c++) {
            for (int ii = 0; ii < columnsIn; ii++)
                for (int ij = 0; ij < linesIn; ij++) {
                    mean[c][ii][ij] = 0;
                }

        }
    }
}
