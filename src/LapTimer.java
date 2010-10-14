/*  LapTimer
 * 
 *  25/8/2010
 * 
 *  Victoria Hone
 * 
 *  v1.0.0
 * 
 *  This class generates a lap timer to display on the screen
 *  while the game is running. It provides functions to get the
 *  current lap time, the lap time of the previous race and
 *  format the time for display on the screen.
 * 
 */

import java.awt.Color;
import java.util.Calendar;

public class LapTimer extends UIObject {
    
	//THREAD: GRAPHICS/UI
    private static int width = 100;
    private static int height = 50;
    private static int xpos = 0;
    private static int ypos = 0;
    private static int buffer = 30;
    
       
    private long startTime = 0;
    private long stopTime = 0;
   
   
    private boolean running = false;
     
    public void show(boolean toShow) {
    	show = toShow;
    }
    
    public LapTimer() {
    	xpos = maxwidth - (width + buffer);
    	ypos = buffer;
    	
    	start();
    }

	public String getTime() {
        if (running){
            long currentTime = Calendar.getInstance().getTimeInMillis();
            return formatTime(currentTime - startTime);
        }else {
            return formatTime(stopTime - startTime);
        }
    }
   
    public void start() {
        running = true;
        startTime = Calendar.getInstance().getTimeInMillis();
    }
       
    public void stop() {
        running = false;
        stopTime = Calendar.getInstance().getTimeInMillis();
    }
   
    public void update() {
    	if (show) {
    		graphics.setColor(Color.BLACK);
    		graphics.fillRoundRect(xpos, ypos, width, height, 5, 5);
    		graphics.setColor(Color.WHITE);
    		graphics.drawRoundRect(xpos, ypos, width, height, 5, 5);
    		graphics.drawString("Lap Timer", ((xpos + width/2) - ("Lap Timer".length()*	6)/2), ypos + height/3);
    		graphics.drawString(getTime(), ((xpos + width/2) - (getTime().length()*6)/2) , ypos + height - 10 );
    	}
    }
    
    private String formatTime(long time){
        long min = time/60000;
        long remainder = time%60000;
        long sec = remainder/1000;
        long tenths = (remainder%1000)/10;
        return (min + ":" + sec + ":" + tenths);
    }
   
}
