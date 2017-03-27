import org.jnativehook.GlobalScreen;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class getsit {
	public static void main(String[] args) throws Exception{
		Timer t = new Timer();
		NativeGetsit Native = new NativeGetsit();
		Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(Native);
		t.schedule(Native,0, 1);
	}
}

