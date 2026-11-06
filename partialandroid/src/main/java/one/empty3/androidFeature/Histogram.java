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
import matrix.PixM;
import one.empty3.libs.*;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

import java.io.File;
import java.util.*;
import java.util.List;

/*
 * radial density of region (x, y, r)
 * by mean or mean square or somewhat else.
 */
public class Histogram {
    static boolean shouldOverwrite = true;
    private final double diffLevel;
    private final double radiusIncr;
    private double min;
    private List<Circle> circles
            = new ArrayList<>();
    private PixM m = null;
    private double levelMin;

    public class Circle {
        public double x, y, r;
        public double i;

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
                    '}';
        }
    }

    //private final int[][][] levels;


    public Histogram(PixM image, int levels, double min, double radiusIncr, double levelMin) {
        this.diffLevel = 1.0 / levels;
        this.min = min;
        //this.levels = new int[levels][image.getColumns()][image.getLines()];
        this.radiusIncr = radiusIncr;
        this.m = image;
        this.levelMin = levelMin;
    }

    public void makeHistogram(double r) {

    }

    public double nPoints(int x, int y, int w, int h) {
        return 0.0;
    }

    public Circle getLevel(Circle c) {
        // I mean. Parcourir le cercle 
        // mesurer I / numPoints
        // for(int i=Math.sqrt()
        //  return c;
        int count = 0;
        double intensity = 0.0;
        for (double i = c.x - c.r; i <= c.x + c.r; i++) {
            for (double j = c.y - c.r; j <= c.y + c.r; j++) {
                if (Math.sqrt((i - c.x) * (i - c.x) + (j - c.y) * (j - c.y)) <= c.r
                        && c.x - c.r >= 0 && c.y - c.r >= 0 && c.x + c.r < m.getColumns() && c.x + c.r < m.getLines()) {
                    intensity += m.getIntensity((int) i, (int) j);
                    count++;
                }
            }
        }

        if (count > 0)
            c.i = intensity / count;
        else {
            c.i = 0.0;
            c.r = 1;
        }


        return c;
    }

    public List<Circle> getPointsOfInterest(double heightMaxLevelI) {

        circles = new ArrayList<>();

        // gradient radial ???
        // X-x2 > li-li+-1
        // i(x2, y2, r2) > i(x, y, r) + leveldiffi|||
        // stop
        for (int i = 0; i < m.getColumns(); i++)
            for (int j = 0; j < m.getLines(); j++) {
                double r = radiusIncr;
                double diffI = 0;
                Circle c1 = null, c2;
                int iterates = 0;
                while (r < m.getColumns() && diffI < heightMaxLevelI) {
                    c1 = new Circle(i, j, r);
                    c2 = new Circle(i, j, r + radiusIncr);
                    diffI = Math.abs(getLevel(c1).i - getLevel(c2).i);
                    if (getLevel(c1).i < diffLevel) break;
                    c1 = c2;
                    r += radiusIncr;
                    iterates++;
                }
                if (iterates > 0 && c1.i > 0.0) {
                    circles.add(c1);
                }
            }
        return circles;
    }

    public static void testCircleSelect(Image file, File directory, int levels, double min, double radiusIncr) {
        for (int i = 0; i < levels; i++) {
            Image img = file;
            Image img2 = new Image(img.getWidth(), img.getHeight());
            Image img3 = new Image(img.getWidth(), img.getHeight());
            Histogram histogram = new Histogram(new PixM(img), levels, min, radiusIncr, 0.1);
            int finalI = i;
            List<Circle> pointsOfInterest = histogram.getPointsOfInterest(0.1);
            pointsOfInterest.stream().forEach(circle -> {
                if (circle.i >= min /*<histogram.diffLevel* finalI*/) {
                    //img.drawOval((int) (circle.x - circle.r), (int) (circle.y - circle.r), (int) (circle.r * 2), (int) (circle.r * 2));
                    Color color = Color.newCol((float) circle.i, 0f, (float) (circle.i / circle.r));
                    //img3.drawOval((int) (circle.x - circle.r), (int) (circle.y - circle.r), (int) (circle.r * 2), (int) (circle.r * 2));
                    //img3.setRGB((int) (circle.x), (int) (circle.y), color.toArgb());
                }
            });
            pointsOfInterest.sort(new Comparator<Circle>() {
                @Override
                public int compare(Circle o1, Circle o2) {
                    double v = o1.y - o1.y;
                    if (v < 0)
                        return -1;
                    if (v > 0)
                        return 1;
                    if (v == 0) {
                        double v1 = o1.x - o1.x;
                        if (v1 < 0)
                            return -1;
                        if (v1 > 0)
                            return 1;
                        if (v1 == 0)
                            return 0;
                    }
                    return 0;
                }
            });

            File fileToWrite = new File(directory.getAbsolutePath()
                    + "level" + finalI + ".jpg");
            File fileToWrite2 = new File(directory.getAbsolutePath()
                    + "level" + finalI + "_NEW.jpg");
            File fileToWrite3 = new File(directory.getAbsolutePath()
                    + "level" + finalI + "_NEW_RGB.jpg");
            fileToWrite.mkdirs();
            img.saveFile( fileToWrite);
            img.saveFile(fileToWrite2);
            img.saveFile(fileToWrite3);

        }
    }

    public static void main(String[] args) {
        int levels = 10;
        //testCircleSelect(new File("resources/vg1.jpg"), new File("resources/res/"), levels, 0.3, 10);
    }
}
