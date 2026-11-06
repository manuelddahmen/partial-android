# Consumer ProGuard rules for partialandroid

-dontwarn aQute.bnd.annotation.spi.ServiceProvider
-dontwarn io.micrometer.context.**
-dontwarn jakarta.servlet.**
-dontwarn java.lang.Module
-dontwarn java.lang.module.**
-dontwarn javax.xml.stream.**
-dontwarn org.apiguardian.api.API
-dontwarn reactor.blockhound.**
-dontwarn org.xmlpull.v1.**
-dontwarn org.kobjects.**
-dontwarn org.ksoap2.**
-dontwarn org.kxml2.**
-dontwarn org.jdom2.**
-dontwarn com.google.api.**
-dontwarn com.google.protobuf.**
-dontwarn com.google.rpc.**
-dontwarn com.google.type.**
-dontwarn java.awt.**
-dontwarn javax.swing.**
-dontwarn com.formdev.**
-dontwarn sun.misc.Unsafe
-dontwarn java.lang.invoke.MethodHandles$Lookup

-keep class * extends one.empty3.io.ProcessFile {
    public <init>();
}
-keep class one.empty3.androidFeature.** {
    public <init>();
}
