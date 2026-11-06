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

import java.util.Arrays;
import java.util.function.Consumer;

import matrix.FilterPixM;
import one.empty3.feature.M;
import matrix.M3;
import matrix.PixM;

/*
 * Multi-Image Matching using Multi-Scale Oriented Patches
 */
public class MIMmops {
    public static PixM applyMultipleFilters(PixM pixM, int level, FilterPixM... filter) {

        final PixM[] res = {pixM};
        for (int i = 0; i < level; i++) {
            // Hl(x, y) = ∇σd Pl(x, y)∇σd Pl(x, y)T∗ gσi(x, y)
            // g      -> Gauss filter
            //  Sigma -> filter size
            //  level -> filter iterations
            // i      -> Iteration value of sigma (end condition?)
            // ∇σd Pl     -> Picture "derivative" (iteration x (gradient(image)))
            //               at level l and at sigma
            // ^T ??? -> Transpose smoothed matrix ?

            // La dérivée et le filtre ne sont pas les mêmes. sommeMatrice(e-..)   et
            // (get(x+1)-2*get(x)+get(x-1) + get(y+1)+2*get(y)-get(y))/4/4 ou /1/1 ??
            Arrays.stream(filter).sequential().forEach(new Consumer<FilterPixM>() {
                @Override
                public void accept(FilterPixM filterPixM) {
                    pixM.applyFilter(filterPixM);
                }
            });
        }
        return pixM;
    }


    public M matGrad(PixM image, M3 gradientX, M3 gradientY) {
        M matGrad = null;
        // image :  smoothes
        // image : gradientX M3(w, h, 2, 1)
        // image : gradientY M3(w, h, 1, 2)

        // ?mode radar?

        // image of matrix matGrad. outer product of vectors gradient.


        // image of angles gradient orientation atan(y/x)
        return matGrad;
    }

    /*
     * fHM(x, y) = det Hl(x, y)
     * tr Hl(x, y)
     * =
     * λ1λ2
     * λ1 + λ2
     */
    public void cornerStrength(M matGrad) {

    }
}
