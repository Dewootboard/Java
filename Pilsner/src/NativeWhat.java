import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class NativeWhat extends TimerTask implements NativeKeyListener {
	private Robot rob;
    //BufferedImage NewImg;
    //BufferedImage SrcImg;
	private ArrayList<BufferedImage> NewImg = new ArrayList<>();
	private  ArrayList<BufferedImage> SrcImg = new ArrayList<>();
	private ArrayList<Rectangle> rect = new ArrayList<>();
    
    //Color c = new Color(255,219, 195);
    boolean run = false;
	int captures = 0;
	int x1, x2, y1, y2;
	long rgb1 = 0;
	long diff, init;

	public NativeWhat() throws Exception{
		rob = new Robot();

    	for(int k = 0; k < captures; k++){
    		NewImg.add(rob.createScreenCapture(rect.get(k)));
    		SrcImg.add(rob.createScreenCapture(rect.get(k)));
    	}
	}

	
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		if(arg0.getKeyCode() == NativeKeyEvent.VC_E){
			run = !run;
			init = 0;
			SrcImg.set(0, rob.createScreenCapture(rect.get(0)));
			System.out.println(run ? "Started!" : "Stopped");
			for(int i = rect.get(0).height-1; i >= 0; i--){
				for(int j = 0; j < rect.get(0).width; j++){
					rgb1 = SrcImg.get(0).getRGB(j, i);
					init += rgb1;
				}
			}
		}
		if(arg0.getKeyCode() == NativeKeyEvent.VC_ALT && captures == 0){
			rect.add(new Rectangle(MouseInfo.getPointerInfo().getLocation().x-1,
									MouseInfo.getPointerInfo().getLocation().y-1,
									2,
									2));

			NewImg.add(rob.createScreenCapture(rect.get(captures)));
			SrcImg.add(rob.createScreenCapture(rect.get(captures)));
			captures += 1;
			System.out.println("Added caption nr " + captures + "!");
			System.out.println("Area: (x, y):" + rect.get(0).x + ", " + rect.get(0).y + "to (x, y):" + (rect.get(0).x+2) + ", " + (rect.get(0).y+2));
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
				diff = 0;
				NewImg.set(k, rob.createScreenCapture(rect.get(k)));
		        for(int i = rect.get(k).height-1; i >= 0; i--) {
					for (int j = 0; j < rect.get(k).width; j++) {
						int rgb2 = NewImg.get(k).getRGB(j, i);
						diff += rgb2;
					}
				}
				if(Math.abs(init-diff) > 1004111) {
					try{  Thread.sleep(15);  }catch(Exception e) {}
					rob.mousePress(InputEvent.BUTTON1_MASK);
					rob.mouseRelease(InputEvent.BUTTON1_MASK);
					System.out.println("NOT SIMILAR!");
					run = !run;
					return;
				}else
					System.out.println("Similar!");
			}
		}
	}

}
