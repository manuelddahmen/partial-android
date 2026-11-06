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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

import matrix.PixM;
import one.empty3.library.Point3D;

public class ClassificationAvgColors extends one.empty3.androidFeature.ProcessFiles {
    public ClassificationAvgColors() {

    }

    @Override
    public boolean process(File in, File out) {
        KMeans classification = new KMeans();
        classification.process(in, out);
        // Processed by "classification
        // Non filtered image
        Image original = ImageIO.read(in);
        PixM pixMOriginal = new PixM(Objects.requireNonNull(original));
        PixM toProcess = new PixM(original);
        Map<Integer, double[]> c = classification.kclusterer.centroids;
        Map<Integer, Point3D> sum = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        // Faire les moyennes des points de même groupe
        c.forEach(new BiConsumer<Integer, double[]>() {
            @Override
            public void accept(Integer integer, double[] doubles) {
                sum.putIfAbsent(integer, Point3D.O0);
                count.putIfAbsent(integer, 0);
                sum.put(integer, sum.get(integer).plus(pixMOriginal.getP((int) doubles[0], (int) doubles[1])));
                count.put(integer, count.get(integer) + 1);
            }
        });
        Point3D[] ps = new Point3D[c.keySet().size()];
        c.forEach(new BiConsumer<Integer, double[]>() {
            @Override
            public void accept(Integer integer, double[] doubles) {
                Integer countI = count.get(integer);
                Point3D sumI = sum.get(integer);
                sumI = sumI.mult(1. / countI);
                ps[integer] = sumI;
            }
        });
        c.forEach(new BiConsumer<Integer, double[]>() {
            @Override
            public void accept(Integer integer, double[] doubles) {
                Integer countI = count.get(integer);
                Point3D sumI = sum.get(integer);
                sumI = sumI.mult(1. / countI);
                for (int c = 0; c < 3; c++)
                    doubles[c + 2] = ps[integer].get(c);

                toProcess.setP((int) (doubles[0]), (int) (doubles[1]),
                        sumI);
            }
        });


        toProcess.getImage().saveFile( out);
        return true;
    }
}
