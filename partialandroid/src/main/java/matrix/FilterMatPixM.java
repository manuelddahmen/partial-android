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

package matrix;
public abstract class FilterMatPixM {
/*
    public int getCompNo() {
        return compNo;
    }

    public void setCompNo(int compNo) {
        this.compNo = compNo;
    }
*/

    public abstract M3 filter(M3 original);

    public abstract void element(M3 source, M3 copy, int i, int j, int ii, int ij);

    public abstract M3 norm(M3 m3, M3 copy);
}
