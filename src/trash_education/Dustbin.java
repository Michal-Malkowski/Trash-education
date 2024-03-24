package trash_education;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dustbin {
    private BufferedImage img;
    private int x;
    private int y;
    private int type;
    File imageFile;
    
    public Dustbin(int t)
    {
        type = t;
        ///Wczytywanie kontenerów, każdy ma swój typ (podobnie jak odpady)
        switch(type)
        {
            case 1:
                imageFile = new File("images/dustbin1.png");
                x = Parameters.dustbin1Posx;
                y = Parameters.dustbin1Posy;
                break;
            case 2:
                imageFile = new File("images/dustbin2.png");
                x = Parameters.dustbin2Posx;
                y = Parameters.dustbin2Posy;
                break;
            case 3:
                imageFile = new File("images/dustbin3.png");
                x = Parameters.dustbin3Posx;
                y = Parameters.dustbin3Posy;
                break;
            case 4:
                imageFile = new File("images/dustbin4.png");
                x = Parameters.dustbin4Posx;
                y = Parameters.dustbin4Posy;
                break;
            case 5:
                imageFile = new File("images/dustbin5.png");
                x = Parameters.dustbin5Posx;
                y = Parameters.dustbin5Posy;
                break;
        }
        
 	try
        {
            img = ImageIO.read(imageFile);
 	}
        catch (IOException e)
        {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
 	}
    }
    
    public BufferedImage getImage()
    {
        return img;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
}
