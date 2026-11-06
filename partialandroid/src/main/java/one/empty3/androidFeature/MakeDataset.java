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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import matrix.PixM;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;
import one.empty3.ImageIO;

/*
 line : l, c, r, g, b
 */
public class MakeDataset {
    public MakeDataset(File image,
                       File outputCsv, int res) {
        try {
            Image img = ImageIO.read(image);
            PixM pix;
            if (res > 0)

                pix = PixM.getPixM(img, res);
            else
                pix = new PixM(img);

            System.out.println("size out : " + pix.getColumns() + ", " + pix.getLines());

            int i = 0;

            PrintWriter pw = new PrintWriter(outputCsv);
            for (int l = 0; l < pix.getLines(); l++)
                for (int c = 0; c < pix.getColumns(); c++) {
                    //if (pix.luminance(c, l) > 0.1) { // ADDED
                    pix.setCompNo(0);
                    double r = pix.get(c, l);

                    pix.setCompNo(1);
                    double g = pix.get(c, l);

                    pix.setCompNo(2);
                    double b = pix.get(c, l);

                    pw.println("" + c + " " + l + " " +
                            r + " " + g + " " + b);
                    //}
                    i++;
                }
            pw.close();


            System.out.println("MakeDataset csv lines: " + i);
            System.out.println("MakeDataset : " + outputCsv.getAbsolutePath());
            System.out.println("MakeDataset output csv exists?: " + outputCsv.exists());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeFeature
            (HashMap<Integer, double[]> data,
             File csvout) {
        try {

            PrintWriter pw = new PrintWriter(csvout);

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).length; j++) {
                    pw.print(data.get(i)[j] + " ");
                }
                pw.println();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

} 
