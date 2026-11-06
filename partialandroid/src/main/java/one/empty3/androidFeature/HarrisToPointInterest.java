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
import matrix.M3;
import matrix.PixM;
import one.empty3.library.Point3D;

import java.util.*;

public class HarrisToPointInterest extends FilterPixM {
    private PixM m;

    public HarrisToPointInterest(int c, int l) {
        super(c, l);
    }
    // nabla
    // sobel x sobel y
    // ou gxgx gxgy
    //    gygx gygy
    // p ij
    // mat Ip, Ip~
    // trace/determinant extract
    // m(p) at ij
    // localextrema filter
    // !=0 => points of interest

    public double filter(double x, double y) {
        m.setCompNo(getCompNo());
        int i = (int) x, j = (int) y;
        //double gxgx, gxgy, gygx, gygy;
        double gpgx = m.get(i + 1, j) - m.get(i, j);
        double gpgy = m.get(i, j + 1) - m.get(i, j);
        double gpgx1 = m.get(i, j) - m.get(i - 1, j);
        double gpgy1 = m.get(i, j) - m.get(i, j - 1);
        double gxgx = gpgx - gpgx1;
        double gygx = gpgy1 - gpgx;
        double gxgy = gpgx1 - gpgy;
        double gygy = gpgy1 - gpgy;

        return (gxgx * gygy - gxgy * gygx) / (gxgx * gygy);
    }

    public List<Point3D> getPoi() {
        LocalExtrema le = new LocalExtrema(m.getColumns(),
                lines, 3, 0);
        PixM m2 = le.filter(new M3(m, 1, 1)).getImagesMatrix()[0][0];
        List<Point3D> poi = new ArrayList<>();
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < lines; j++)
                if (m2.get(i, j) != 0 && !Double.isNaN(m2.get(i, j))
                        && !Double.isInfinite(m2.get(i, j)))
                    poi.add(new Point3D(
                            (double) i,
                            (double) j,
                            m2.get(i, j)
                    ));
        return poi;
    }
}
