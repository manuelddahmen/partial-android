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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import one.empty3.library.core.lighting.Colors;
import matrix.PixM;
import one.empty3.libs.Color;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

import one.empty3.library.Lumiere;

public class Classification extends one.empty3.androidFeature.ProcessFiles {
    Random random = new Random();
    private Image imageOut;
    private int SIZE = 5;
    private double ratio = 0.3;
    private double threshold = 0.3;


    @Override
    public boolean process(File in, final File out) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("effectClassification-", ".jpg");
            Lines7luckyLinesOutline lines7luckyLinesOutline = new Lines7luckyLinesOutline();
            lines7luckyLinesOutline.process(in, tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //!!! Border effect
        in = tempFile;
        if (in == null) {
            try {
                throw new FileNotFoundException("Temporary file in $tempFile");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (!in.getName().endsWith(".jpg"))
            return false;
        PixM selectPointColorMassAglo = null;
        Image read = null;
        read = new Image(Objects.requireNonNull(ImageIO.read(in)).getImage());
        selectPointColorMassAglo = PixM.getPixM(read, maxRes);
        imageOut = ImageIO.read(in);
        SelectPointColorMassAglo selectPointColorMassAglo1 = new SelectPointColorMassAglo(read);
        int color = Color.WHITE;
        for (int i = 0; i < imageOut.getWidth(); i += 1)
            for (int j = 0; j < imageOut.getHeight(); j += 1) {
                selectPointColorMassAglo1.setTmpColor(new Colors().random());
                double v = selectPointColorMassAglo1.averageCountMeanOf(i, j, SIZE, SIZE, threshold);
                if (v > ratio) {
                    imageOut.setRgb(i, j, color);/*selectPointColorMassAglo.getChosenColor().getRgb()*/
                } else {
                    double[] doubles = Lumiere.getDoubles(read.getRgb(i, j));
                    /*for(int c=0; c<3; c++)
                        doubles[c] = doubles[c]/3;
*/
                    imageOut.setRgb(i, j, Lumiere.getInt(doubles));
                }
            }

        imageOut.saveFile( out);
        return true;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
}
