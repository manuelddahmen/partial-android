package tests;

import static org.junit.Assert.assertTrue;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import matrix.PixM;
import one.empty3.library.Lumiere;
import one.empty3.library.Point3D;
import one.empty3.libs.Color;
import one.empty3.libs.Image;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class TestMeshMasksPartialAndroidAndroid {

    @Rule
    public GrantPermissionRule getGrantPermissionRule() {
        return GrantPermissionRule.grant(
                "android.permission.READ_MEDIA_IMAGES",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.READ_MEDIA_IMAGES"
        );
    }

    public String getEmulatorPhotosDirPath() {
        File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        return downloadDir.getAbsolutePath() + File.separator;
    }

    private Context context;
    int countTestsProcessFiles = 0;
    private int countNonApplicable = 0;
    private int errors = 0;
    int maxRes = 15;
    public String emulatorPhotosDirPath = "/storage/emulated/0/Download/";

    @Before
    public void setup() {
    }


    @Test
    public void testImageColorsAndroidGetPsetPrgb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(appContext.getPackageName());

        Color[] colors = new Color[3];
        colors[0] = new Color(0xFFFF0000);
        colors[1] = new Color(0xFF00FF00);
        colors[2] = new Color(0xFF0000FF);
        String[] colorsName = {"RED", "GREEN", "BLUE"};
        for (int k = 0; k < 3; k++) {
            Bitmap argb8888 = Bitmap.createBitmap(1000,1000, Bitmap.Config.RGB_565);
            Image image = new Image(argb8888 );
            int width = image.getWidth();
            int height = image.getHeight();
            Color color = colors[k];
            System.out.println(color.getRed() + " " + color.getGreen() + " " + color.getBlue());
            image.setRgb(0, 0, color.getRgb());
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    double[] d1 = new double[5];
                    int p = image.getRgb(0, 0);
                    image.setRgb(i, j, p);
                }
            }
            File outputimage = new File(emulatorPhotosDirPath + "testPixMColorsAndroidGetPsetP-" + colorsName[k] + ".jpg");
            if (outputimage.exists())
                outputimage.delete();
            outputimage = new File(outputimage.getAbsolutePath());
            //FileOutputStream fileOutputStream = new FileOutputStream(outputimage);
            image.saveFile(outputimage);
        }
    }

    @Test
    public void testBitmapPixMColors() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context instrumentationContext = instrumentation.getContext();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Add logging to check these values (replace with your preferred logging method):
        System.out.println("Instrumentation Context: " + instrumentationContext);
        System.out.println("App Context: " + appContext);
        GrantPermissionRule grantPermissionRule = getGrantPermissionRule();

        emulatorPhotosDirPath = getEmulatorPhotosDirPath();

        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.RGB_565);
        Color color = new Color(0xFFFF0000);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bitmap.setPixel(i, j, color.getRgb());
            }
        }
        PixM pixM = new PixM(bitmap);
        File outputimage = new File(emulatorPhotosDirPath + "testBitmapPixMColorRed100x100.jpg");
        if (outputimage.exists())
            outputimage.delete();
        pixM.getImage().saveFile(outputimage);
    }

    @Test
    public void testBitmapColors() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(appContext.getPackageName());
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.RGB_565);
        Color color = new Color(0xFFFF0000);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bitmap.setPixel(i, j, color.getRgb());
            }
        }
        File outputimage = new File(emulatorPhotosDirPath + "testBitmapColorRed100x100.jpg");
        outputimage = new File(outputimage.getAbsolutePath());
        if (outputimage.exists())
            outputimage.delete();
        new Image(bitmap).saveFile(outputimage);
    }

    @Test
    public void testPixMColors() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(appContext.getPackageName());
        PixM pixM = new PixM(100, 100);
        Color color = new Color(0xFFFF0000);
        int width = pixM.getColumns();
        int height = pixM.getLines();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixM.setInt(pixM.index(i, j), color.getRgb());
            }
        }
        File outputimage = new File(emulatorPhotosDirPath + "testPixMColorRed100x100.jpg");
        if (outputimage.exists())
            outputimage.delete();
        outputimage = new File(outputimage.getAbsolutePath());
        if (outputimage.exists())
            outputimage.delete();
        //FileOutputStream fileOutputStream = new FileOutputStream(outputimage);
        pixM.getImage().saveFile(outputimage);
        //fileOutputStream.close();
    }

    @Test
    public void testPixMColorsAndroidColorValueOf1() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(appContext.getPackageName());
        PixM pixM = new PixM(100, 100);
        Color color = new Color(Color.valueOf(0xFFFF0000));
        int width = pixM.getColumns();
        int height = pixM.getLines();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixM.setInt(pixM.index(i, j), color.getRgb());
            }
        }
        File outputimage = new File(emulatorPhotosDirPath + "testPixMColorsAndroidColorValueOf1.jpg");
        if (outputimage.exists())
            outputimage.delete();
        outputimage = new File(outputimage.getAbsolutePath());
        //FileOutputStream fileOutputStream = new FileOutputStream(outputimage);
        pixM.getImage().saveFile(outputimage);
        //fileOutputStream.close();
    }

    @Test
    public void testPixMColorsAndroidColorValueOf2() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(appContext.getPackageName());
        PixM pixM = new PixM(100, 100);
        Color color = new Color(0xFFFF0000);
        int width = pixM.getColumns();
        int height = pixM.getLines();
        System.out.println(color.getRed() + " " + color.getGreen() + " " + color.getBlue());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double[] d1 = new double[5];
                pixM.setValues(i, j, color.getRed() / 255d, color.getGreen() / 255d, color.getBlue() / 255d);
            }
        }
        File outputimage = new File(emulatorPhotosDirPath + "testPixMColorsAndroidColorValueOf2.jpg");
        if (outputimage.exists())
            outputimage.delete();
        outputimage = new File(outputimage.getAbsolutePath());
        if (outputimage.exists())
            outputimage.delete();
        //FileOutputStream fileOutputStream = new FileOutputStream(outputimage);
        pixM.getImage().saveFile(outputimage);
        //fileOutputStream.close();
    }

    @Test
    public void testPixMColorsAndroidGetPsetPrgb() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(appContext.getPackageName());

        Color[] colors = new Color[3];
        colors[0] = new Color(0xFFFF0000);
        colors[1] = new Color(0xFF00FF00);
        colors[2] = new Color(0xFF0000FF);
        String[] colorsName = {"RED", "GREEN", "BLUE"};
        for (int k = 0; k < 3; k++) {
            PixM pixM = new PixM(100, 100);
            int width = pixM.getColumns();
            int height = pixM.getLines();
            Color color = colors[k];
            System.out.println(color.getRed() + " " + color.getGreen() + " " + color.getBlue());
            pixM.setP(0, 0, new Point3D(color.getRed()/255d, color.getGreen()/255d, color.getBlue()/255d));
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    double[] d1 = new double[5];
                    Point3D p = pixM.getP(0, 0);
                    pixM.setP(i, j, p);
                }
            }
            File outputimage = new File(emulatorPhotosDirPath + "testPixMColorsAndroidGetPsetP-" + colorsName[k] + ".jpg");
            if (outputimage.exists())
                outputimage.delete();
            outputimage = new File(outputimage.getAbsolutePath());
            //FileOutputStream fileOutputStream = new FileOutputStream(outputimage);
            pixM.getImage().saveFile(outputimage);
        }
    }
    @Test
    public void testPixMGetSetRGB() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(appContext.getPackageName());

        Color[] colors = new Color[3];
        colors[0] = new Color(0xFFFF0000);
        colors[1] = new Color(0xFF00FF00);
        colors[2] = new Color(0xFF0000FF);
        String[] colorsName = {"RED", "GREEN", "BLUE"};
        for (int k = 0; k < 3; k++) {
            PixM pixM = new PixM(100, 100);
            int width = pixM.getColumns();
            int height = pixM.getLines();
            Color color = colors[k];


            PixM pixM2 = new PixM(100, 100);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    for (int l = 0; l < 3; l++) {
                        pixM2.setValues(i, j, color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f);

                    }
                }
            }



            System.out.println(color.getRed() + " " + color.getGreen() + " " + color.getBlue());
            pixM.setP(0, 0, new Point3D(Lumiere.getDoubles(color.getColor())));

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    for (int l = 0; l < 3; l++) {
                        pixM.setCompNo(l);
                        pixM2.setCompNo(l);
                        pixM.set(i, j, pixM2.get(i, j));
                    }
                    Point3D p = pixM.getP(0, 0);
                    pixM.setP(i, j, p);
                }
            }
            File outputimage = new File(emulatorPhotosDirPath + "testPixMGetSetRGB-" + colorsName[k] + ".jpg");
            if (outputimage.exists())
                outputimage.delete();
            outputimage = new File(outputimage.getAbsolutePath());
            //FileOutputStream fileOutputStream = new FileOutputStream(outputimage);
            pixM.getImage().saveFile(outputimage);
        }
    }



    @Test
    public void testColorImageWriteImage() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        System.out.println(appContext.getPackageName());

        Color[] colors = new Color[3];
        colors[0] = new Color(0xFFFF0000);
        colors[1] = new Color(0xFF00FF00);
        colors[2] = new Color(0xFF0000FF);
        colors[0] = new Color(0x00FF0000);
        colors[1] = new Color(0x0000FF00);
        colors[2] = new Color(0x000000FF);
        int count2 = 0;
        int count3 = 0;

        String[] colorsName = {"RED", "GREEN", "BLUE"};
        for (int k = 0; k < 3; k++) {
            Image pixM = new Image(100, 100);
            int width = pixM.getWidth();
            int height = pixM.getHeight();


            int color = colors[k].getRgb();
            Image pixM2 = new Image(100, 100);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    pixM2.setRgb(i, j, color);
                }
            }


            File outputimage2 = new File(emulatorPhotosDirPath + "testCIgetRgb2-" + colorsName[k] + ".jpg");
            if (outputimage2.exists())
                outputimage2.delete();
            outputimage2 = new File(outputimage2.getAbsolutePath());
            //outputimage3 = new File(outputimage3.getAbsolutePath());
            //FileOutputStream fileOutputStream = new FileOutputStream(outputimage);
            pixM2.saveFile(outputimage2);
            //pixM3.saveFile(outputimage3);

            Image fromFile2 = Image.getFromFile(outputimage2);

            assert fromFile2 != null;
            int rgb2 = fromFile2.getRgb(0, 0);

            count2 += (rgb2 == colors[k].getRgb()) ? 1 : 0;

            System.out.println("count 2: " + count2);
        }


    }


    @Test
    public void addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2);
    }

}