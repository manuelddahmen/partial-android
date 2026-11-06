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
import one.empty3.library1.tree.*;

public class DirectMaskFilter {
    PixM m1;
    PixM m2;

    public DirectMaskFilter(PixM m1, PixM m2) {

        this.m1 = m1;
        this.m2 = m2;
    }

    /* (M3.p =) = p1x, p1y,
    
, p2x, p2y,c1r,c2g b a, w, h, ww, wh */
    public PixM applyOperator(String[] formulaColorComps) {
        PixM m3 = new PixM(m1.getColumns(), m1.getLines());
        AlgebraicTree[] treeA = new AlgebraicTree[formulaColorComps.length];
        try {
            for (int c = 0; c < formulaColorComps.length; c++) {
                treeA[c] = new AlgebraicTree(formulaColorComps[c]);

                for (int i = 0; i < m1.getColumns(); i++)
                    for (int j = 0; j < m1.getLines(); j++) {
                        AlgebraicTree tree = treeA[c];
                        tree.setParameter("p1x", (double) i);
                        tree.setParameter("p2x", (double) i);
                        tree.setParameter("p1y", (double) j);
                        tree.setParameter("p2y", (double) j);

                        m1.setCompNo(0);
                        tree.setParameter("c1r", m1.get(i, j));
                        m1.setCompNo(1);
                        tree.setParameter("c1g", m1.get(i, j));
                        m1.setCompNo(2);
                        tree.setParameter("c1b", m1.get(i, j));
                        m2.setCompNo(0);
                        tree.setParameter("c2r", m2.get(i, j));
                        m2.setCompNo(1);
                        tree.setParameter("c2g", m2.get(i, j));
                        m2.setCompNo(2);
                        tree.setParameter("c2b", m2.get(i, j));
                        m1.setCompNo(3);
                        tree.setParameter("c1a", m1.get(i, j));
                        m2.setCompNo(3);
                        tree.setParameter("c2a", m2.get(i, j));

                        tree.setParameter("w", (double) m1.getColumns());

                        tree.setParameter("h", (double) m1.getLines

                                ());

                        tree.setParameter("ww", (double) m2.getColumns());

                        tree.setParameter("wh", (double) m2.getLines());


                        tree.construct();

                        double value = (double) (Double) (tree.eval().getElem());
                        m3.setCompNo(c);
                        m3.set(i, j, value);

                    }
            }
        } catch (AlgebraicFormulaSyntaxException | TreeNodeEvalException ex) {
            ex.printStackTrace();
        }


        return m3;

    }


}
