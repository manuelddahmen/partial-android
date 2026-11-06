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

/*
 *  // 1. amplitude and scale featyre
 *     Gauss
 *     2.1 pattern based amplification filter
 *     circle : (x, y, [xc, yc,] r) (radial)
 *     mutil circle tries
 *     2.2 linear : (x,y, xl, yl, d) (linear)
 *     other shapes
 *     3. local amplification : { (xi, yi) }
 *     kind of feature
 *     planes of colors or magnitude
 *     gradient == 0.0 ~+ 0.1
 *     surface { (xi, yi) }
 * Filtre sur toute l'image et plusieurs images.
 */
public class DaBandFilter {

    /*
     * Tracer toutes les lignes passant par Mxy et son voisinage, 1x1, 2x1, 1x2, 2x2, etc.
     * Une à n lignes, sans addition de couleurs (synthèse additive)
     *  . - ((résultat de Voisinage de GMatrix(vx, vy)) - max){comps}
     * Regarder les minima locaux à 2x2 pour commencer Mres
     * Mres = adapter la teinte de la couleur de ligne max pour RN(vx,v)-max -----vers min ou zéro.
     * relier les points adjacents si couleur similaire
     * //Retenter sur Mres{(x,y)} pour nxn avec n = n(step-1)+1
     * @param matrix matrice d'image à analyser
     */
    public DaBandFilter(PixM matrix) {

    }

}
