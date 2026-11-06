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
import one.empty3.ImageIO;


import java.io.File;
import java.util.Objects;

public class IdentNullProcess extends one.empty3.androidFeature.ProcessFiles {

    @Override
    public boolean process(File in, File out) {
        PixM pixM = null;
        if (maxRes > 0) {
            pixM = PixM.getPixM(Objects.requireNonNull(ImageIO.read(in)), maxRes);
        } else {
            pixM = new PixM(Objects.requireNonNull(ImageIO.read(in)));
        }
        assert pixM != null;
        pixM.getBitmap().saveFile(out);
        addSource(out);
        return true;

    }

}
