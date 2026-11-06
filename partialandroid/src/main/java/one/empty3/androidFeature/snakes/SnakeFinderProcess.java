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

package one.empty3.androidFeature.snakes;


import matrix.PixM;
import one.empty3.library.Point3D;

import one.empty3.ImageIO;

import java.io.File;

public class SnakeFinderProcess extends one.empty3.androidFeature.ProcessFiles {
    public SnakeFinderProcess() {

    }

    @Override
    public boolean process(File in, File out) {
        DipSnake snake = new DipSnake();
        PixM image = PixM.getPixM(ImageIO.read(in), maxRes);
        for (int i = 0; i < 6; i++) {
            snake.add(i, new Point3D(image.getColumns() / 2 + 0.6 * image.getColumns() / 2 * Math.cos(2 * 3.1416),
                    image.getLines() / 2 - 0.6 * image.getLines() / 2 * Math.sin(2 * 3.1416),
                    0.0));
        }

        snake.energyMinimization(image);

        //image.fillIn(snake, new ColorTexture(Color.WHITE), new ColorTexture(Color.WHITE));

        image.normalize(0, 1).getImage().saveFile(out);
        return true;
    }

}
