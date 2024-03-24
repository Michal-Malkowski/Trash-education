package trash_education;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class KeyEvents implements KeyListener{
    public boolean up,down,right,left,space;
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        
        ///Po naciśnięciu klawisza odpowiednia zmienna jest ustawiana na true, co świadczy o tym, że klawisz jest wciśnięty
        switch(key)
        {
            case KeyEvent.VK_W:
                up=true;
                break;
            case KeyEvent.VK_S:
                down=true;
                break;
            case KeyEvent.VK_D:
                right=true;
                break;
            case KeyEvent.VK_A:
                left=true;
                break;
            case KeyEvent.VK_SPACE:
                space=true;
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        ///Po zwolnieniu przycisku odpowiednia zmienna jest ustawiana na false
        int key = e.getKeyCode();
        switch(key)
        {
            case KeyEvent.VK_W:
                up=false;
                break;
            case KeyEvent.VK_S:
                down=false;
                break;
            case KeyEvent.VK_D:
                right=false;
                break;
            case KeyEvent.VK_A:
                left=false;
                break;
            case KeyEvent.VK_SPACE:
                space=false;
                break;
        }
    }
    
    public void mouseTyped(MouseEvent e)
    {
        
    }
    
    public void mousePressed(MouseEvent e)
    {
        
    }
    
}
