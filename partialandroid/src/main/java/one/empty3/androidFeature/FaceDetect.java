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
import one.empty3.feature20220726.jviolajones.Detector;


import one.empty3.libs.*;
import java.io.*;
import java.util.List;
/*
public class FaceDetect extends one.empty3.androidFeature.ProcessFiles {
    public boolean process(File in, File out) {

        File img = in;
        InputStream haarXml = Detector.class.getResourceAsStream("/haarcascade_frontalface_alt2.xml");

        Detector detector = new Detector(haarXml);
        List<Rectangle> res = null;
        try {
            File file = new File(getOutputDirectory()
                    .getAbsolutePath() + File.separator + "faces.csv");
            PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
            res = detector.getFaces(img.getAbsolutePath(), 1, 1.25f, 0.1f, 1, true);
            if (res.size() == 0) {
                System.out.printf("No face\n");
            } else {
                for (Rectangle re : res) {
                    System.out.printf("Face %d\n%d\n%d\n%d\n",
                            re.x, re.y, re.width, re.height);
                    printWriter.println(String.format("%s, %d, %d, %d, %d", out.getName(), re.x, re.y, re.width, re.height));
                }
            }
            printWriter.close();

            addSource(file);

            return res.size() > 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
*/