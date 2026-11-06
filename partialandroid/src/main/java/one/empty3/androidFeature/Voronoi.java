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


import matrix.PixM;
import one.empty3.library.Point3D;

import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

import java.io.File;
import java.util.ArrayList;

import java.util.List;

public class Voronoi extends one.empty3.androidFeature.ProcessFiles {

    private Point3D near(Point3D point3D, List<Point3D> p) {
        double dist = 1000000;
        Point3D pRes = null;

        int index2 = 0;

        while (index2 <= p.size() - 1) {
            Point3D p3 = p.get(index2);

            if (Point3D.distance(point3D, p3) < dist && p3 != point3D && !p3.equals(point3D)) {
                dist = Point3D.distance(point3D, p3);
                pRes = p3;


            }
            index2++;
        }
        return pRes;
    }

    @Override
    public boolean process(File in, File out) {
        try {
            List<Point3D> points = new ArrayList<>();
            Image read = ImageIO.read(in);
            PixM pixM = PixM.getPixM(read, maxRes);
            PixM pixMOut = pixM.copy();
            for (int i = 0; i < pixM.getColumns(); i++) {
                for (int j = 0; j < pixM.getLines(); j++) {
                    if (pixM.luminance(i, j) > 0.4) {
                        points.add(new Point3D((double) i, (double) j, pixM.luminance(i, j)));
                    }
                }
            }


            for (int i = 0; i < pixM.getColumns(); i++) {
                for (int j = 0; j < pixM.getLines(); j++) {
                    Point3D near = near(new Point3D((double) i, (double) j, 0.0), points);
                    if (near != null) {
                        Point3D p = pixM.getP((int) (double) near.get(0), (int) (double) near.get(1));
                        pixMOut.setValues(i, j, p.getX(), p.getY(), p.getZ());
                    } else {
                        //System.out.println("Error near==null");
                    }
                }
            }


            one.empty3.libs.Image.saveFile(pixMOut.getImage(), "jpg", out, shouldOverwrite);

            return true;


        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
