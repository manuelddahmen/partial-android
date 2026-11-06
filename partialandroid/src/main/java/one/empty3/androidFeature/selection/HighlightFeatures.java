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

package one.empty3.androidFeature.selection;


import java.io.File;
import java.util.List;

import one.empty3.ImageIO;

import one.empty3.library.ITexture;
import one.empty3.library.Lumiere;
import matrix.PixM;
import one.empty3.library.Point3D;
import one.empty3.library.Scene;
import one.empty3.library.core.nurbs.ParametricCurve;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

public class HighlightFeatures extends one.empty3.androidFeature.ProcessFiles {

    /*
     *
     * @param points Sélection de points
     * @param img Image sur laquelle dessiner
     * @param col Couleur ou texture de dessin
     */
    public void pasteList(List<Point3D> points, PixM img, ITexture col) {

        for (int i = 0; i < points.size(); i++) {

            int x = (int) (double) points.get(i).getX();
            int y = (int) (double) points.get(i).getY();

            int rgb = col.getColorAt(
                    points.get(i).getX() / img.getColumns(),
                    points.get(i).getY() / img.getLines());

            double[] rgbD = Lumiere.getDoubles(rgb);
            for (int i1 = 0; i1 < 3; i1++) {
                img.setCompNo(i1);
                img.set(x, y, rgbD[i1]);
            }

        }

    }

    /*
     *
     * @param points Sélection de points
     * @param img Image sur laquelle dessiner
     * @param objets Objets à dessiner sur l'image (3d-2d)
     */
    public void pasteList(List<Point3D> points, PixM img, Scene objets) {

        for (int i = 0; i < points.size(); i++) {

            int x = (int) (double) points.get(i).getX();
            int y = (int) (double) points.get(i).getY();

            objets.getObjets().getData1d().forEach(representable -> {
                if (representable instanceof ParametricCurve)
                    img.plotCurve((ParametricCurve) representable, representable.texture());///Clip curve for moving, rotating, scaling
                else if (representable instanceof Point3D) {
                    img.setValues((int) (double) ((Point3D) representable).getX(),
                            (int) (double) ((Point3D) representable).getY(),
                            Lumiere.getDoubles(representable.texture().getColorAt(0.5, 0.5)));
                }
            });

        }

    }

    public void openCsv() {

    }

    public void writeCsv(String filename, int xPercent, int yPercent, double... vector) {
    /*
        Misérables oeufs mignolet

        Te voilà jeté en freestyle
        Comme un steak à l 'ail
        J'aime les vaches
        et les viandes
        J'aime les femmes
        Et les comptes
        En banques que
        Je découpe tous
        à la hache
        Ma kekette
        Et mes boulets
        Au bouquet
        Des bollets
        Dans l'bosquet
        Des balais
        Déblayent
        Les lendemains
        De la fête
        A saints de putains
        Qui mont'ses hein
        Dans le purin
        Police politik
        Potiche pissin'
        Ca c'est drôle
        Drole de drame
        Dans l'émoi
        Des piques
        De Véronique
        La putain du diable
        Ses vérolaises
        Ah que
        Hackerdôme
        Dos au mur
        Eh c'est sûr
        Et sertis
        De pistolet
        Un anchois
        En chocolat
    */
    }

    public void closenCsv() {

    }

    @Override
    public boolean process(File in, File out) {
        Image read = ImageIO.read(in);
        PixM pixM = PixM.getPixM(read.getImage());
        File stackItem = getStackItem(1);
        PixM original = new PixM(ImageIO.read(stackItem));
        int cadre = (int) Math.min((pixM.getColumns() + pixM.getLines()) / 2., 10.);
        for (int i = 0; i < pixM.getColumns(); i++)
            for (int j = 0; j < pixM.getLines(); j++) {
                double luminance = pixM.luminance(i, j);
                if (luminance > 0.1) {
                    for (int x = i - cadre; x < i + cadre; x++)
                        for (int y = j - cadre; y < j + cadre; y++) {
                            if ((x == i - cadre) || (y == j - cadre) || x == i + cadre - 1 || y == j + cadre - 1)
                                original.setValues(x, y, 1, 1, 0);
                        }
                }
            }
        original.normalize(0., 1., 0., 1.).getImage().saveFile(out);

        return true;
    }
}
