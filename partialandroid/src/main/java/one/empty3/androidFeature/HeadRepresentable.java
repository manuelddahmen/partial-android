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

import one.empty3.library.Representable;
import one.empty3.library.Scene;
import one.empty3.library.core.export.STLExport;
import one.empty3.library.core.script.ExtensionFichierIncorrecteException;
import one.empty3.library.core.script.Loader;
import one.empty3.library.core.script.VersionNonSupporteeException;

import java.io.*;
import java.util.Properties;

public class HeadRepresentable extends Representable {
    public HeadRepresentable() {
        StringBuilder sb = new StringBuilder();
        Properties headRb = new Properties();
        try {
            File properties = new File("resources/stl/head.properties");

            File sceneStl = new File("resources/stl/head.stl");
            File sceneXml = new File("resources/stl/head.xml");
            File txt = new File("resources/stl/head.moo");
            headRb.load(new BufferedReader(new InputStreamReader(new FileInputStream(properties))));

            headRb.keySet().forEach(o -> {
                        sb.append(headRb.getProperty((String) o));
                        System.out.println(o + " = " + headRb.getProperty((String) o));
                    }

            );

            //sb.append(')');


            String s = sb.toString();

            new OutputStreamWriter(new FileOutputStream(txt)).write(s);


            Scene load = new Scene();

            if ((load = new Loader().load(txt, load)) != null) {


                //File sceneL = new File("resources/stl/headRewrite.moo");
                //new PrintWriter(sceneL).println(load);
                StringBuilder stringBuilder = new StringBuilder();
                load.xmlRepresentation(null, stringBuilder, load);
                new PrintWriter(sceneXml).println(stringBuilder);
                STLExport.save(sceneStl, load, true);
                new PrintWriter(sceneStl).println(sb.toString());

                System.out.println(load);
            } else
                System.out.println("Model moo not loaded");

        } catch (IOException | ExtensionFichierIncorrecteException |
                 VersionNonSupporteeException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HeadRepresentable headRepresentable = new HeadRepresentable();
    }
}
