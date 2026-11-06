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

import matrix.M3;
import matrix.PixM;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

import java.util.Arrays;
import java.util.function.Consumer;

public class Linear {
    private int type = 0;
    public static final int TYPE_2D = 0;
    public static final int TYPE_2D_2D = 1;

    private PixM[] images;
    private M3[] imagesM;

    public Linear(PixM... images) {
        type = TYPE_2D_2D;
        this.imagesM = null;
        this.images = images;
    }

    public Linear(M3... imagesM) {
        type = TYPE_2D;
        this.images = null;
        this.imagesM = imagesM;
    }

    public Linear(Image... bufferedImages) {
        type = TYPE_2D_2D;
        this.imagesM = null;
        images = new PixM[bufferedImages.length];
        final int[] i = {0};
        Arrays.stream(bufferedImages).forEach(new Consumer<Image>() {
            @Override
            public void accept(Image bufferedImage) {
                images[i[0]] = new PixM(bufferedImage);
                i[0]++;
            }
        });
        this.imagesM = null;
        this.images = images;

    }

    public boolean op2d2d(char[] op, int[][] index, int[] indexRes) {
        PixM[] workingImages = null;
        assert images != null;
        for (int x = 0; x < op.length; x++) {
            workingImages = new PixM[images.length];//.??N
            //new matrix.PixM[index[x].length];//.??N
            for (int j = 0; j < index[x].length; j++) {
                workingImages[j] = images[index[x][j]];
            }
            PixM pixM = new PixM(workingImages[0].getColumns(),
                    workingImages[0].getLines());
            for (int m = 0; m < index[x].length; m++) {
                assert workingImages[m] != null;
                for (int comp = 0; comp < workingImages[m].getCompCount(); comp++) {
                    workingImages[m].setCompNo(comp);
                    pixM.setCompNo(comp);
                    for (int i = 0; i < workingImages[m].getColumns(); i++)
                        for (int j = 0; j < workingImages[m].getLines(); j++)
                            switch (op[x]) {
                                case '+':
                                    if (m == 0)
                                        pixM.set(i, j, workingImages[0].get(i, j));
                                    pixM.set(i, j, pixM.get(i, j) + workingImages[m].get(i, j));
                                    break;
                                case '-':
                                    if (m == 0)
                                        pixM.set(i, j, workingImages[0].get(i, j));
                                    else
                                        pixM.set(i, j, pixM.get(i, j) - workingImages[m].get(i, j));
                                    break;
                                case '*':
                                    if (m == 0)
                                        pixM.set(i, j, workingImages[0].get(i, j));
                                    pixM.set(i, j, pixM.get(i, j) * workingImages[m].get(i, j));
                                    break;
                                case '/'://divide M1/M2/M3
                                    if (m == 0)
                                        pixM.set(i, j, workingImages[0].get(i, j));
                                    pixM.set(i, j, pixM.get(i, j) / workingImages[m].get(i, j));
                                    break;
                                case '~': //average
                                    if (m == 0)
                                        pixM.set(i, j, workingImages[0].get(i, j));
                                    pixM.set(i, j, pixM.get(i, j) + workingImages[m].get(i, j) / workingImages.length);
                                    break;
                                case '%':
                                    if (m == 0)
                                        pixM.set(i, j, workingImages[0].get(i, j));
                                    pixM.set(i, j, Math.IEEEremainder(pixM.get(i, j), workingImages[m].get(i, j)));
                                    break;
                                case '|':
                                    // Norme
                                    break;
                            }
                }
            }
            pixM.normalize(0.0, 1.0);
            workingImages[indexRes[x]] = pixM;
        }
        this.images = workingImages == null ? images : workingImages;
        return true;
    }

    public int getType() {
        return type;
    }

    public PixM[] getImages() {
        return images;
    }
}
