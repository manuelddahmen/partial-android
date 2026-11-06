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
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;


import java.io.File;

public class TransformColor extends one.empty3.androidFeature.ProcessFiles {
    @Override
    public boolean process(File in, File out) {

        PixM pix = PixM.getPixM(ImageIO.read(in), maxRes);

        for (int i = 0; i < pix.getColumns(); i++) {
            for (int j = 0; j < pix.getLines(); j++) {
                for (int c = 0; c < pix.getCompNo(); c++) {
                    pix.setCompNo(c);
                    pix.set(i, j, 1 - pix.get(i, j));
                }
            }
        }

        Image.saveFile(pix.normalize(0.0, 1.0).getImage(), "jpg", out, shouldOverwrite);

        return true;
    }
}
