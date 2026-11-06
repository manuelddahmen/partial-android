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

package one.empty3.androidFeature.motion;

import matrix.PixM;
import one.empty3.libs.Image;
import one.empty3.androidFeature.ProcessFiles;
import one.empty3.ImageIO;

import java.util.ArrayList;


/*
 * Motion
 * resize part (+/-/show/hide), move part, rotate part
 */
public abstract class Motion /*extends ProcessFile */ {
    public static final int BUFFER_MAX_FRAMES = 26;
    public ArrayList<Image> frames = new ArrayList<>();


    public boolean addFrame(Image bufferedImage) {
        if (bufferedImage != null) {
            this.frames.add(bufferedImage);
        }
        return frames.size() > BUFFER_MAX_FRAMES;
    }

    public Image processFrame() {
        PixM frame1 = null;
        PixM frame2 = null;
        if (frames.size() == 0 || frames.get(0) == null)
            return null;
        if (frames.size() >= 2 && frames.size() < BUFFER_MAX_FRAMES) {

            frame1 = new PixM(frames.get(0));
            frame2 = new PixM(frames.get(1));
            frames.remove(0);
        } else if (frames.size() >= BUFFER_MAX_FRAMES) {
            frame1 = new PixM(frames.get(0));
            frame2 = new PixM(frames.get(1));
            frames.remove(0);
        } else {
            return null;
        }

        return process(frame1, frame2);
    }

    public abstract Image process(PixM frame1, PixM frame2);

}
