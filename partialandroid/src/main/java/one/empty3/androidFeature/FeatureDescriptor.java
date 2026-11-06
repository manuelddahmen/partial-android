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

/*
public class FeatureDescriptor extends matrix.PixM {


    private matrix.PixM m;
    private matrix.PixM pi;
    private List<Point3D> poi;

    public FeatureDescriptor() {
        super(3, 3);
    }

    public boolean setDescription(double[][] values) {
        for (int i = 0; i < lines; i++)
            for (int j = 0; j < columns; j++)
                set(i, j, values[i][j]);

        return true;
    }

    public void setPixM(matrix.PixM m) {
        this.m = m;
        HarrisToPointInterest h = new HarrisToPointInterest(
                m.getColumns(), m.getLines());
        pi = m.applyFilter(h);
        poi = h.getPoi();
    }

    // table line
    // featuredescriptor, image, imagelocation, matchscore*
    // *e min
    public List<FeatureImageLocationMatchScore> matchesAll(FeatureDescriptor[] fd, File[] set) {
        List<FeatureImageLocationMatchScore> l = new ArrayList<>();

        for (File file : set) {
            try {
                matrix.PixM pm = new PixM(ImageIO.read(file));
                for (FeatureDescriptor f : fd) {
                    f.setPixM(pm);
                    for (int i = 0; i < poi.size(); i++) {
                        double de = f.deltaEnergy((int) (double) (poi.get(i).getX()), (int) (double) (poi.get(i).getY()));
                        if (de < 0.5) {
                            l.add(new FeatureImageLocationMatchScore());
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return l;

    }


    public double deltaEnergy(int i, int j) {
        return pi.getColorsRegion(i - columns / 2, j - lines / 2, columns, lines, columns, lines).distance(this);
    }


    public double filter(double x, double y) {


        return 0.0;
    }


    public static double[][][] getFeatureSampleDescr(int mSize,
                                                     double max, double[][] anglesIntensitiesRatio) {
        double[][][] m = new double[anglesIntensitiesRatio.length][mSize][mSize];
        return m;
    }
}
*/