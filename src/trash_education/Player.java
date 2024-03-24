package trash_education;

import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    private BufferedImage image;
    private BufferedImage img;
    private int x;
    private int y;
    private int speed;
    private File imageFile;
    private boolean isHolding;
    private int rubbishIndex;
    private int i;
    
    public Player()
    {
        i = 0;
        x = Parameters.playerPosx;
        y = Parameters.playerPosy;
        speed = Parameters.playerSpeed;
        isHolding = false;
        imageFile = new File("images/player.png");
 	try
        {
            image = ImageIO.read(imageFile);
 	}
        catch (IOException e)
        {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
 	}
        img = image.getSubimage(i,i,Parameters.playerWidth,Parameters.playerHeight);
    }
    
    BufferedImage getImage()
    {
        return img;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int s, boolean dir)
    {
        ///ustawianie pozycji, przy chodzeniu w lewo lub prawo w zależności od zmiennej dir
        if(dir)
        {
            x+=s;
            ///wycinanie postaci, by przypominało to chodzenie
            img = image.getSubimage(Parameters.playerWidth * (i/10),Parameters.playerHeight*3,Parameters.playerWidth,Parameters.playerHeight);
        }   
        else
        {
            x-=s;
            img = image.getSubimage(Parameters.playerWidth * (i/10),Parameters.playerHeight*2,Parameters.playerWidth,Parameters.playerHeight);
        } 
        i++;
        if(i>39)
            i = 0;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int s, boolean dir)
    {
        ///ustawianie pozycji, przy chodzeniu w góre lub dół w zależności od zmiennej dir
        if(dir)
        {
            y+=s;
            img = image.getSubimage(Parameters.playerWidth * (i/10),Parameters.playerHeight*0,Parameters.playerWidth,Parameters.playerHeight);
        }   
        else
        {
            y-=s;
            img = image.getSubimage(Parameters.playerWidth * (i/10),Parameters.playerHeight*1,Parameters.playerWidth,Parameters.playerHeight);
        } 
        i++;
        if(i>39)
            i = 0;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    
    public boolean getIsHolding()
    {
        return isHolding;
    }
    
    public void setIsHolding(boolean x, int i)
    {
        isHolding = x;
        rubbishIndex = i;
    }
    
    public int getRubbishIndex()
    {
        return rubbishIndex;
    }
}
