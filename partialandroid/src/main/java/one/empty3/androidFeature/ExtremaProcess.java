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

public class ExtremaProcess extends one.empty3.androidFeature.ProcessFiles {
    private final int pointsCount;
    private final int neighbourSize;


    public ExtremaProcess() {
        this.neighbourSize = 3;//neighbourSize;
        this.pointsCount = 1; //pointsCount;
        //sub = new double[4*lines*columns];
    }

    public boolean process(File in, File out) {
        PixM pix = null;
        if (!isImage(in))
            return false;

        try {
            pix = new PixM(ImageIO.read(in));
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }


        LocalExtrema le = new LocalExtrema(
                pix.getColumns(), pix.getLines(),
                neighbourSize, pointsCount);


        M3 filter = le.filter(new M3(pix, 2, 2));
        if(filter==null || filter.getImagesMatrix()== null || filter.getImagesMatrix().length==0 || filter.getImagesMatrix()[0]==null || filter.getImagesMatrix()[0].length==0)
            return false;
        PixM m =filter.getImagesMatrix()[0][0];

        try {
            m.normalize(0, 1).getImage().saveFile(out);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }


    }


}
