import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;

public class Sniper {
    public static void main(String[] args) throws Exception{
        Timer t = new Timer();
        NativeWhat Native = new NativeWhat();
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(Native);
        t.schedule(Native,0, 1);
    }
}

