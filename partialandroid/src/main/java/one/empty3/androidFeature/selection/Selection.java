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

package one.empty3.androidFeature.selection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import one.empty3.library.Lumiere;
import matrix.PixM;
import one.empty3.library.Point3D;

/*
 * Trouver les primitives.
 */
public abstract class Selection extends one.empty3.androidFeature.ProcessFiles {
    private List<Point3D> selectedPoints = new ArrayList<>();
    private PixM image;
    public static final int SELECTION_TYPE_COLOR = 1;
    public static final int SELECTION_TYPE_COLOR_FROM_POINT = 2;
    public static final int SELECTION_TYPE_COLOR_FROM_RECT = 4;
    public static final int SELECTION_OPTION_PRESELECTION = 8;

    public List<Point3D> select(List<Point3D> preSelection, PixM pix,
                                int rgb, double threshold) {
        List<Point3D> list = new ArrayList<>();
        double[] doubles = Lumiere.getDoubles(rgb);
        for (int i = 0; i < pix.getColumns(); i++) {
            for (int j = 0; j < pix.getLines(); j++) {
                for (int c = 0; c < pix.getCompNo(); c++) {
                    if (pix.get(i, j) <= doubles[c] - threshold && pix.get(i, j) >= doubles[c] + threshold) {
                        list.add(Point3D.n(i, j, 0));
                    }
                }
            }
        }
        return list;
    }

    public List<Point3D> selectColorPoint(List<Point3D> preSelection, PixM pix,
                                          int rgb, int x, int y, double threshold) {
        List<Point3D> list = new ArrayList<>();
        double[] doubles = Lumiere.getDoubles(rgb);
        for (int i = 0; i < pix.getColumns(); i++) {
            for (int j = 0; j < pix.getLines(); j++) {
                for (int c = 0; c < pix.getCompNo(); c++) {
                    if (pix.get(i, j) <= doubles[c] - threshold && pix.get(i, j) >= doubles[c] + threshold) {
                        list.add(Point3D.n(i, j, 0));
                    }
                }
            }
        }
        return list;
    }


    public abstract List<Point3D> selectPoint(List<Point3D> preSelection, PixM pix,
                                              int x, int y);

    public abstract List<Point3D> selectInRect(List<Point3D> preSelection, PixM pix,
                                               int x1, int y1, int x2, int y2);

    @Override
    public boolean process(File in, File out) {
        return false;
    }
}
