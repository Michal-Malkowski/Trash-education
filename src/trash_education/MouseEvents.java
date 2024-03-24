package trash_education;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseListener{
    public boolean isPressed;

    @Override
    public void mouseClicked(MouseEvent e) {}
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        isPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
       
}
