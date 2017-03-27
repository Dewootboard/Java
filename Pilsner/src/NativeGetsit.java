import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;

public class NativeGetsit extends TimerTask implements NativeKeyListener {
	private Robot rob;

	private ArrayList<BufferedImage> NewImg = new ArrayList<>();
	private  ArrayList<BufferedImage> SrcImg = new ArrayList<>();
	private ArrayList<Rectangle> rect = new ArrayList<>();

    boolean run = false;
	boolean secondArea = false;
	int captures = 0;
	int x1, x2, y1, y2;

	public NativeGetsit() throws Exception{
		rob = new Robot();

    	for(int k = 0; k < captures; k++){
    		NewImg.add(rob.createScreenCapture(rect.get(k)));
    		SrcImg.add(rob.createScreenCapture(rect.get(k)));
    	}
	}

	
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		if(arg0.getKeyCode() == NativeKeyEvent.VC_F){
			run = !run;
			System.out.println(run ? "Started!" : "Stopped");
		}
		if(arg0.getKeyCode() == NativeKeyEvent.VC_C) {
			if (!secondArea) {
				x1 = MouseInfo.getPointerInfo().getLocation().x;
				y1 = MouseInfo.getPointerInfo().getLocation().y;
				secondArea = !secondArea;
			} else {
				x2 = MouseInfo.getPointerInfo().getLocation().x;
				y2 = MouseInfo.getPointerInfo().getLocation().y;

				rect.add(new Rectangle(x1, y2, Math.abs(x2 - x1), Math.abs(y1 - y2)));

				NewImg.add(rob.createScreenCapture(rect.get(captures)));
				SrcImg.add(rob.createScreenCapture(rect.get(captures)));
				captures += 1;
				System.out.println("Added caption nr " + captures + "!");
				System.out.println("Area: (x, y):" + x1 + ", " + y2 +
						"to (x, y):" + x2 + ", " + y1);
				secondArea = !secondArea;
			}
		}
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		if(run){
			for(int k = 0; k < captures; k++){
				NewImg.set(k, rob.createScreenCapture(rect.get(k)));
		        for(int i = rect.get(k).height-1; i >= 0; i--) {
					for (int j = 0; j < rect.get(k).width; j++) {
						if(SrcImg.get(k).getRGB(j,i) != NewImg.get(k).getRGB(j,i)) {
							rob.mouseMove(j + rect.get(k).x, i + rect.get(k).y);
							rob.mousePress(InputEvent.BUTTON1_MASK);
							try{  Thread.sleep(10);  }catch(Exception e) {}
							rob.mouseRelease(InputEvent.BUTTON1_MASK);
							SrcImg.set(k, rob.createScreenCapture(rect.get(k)));
							return;
						}
					}
				}
			}
		}
	}

}
