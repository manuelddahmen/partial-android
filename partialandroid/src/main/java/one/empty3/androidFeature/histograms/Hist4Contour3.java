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

package one.empty3.androidFeature.histograms;

import java.io.File;


import matrix.PixM;
import one.empty3.library.Point3D;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

public class Hist4Contour3 extends one.empty3.androidFeature.ProcessFiles {
    private int kMax = 3;
    private double fractMax = 0.05;//0.05;
    private Point3D pickedColor = Point3D.O0;

    public static class Circle {
        public double x = 0.0, y = 0.0, r = 0.0;
        public double i = 0.0;
        public Point3D maxColor = Point3D.O0;
        public double count = 0.0;
        public Point3D mincolor = Point3D.n(1, 1, 1);

        public Circle(double x, double y, double r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Circle{" +
                    "x=" + x +
                    ", y=" + y +
                    ", r=" + r +
                    ", i=" + i +
                    "n maxColor = p " + maxColor.toString() +
                    '}';
        }
    }


    public void makeHistogram(double r) {

    }

    public double nPoints(int x, int y, int w, int h) {
        return 0.0;
    }

    public Circle getLevel(Circle c, PixM m) {
        // I mean. Parcourir le cercle
        // mesurer I / numPoints
        // for(int i=Math.sqrt()
        //  return c;
        double sum = 0;
        int count = 0;
        c.maxColor = pickedColor;
        double intensity = 0.0;
        for (double i = c.x - c.r; i <= c.x + c.r; i++) {
            for (double j = c.y - c.r; j <= c.y + c.r; j++) {
                if (c.x - c.r >= 0 && c.y - c.r >= 0 && c.x + c.r < m.getColumns() && c.y + c.r < m.getLines()
                        && (i == c.x - c.r || j == c.y - c.r || i == c.x + c.r || j == c.y + c.r)) {
                    intensity += m.getIntensity((int) i, (int) j);
                    count++;
                    Point3D p = m.getP((int) i, (int) j);
                    if (p.norme() > 0.3 && p.moins(c.maxColor).norme() > 0.3) {
                        c.maxColor = p;
                        sum++;
                    }
                }
            }
        }
        c.maxColor = c.maxColor.mult(1 / (sum + 1));
        if (count > 0) {
            c.i = intensity;
            c.count = count;
        } else {
            c.i = 0.0;
            // c.r = 1;
        }


        return c;
    }

    public Point3D getPickedColor() {
        return pickedColor;
    }

    public void setPickedColor(Point3D pickedColor) {
        this.pickedColor = pickedColor;
    }

    @Override
    public boolean process(File in, File out) {
        if (!isImage(in)) {
            return false;
        }
        PixM inP;
        inP = PixM.getPixM(ImageIO.read(in), maxRes);


        double max = 0.0;
        PixM outP = new PixM(inP.getColumns(), inP.getLines());
        double maxR = Math.min(inP.getLines(), inP.getColumns()) * fractMax;
        Circle c = null;
        Point3D maxP = Point3D.O0;
        for (int i = 0; i < inP.getColumns(); i++) {
            for (int j = 0; j < inP.getLines(); j++) {
                for (int k = 1; k < maxR; k += 1) {
                    if (outP.getP(i, j).equals(Point3D.O0)) {
                        c = getLevel(new Circle(i, j, k), inP);
                        if (c.i > 0.0) {
                            //Point3D n = new Point3D(c.i, c.r, c.count);
                            Point3D n = new Point3D(c.i, c.i, c.i);
                            outP.setP(i, j, n);
                        }
                    }
                }
                Point3D n = outP.getP(i, j);
                if (!n.equals(Point3D.O0)) {
                    for (int l = 0; l < 3; l++) {
                        if (maxP.get(l) < n.get(l)) {
                            maxP.set(l, n.get(l));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < inP.getColumns(); i++) {
            for (int j = 0; j < inP.getLines(); j++) {
                for (int l = 0; l < 3; l++) {
                    inP.setCompNo(l);
                    outP.setCompNo(l);
                    outP.set(i, j, outP.get(i, j) / maxP.get(l) * inP.get(i, j));
                }
            }
        }
        // Colorier en fonction des pixels voisins
        //        Circle c2 = getLevel(cc, inP, cc.r/2);
        try {
            //one.empty3.libs.Image.saveFile(outP.normalize(0, 1).getImage().getBitmap(), "jpg", out);
            outP./*normalize(0, 1).*/getBitmap().saveFile(out);
            return true;

        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        return false;

    }


}
