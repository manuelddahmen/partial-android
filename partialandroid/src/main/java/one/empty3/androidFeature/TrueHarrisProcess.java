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


import java.io.File;

import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

public class TrueHarrisProcess extends one.empty3.androidFeature.ProcessFiles {

    public boolean process(File in, File out) {

        if (!in.getName().endsWith(".jpg"))

            return false;

        File file = in;

        PixM pix = null;
        Image img = null;

        try {
            img = ImageIO.read(file);
            pix = PixM.getPixM(img, maxRes);

        } catch (Exception ex) {

            ex.printStackTrace();

            return false;

            // assertTrue(false);


        }

        TrueHarris th = new TrueHarris(pix);
        PixM pixM = new PixM(pix.getImage());
        for (int c = 0; c < 3; c++) {
            th.setCompNo(c);
            pixM.setCompNo(c);
            pix.setCompNo(c);
            for (int i = 0; i < pix.getColumns(); i++)
                for (int j = 0; j < pix.getLines(); j++)
                    pixM.set(i, j, th.filter(i, j));
        }


        PixM normalize = pixM.normalize(0.0, 1.0);


        //


        try {

            one.empty3.libs.Image.saveFile(normalize.getImage(), "JPEG", out, shouldOverwrite);
            return true;
        } catch (Exception ex) {

        }
        return true;
    }

} 
