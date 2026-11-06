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
// Implementing Coppersmith Winograd Algorithm in Java

import java.util.Random;

class GFG {

    public static boolean coppersmithWinograd(double[][] M1,
                                              double[][] M2,
                                              double[][] M3, int n) {
        double[][] a = new double[n][1];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            a[i][0] = rand.nextInt() % 2;
        }

        double[][] M2a = new double[n][1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < n; k++) {
                    M2a[i][j]
                            = M2a[i][j] + M2[i][k] * a[k][j];
                }
            }
        }

        double[][] M3a = new double[n][1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < n; k++) {
                    M3a[i][j]
                            = M3a[i][j] + M3[i][k] * a[k][j];
                }
            }
        }

        double[][] M12a = new double[n][1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < n; k++) {
                    M12a[i][j]
                            = M12a[i][j] + M1[i][k] * M2a[k][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            M12a[i][0] -= M3a[i][0];
        }
        boolean sameResultantMatrix = true;
        for (int i = 0; i < n; i++) {
            if (M12a[i][0] == 0)
                continue;
            else
                sameResultantMatrix = false;
        }
        return sameResultantMatrix;
    }

    // Driver's Function m1*m2==m3
    public static void inv(double[][] M2, int n) {

        /// "Input the dimension of the matrices: "


        // "Input the 1st or M1 matrix: "
        double[][] M1 = {{1, 2}, {3, 4}};
        // "Input the 2nd or M2 matrix: "

        // double[][] M2 = { { 2, 0 }, { 1, 2 } };

        // "Input the result or M3 matrix: "
        double[][] M3 = {{4, 4}, {10, 8}};

        if (coppersmithWinograd(M1, M2, M3, n))
            System.out.println("Resultant matrix is Matching");
        else
            System.out.println("Resultant matrix is not Matching");
    }
}
