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
import matrix.M3;
import matrix.PixM;


import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GradAddProcess extends one.empty3.androidFeature.ProcessFiles {

    public void setMaxRes(int maxRes) {
        this.maxRes = maxRes;
    }

    public boolean process(File in, File out) {
        if (!isImage(in))
            return false;
        PixM pix;
        try {
            pix = PixM.getPixM(ImageIO.read(in), maxRes);
            if(pix==null)
                Logger.getAnonymousLogger().log(Level.SEVERE, "Error in ProcessFile GradAddProcess. Can't create matrix.PixM");
            GradientFilter gf = new GradientFilter(pix.getColumns(),
                    pix.getLines());
            PixM[][] imagesMatrix = gf.filter(
                    new M3(
                            pix, 2, 2)
            ).getImagesMatrix();

            Linear linear = new Linear(imagesMatrix[0][0], imagesMatrix[0][1],
                    new PixM(imagesMatrix[0][0].getColumns(), imagesMatrix[0][0].getLines()));

            boolean b = linear.op2d2d(new char[]{'+'}, new int[][]{{0}, {1}}, new int[]{2});

            PixM image = linear.getImages()[2];

            one.empty3.libs.Image.saveFile(image.normalize(0.0, 1.0).getImage(), "jpg", out, shouldOverwrite);


            addSource(out);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
