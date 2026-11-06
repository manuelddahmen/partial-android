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
import java.util.logging.Level;
import java.util.logging.Logger;

import matrix.PixM;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;
import one.empty3.ImageIO;
import one.empty3.io.ProcessNFiles;

public class Mix extends ProcessNFiles {
    public static final int MAX_PROGRESS = 256;
    private int progress = MAX_PROGRESS;

    public void setProgressColor(int progress) {
        this.progress = progress;
    }

    public int getProgressColor() {
        return progress;
    }

    @Override
    public boolean processFiles(File out, File... ins) {
        double ratio = 1.0 * progress / MAX_PROGRESS;
        boolean catched = false;
        if (ins.length > 1 && ins[0] != null && isImage(ins[0]) && ins[1] != null && isImage(ins[1])) {
            Image read1 = Image.getFromFile(ins[0]);
            Image read2 = Image.getFromFile(ins[1]);
            PixM pixMin1 = new PixM(read1);
            PixM pixMin2 = null;
            PixM outPixM = null;
            try {
                pixMin2 = new PixM(read2);
                outPixM = new PixM(pixMin1.getColumns(), pixMin1.getLines());
            } catch (RuntimeException ex) {
                Logger.getAnonymousLogger().log(Level.INFO, "Failed to load image #2", ex);
                catched = true;
            }
            if(!catched) {
                for (int i = 0; i < outPixM.getColumns(); i++) {
                    for (int j = 0; j < outPixM.getLines(); j++) {
                        for (int c = 0; c < 3; c++) {
                            pixMin1.setCompNo(c);
                            pixMin2.setCompNo(c);
                            outPixM.setCompNo(c);

                            outPixM.set(i, j, pixMin1.get(i, j) * (1 - ratio) + pixMin2.get(i, j) * (ratio));
                        }
                    }
                }
                outPixM.getImage().saveFile( out);
                return true;
            }
        }
        if((ins.length==1 && isImage(ins[0])) || catched ) {
            Image image = Image.getFromFile(ins[0]);
            image.saveFile( out);
            return true;
        }
        return false;
    }
}
