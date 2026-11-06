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

import java.util.ArrayList;

import java.util.Objects;

import matrix.M3;

public class FollowLines {
    private M3 traces;
    private double threshold = 0.1;
    private ArrayList<Line> lines = new ArrayList<>();
    private int maxNeighbours = 2;

    public FollowLines(M3 traces) {
        this.traces = traces;
    }

    public M3 getTraces() {
        return traces;
    }

    public void setTraces(M3 traces) {
        this.traces = traces;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }


    // point00, neighbours 01 angle10, dist11

    /*
     * add line segment and join
     * @param ii column
     * @param ij line
     * @return m3 traiter matrice (ii,ij)
     */
    public ArrayList<Line> processPoints(int ii, int ij) {
        int[] x = new int[getMaxNeighbours()];
        int[] y = new int[getMaxNeighbours()];
        M3 points = new M3(traces.getColumns(), traces.getLines(), 1, 1);
        for (int comp = 0; comp < 4; comp++) {
            for (int i = 0; i < traces.getColumns(); i++) {
                for (int j = 0; j < traces.getLines(); j++) {

                    if (comp < 3) {
                        int add = 0;
                        double[][] neighbours = neighbours(i, j, ii, ij);
                        if (traces.get(i, j, ii, ij) > getThreshold())
                            for (int m = 0; m < neighbours.length; m++) {
                                for (int n = 0; n < neighbours[m].length; n++) {
                                    if (!(m == 1 && n == 1) && neighbours[m][n] > getThreshold() && add < getMaxNeighbours()) {
                                        x[add] = i + m;
                                        y[add] = j + n;

                                        add++;
                                    }
                                }

                            }
                        if (add < getMaxNeighbours()) {
                            for (int a = 0; a < getMaxNeighbours(); a++) {
                                Line l = new Line(i, j, x[a], y[a]);
                                lines.add(l);
                            }
                        }
                    }
                }

            }
        }
/*
        final int[] linesRemoved = {1};
        while (linesRemoved[0] > 0) {
            System.out.println("Lines size : " + lines.size() + " ; lines removed" + linesRemoved[0]);
            linesRemoved[0] = 0;
            lines.stream().iterator().forEachRemaining(line0 -> lines.stream().iterator().forEachRemaining(line1 -> {
                if (line0.join(line1)) {
                    lines.remove(line1);
                    linesRemoved[0]++;
                }
            }));
        }*/
        return lines;
    }

    private int getMaxNeighbours() {
        return maxNeighbours;
    }

    private double[][] neighbours(int i, int j, int ii, int ij) {
        double[][] a = new double[3][3];
        for (int m = 0; m < 3; m++)
            for (int n = 0; n < 3; n++)
                a[m][n] = traces.get(i - 1 + m, j - 1 + n, ii, ij);

        return a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowLines)) return false;
        FollowLines that = (FollowLines) o;
        return Double.compare(that.threshold, threshold) == 0 &&
                Objects.equals(traces, that.traces) &&
                Objects.equals(lines, that.getLines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(traces, threshold, lines);
    }
}
