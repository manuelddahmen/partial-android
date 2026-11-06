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


import android.graphics.Color;

import java.io.File;
import java.util.Arrays;

import one.empty3.ImageIO;
import matrix.PixM;

import one.empty3.library.ColorTexture;
import one.empty3.library.ITexture;
import one.empty3.library.Lumiere;
import one.empty3.library.Point3D;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;


public class PasteBlank extends one.empty3.androidFeature.ProcessFiles {

    /*
     *
     * @param img Image sur laquelle dessiner
     * @param col Couleur ou texture de dessin
     */
    public PixM pasteList(PixM img, ITexture col) {

        final PixM res = new PixM(img.getColumns(), img.getLines());
        for (int i = 0; i < img.getColumns() * img.getLines(); i++) {

            int ix = (i % img.getColumns());
            int iy = (i / img.getColumns());
            Point3D pi = new Point3D(1.0 * ix, 1.0 * iy, 0.0);


            int x = ix;
            int y = iy;

            int rgb = col.getColorAt(
                    pi.getX() / img.getColumns(),
                    pi.getY() / img.getLines());
            rgb = Color.BLACK;
            double[] rgbD = lookForColor(img, x, y, Lumiere.getDoubles(rgb));
            for (int comp = 0; comp < 3; comp++) {
                img.setCompNo(comp);
                res.setCompNo(comp);
                if (rgbD != null)
                    res.set(x, y, rgbD[comp]);
                else
                    res.set(x, y, img.get(x, y));
            }

        }
        return res;
    }

    private Point3D spirale(int x, int y, double r, double a) {
        return new Point3D(x + Math.cos(a) * r, y + Math.sin(a) * r, 0.0);
    }

    public boolean checkPointColorEquals(PixM img, int x, int y, int x1, int y1) {
        return img.getP(x, y).moins(img.getP(x1, y1)).norme() < 0.3;
    }

    private double[] lookForColor(PixM img, int x, int y, double[] search) {
        Point3D searchP = new Point3D(search);
        double[] colorProxy = new double[]{0., 0., 0.};
        Point3D c0 = img.getP(x, y);
        int countValues = 0;
        double[] values = new double[3];
        if (Arrays.equals(img.getValues(x, y), search)) {
            double radius = 1;
            double iA = 0;
            while (countValues < 4 && radius < 20) {
                //Parcourir autour de (x,y) dans un rayon radius
                // j: rayon du cercle courant dans la spirale de longueur L
                // i: angle du cercle courant dans "
                Point3D p = spirale(x, y, radius, iA);//Emplacement

                Point3D c1 = img.getP((int) (double) (p.get(0)), (int) (double) (p.get(1)));//Couleur


                if (searchP.moins(c1).norme() >= 0.3) {
                    for (int c = 0; c < colorProxy.length; c++) {
                        colorProxy[c] += c1.get(c);
                    }
                    countValues++;
                }

                iA += 1.0 / (2 * Math.PI * radius);
                if (iA >= 2 * Math.PI * radius) {
                    radius++;
                    iA = 0;
                }
            }
        }
        if (countValues > 0) {
            for (int i = 0; i < 3; i++) {
                colorProxy[i] /= countValues;
            }
            return colorProxy;

        }
        return null;
    }

    @Override
    public boolean process(File in, File out) {
        if (!in.getAbsolutePath().endsWith("jpg"))
            return false;
        Image read = ImageIO.read(in);
        PixM pixM = PixM.getPixM(read, maxRes);
        PixM pixM1 = pasteList(pixM, new ColorTexture(Color.BLACK));
        pixM1.normalize(0, 1).getImage().saveFile(out);
        return true;
    }

}
