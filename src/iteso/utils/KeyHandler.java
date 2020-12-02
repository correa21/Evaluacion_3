package iteso.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    /**KEY DEFINITIONS */
    public static final int JUMP = KeyEvent.VK_W;
    public static final int LEFT = KeyEvent.VK_A;
    public static final int RIGHT = KeyEvent.VK_D;
    public static final int SHOOT = KeyEvent.VK_SPACE;
    public static final int ESCAPE = KeyEvent.VK_ESCAPE;
    public static final int BFB = KeyEvent.VK_Q;


    private boolean[] keyStatus; 
    
    public KeyHandler()
    {
        keyStatus = new boolean[256]; 
    }
    
    public boolean getKeyStatus(int keyCode)
    {
        if(keyCode < 0 || keyCode > 255)
        {
            return false; 
        }
        else
        {
            return keyStatus[keyCode]; 
        }
    }
    public void setKeyStatus(int keyCode ,boolean status){
        keyStatus[keyCode] = status;
    }
    
    public void resetController()
    {
        keyStatus = new boolean[256]; 
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //do nothing
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keyStatus[ke.getKeyCode()] = true; 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keyStatus[ke.getKeyCode()] = false; 
    }
}
