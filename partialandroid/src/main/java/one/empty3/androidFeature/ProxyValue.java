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


import java.io.File;

import matrix.PixM;
import one.empty3.ImageIO;
import one.empty3.library.Point3D;


public class ProxyValue extends one.empty3.androidFeature.ProcessFiles {

    public boolean process(File in, File out) {
        double min = 0.3;
        if (!in.getName().endsWith(".jpg"))
            return false;
        File file = in;
        PixM original = null;

        try {
            original = PixM.getPixM(ImageIO.read(in), maxRes);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
            // assertTrue(false);

        }
        int p = 0;
        PixM copy = new PixM(original.getColumns(), original.getLines());


        for (int i = 0; i < original.getColumns(); i++)

            for (int j = 0; j < original.getLines(); j++)

                if (original.luminance(i, j) < min) {

                    searchFromTo(original, copy, i, j, min * 2);
                    p++;

                } else {
                    for (int l = 0; l < 3; l++) {
                        original.setCompNo(l);
                        copy.setCompNo(l);
                        copy.set(i, j, original.get(i, j));
                    }
                }


        try {
            copy.getImage().saveFile( out);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }


        System.out.println("point " + p);

        return true;
    }


    public void searchFromTo(PixM original, PixM copy, int i, int j, double min) {
        Point3D p = null;
        int i2 = i, j2 = j;
        
     /*   for(int k=0; k<original.getColumns()*original.getLines();k++)
                { 
            
              int [] k1 = new int[] {incr[(k/2)%8], 
                                     incr[(k/2+1)%8]};
                i2+= k1[0];
                j2 += k1[1];
            
           */
        for (int l = 1; l < original.getColumns(); l++) {
            int[] incr = new int[]{

                    -l, -l, 0, 1,
                    l, -l, 0, 1,
                    -l, l, 1, 0,
                    -l, -l, 1, 0

            };

            for (int sq = 0; sq < incr.length; sq += 4) {
                int pass = 0;
                for (int i3 = incr[sq]; i3 < l && pass > -1; i3 += incr[(sq + 2)]) {

                    for (int j3 = incr[(sq + 1)]; j3 < l && pass > -1; j3 += incr[(sq + 3)]) {

                        pass++;
                        i2 = i + i3;
                        j2 = j + j3;
                        p = null;


                        if (original.luminance(i2, j2) >= min) {


                            copyPixel(original, i2,
                                    j2,
                                    copy, i, j);
                            return;
                        }


                        if (pass > 2 * l) pass = -1;

                    }
                }
            }
        }
        // System.out.println("error not found");

        return;
    }

    public void copyPixel(PixM m1, int i, int j,
                          PixM m2, int i2, int j2) {
        for (int c = 0; c < 3; c++) {

            m1.setCompNo(c);
            m2.setCompNo(c);

            m2.set(i2, j2, m1.get(i, j));
        }

    }
} 
