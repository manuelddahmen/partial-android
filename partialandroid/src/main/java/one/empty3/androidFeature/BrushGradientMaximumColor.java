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

import android.graphics.BitmapFactory;

import java.io.File;

import one.empty3.feature.M;
import matrix.PixM;


public class BrushGradientMaximumColor extends one.empty3.androidFeature.ProcessFiles {
    public BrushGradientMaximumColor() {
        super();
    }

    public int[][] circle = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int[] values = {1, 0, 0, 0};
    public int[][] turn = {{0, 1, 2, 3}, {1, 2, 3, 0}, {2, 3, 0, 1}, {3, 0, 1, 2}};

    public M filterElem(int x, int y, int size) {
        M filter = new M(size * 2 + 1, size * 2 + 1);
        for (int i = x - size; i <= x + size; i++) {
            for (int j = y - size; j <= y + size; j++) {
                filter.setValues(i, j, distance(i, j, i - x, j - y));
            }
        }
        return filter;
    }

    private double distance(int x, int y, int i, int i1) {
        return Math.sqrt((x - i) * (x - i) + (y - i1) * (y - i1));
    }

    @Override
    public boolean process(File in, File out) {
        PixM pixM = new PixM(BitmapFactory.decodeFile(in.getAbsolutePath()));
        PixM pixM2 = new PixM(pixM.getColumns(), pixM.getLines());
        for (int i = 0; i < pixM.getLines(); i++) {
            for (int j = 0; j < pixM.getColumns(); j++) {
                for (int i2 = -1; i2 <= 1; i2++)
                    for (int j2 = -1; j2 <= 1; j2++) {
                        int x = i + i2;
                        int y = j + j2;

                        M f = filterElem(x, y, 1);
                        double fd = f.determinant();
                        pixM2.setValues(x, y, fd, fd, fd);

                    }
            }
        }
        pixM2.normalize(0, 1).getBitmap().saveFile( out);
        return true;
    }
}