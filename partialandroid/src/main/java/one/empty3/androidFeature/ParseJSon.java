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
public class ParseJSon {
    public List<ProcessFile> parse(String jsonString) {
        try {
            List<ProcessFile> o = new ArrayList<>();
            //assign your JSON String here
            JSONObject obj = new JSONObject(jsonString);
            String classname = "";
            JSONArray arr = obj.getJSONArray("filters");
            for (int i = 0; i < arr.length(); i++) {
                classname = arr.getJSONObject(i).getString("classname");

                Class c = Class.forName(classname);
                ProcessFile pf = (ProcessFile)
                        (c.getDeclaredConstructor().newInstance());

                o.add(pf);

                String properties = arr.getJSONObject(i).getString("properties");
                JSONObject propertiesA = new JSONObject(properties);
                Set<String> keys = propertiesA.keySet();
                for (String key : keys) {
                    String value = propertiesA.getString(key);
                    if (key.equals("classname"))
                        classname = value;
                    else if (value.split(":").length == 2) {
                        String[] array = value.split(":");
                        String vType = array[0];
                        String vValue = array[1];

                        Pojo.setP((Object) pf, key, classname, vValue);


                    }
                }
            }
            return o;
        } catch (InstantiationException
                | ClassNotFoundException
                | IllegalAccessException
                ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
*/