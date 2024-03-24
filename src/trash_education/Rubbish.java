package trash_education;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


public class Rubbish {
    private BufferedImage img;
    private int x;
    private int y;
    private int type;
    private int index;
    private String paper[];
    private String glass[];
    private String plastic[];
    private String organic[];
    private String metal[];
    private File imageFile;
    private Random random;
    private boolean active;
    public static int rubbishLeft;
    
    public Rubbish()
    {
        paper = new String[10];
        glass = new String[10];
        plastic = new String[10];
        organic = new String[10];
        metal = new String[10];
        
        random = new Random();
        ///Losowanie typu odpadu
        type = random.nextInt(Parameters.dustbinsAmount)+1;
        ///Losowanie konkretnego odpadu z danego rodzaju
        index = random.nextInt(10);
        
        ///Losowanie pozycji
        x = random.nextInt(960-10)+10;
        y = random.nextInt(720-180)+180;
        
        active = false;
        
        rubbishLeft = Parameters.rubbishAmount;
        
        switch(type)
        {
            case 1:
                switch(index)
                {
                    case 0:
                        imageFile = new File("images/paper0.png");
                        paper[index] = "Paczka";
                        break;
                    case 1:
                        imageFile = new File("images/paper1.png");
                        paper[index] = "Karton";
                        break;
                    case 2:
                        imageFile = new File("images/paper2.png");
                        paper[index] = "Serwetka";
                        break;
                    case 3:
                        imageFile = new File("images/paper3.png");
                        paper[index] = "Gazety";
                        break;
                    case 4:
                        imageFile = new File("images/paper4.png");
                        paper[index] = "Stos zniszczonych kartek";
                        break;
                    case 5:
                        imageFile = new File("images/paper5.png");
                        paper[index] = "Gazeta";
                        break;
                    case 6:
                        imageFile = new File("images/paper6.png");
                        paper[index] = "Zgnieciona kartka";
                        break;
                    case 7:
                        imageFile = new File("images/paper7.png");
                        paper[index] = "Karton po mleku";
                        break;
                    case 8:
                        imageFile = new File("images/paper8.png");
                        paper[index] = "Pudełko";
                        break;
                    case 9:
                        imageFile = new File("images/paper9.png");
                        paper[index] = "Zapisana kartka";
                        break;
                }
                break;
            case 2:
                switch(index)
                {
                    case 0:
                        imageFile = new File("images/glass0.png");
                        glass[index] = "Pobita buetlka";
                        break;
                    case 1:
                        imageFile = new File("images/glass1.png");
                        glass[index] = "Butelka po winie";
                        break;
                    case 2:
                        imageFile = new File("images/glass2.png");
                        glass[index] = "Kieliszek";
                        break;
                    case 3:
                        imageFile = new File("images/glass3.png");
                        glass[index] = "Butelka po piwie";
                        break;
                    case 4:
                        imageFile = new File("images/glass4.png");
                        glass[index] = "Butelka po szampanie";
                        break;
                    case 5:
                        imageFile = new File("images/glass5.png");
                        glass[index] = "Pobita waza";
                        break;
                    case 6:
                        imageFile = new File("images/glass6.png");
                        glass[index] = "Pobita butelka";
                        break;
                    case 7:
                        imageFile = new File("images/glass7.png");
                        glass[index] = "Pobity wazon";
                        break;
                    case 8:
                        imageFile = new File("images/glass8.png");
                        glass[index] = "Karafka";
                        break;
                    case 9:
                        imageFile = new File("images/glass9.png");
                        glass[index] = "Słoik";
                        break;
                }
                break;
            case 3:
                switch(index)
                {
                    case 0:
                        imageFile = new File("images/plastic0.png");
                        plastic[index] = "Butelka po wodzie";
                        break;
                    case 1:
                        imageFile = new File("images/plastic1.png");
                        plastic[index] = "Kubek po napoju";
                        break;
                    case 2:
                        imageFile = new File("images/plastic2.png");
                        plastic[index] = "Porwana reklamówka";
                        break;
                    case 3:
                        imageFile = new File("images/plastic3.png");
                        plastic[index] = "Czerwona torba";
                        break;
                    case 4:
                        imageFile = new File("images/plastic4.png");
                        plastic[index] = "Zgnieciona butelka";
                        break;
                    case 5:
                        imageFile = new File("images/plastic5.png");
                        plastic[index] = "Pudełko po jedzeniu";
                        break;
                    case 6:
                        imageFile = new File("images/plastic6.png");
                        plastic[index] = "Zielona torba";
                        break;
                    case 7:
                        imageFile = new File("images/plastic7.png");
                        plastic[index] = "Łyżeczka z wieczkiem po jogurcie";
                        break;
                    case 8:
                        imageFile = new File("images/plastic8.png");
                        plastic[index] = "";
                        break;
                    case 9:
                        imageFile = new File("images/plastic9.png");
                        plastic[index] = "Butelka z wodą";
                        break;
                }
                break;
            case 4:
                switch(index)
                {
                    case 0:
                        imageFile = new File("images/organic0.png");
                        organic[index] = "Lód w rożku";
                        break;
                    case 1:
                        imageFile = new File("images/organic1.png");
                        organic[index] = "Papryka zielona";
                        break;
                    case 2:
                        imageFile = new File("images/organic2.png");
                        organic[index] = "Papryka żółta";
                        break;
                    case 3:
                        imageFile = new File("images/organic3.png");
                        organic[index] = "Papryka czerwona";
                        break;
                    case 4:
                        imageFile = new File("images/organic4.png");
                        organic[index] = "Skorupka po jajku";
                        break;
                    case 5:
                        imageFile = new File("images/organic5.png");
                        organic[index] = "Marchewka";
                        break;
                    case 6:
                        imageFile = new File("images/organic6.png");
                        organic[index] = "Kość";
                        break;
                    case 7:
                        imageFile = new File("images/organic7.png");
                        organic[index] = "Zepsuta sałata";
                        break;
                    case 8:
                        imageFile = new File("images/organic8.png");
                        organic[index] = "Skórka po baranie";
                        break;
                    case 9:
                        imageFile = new File("images/organic9.png");
                        organic[index] = "Ość";
                        break;
                }
                break;
            case 5:
                switch(index)
                {
                    case 0:
                        imageFile = new File("images/metal0.png");
                        metal[index] = "Zgnieciona puszka";
                        break;
                    case 1:
                        imageFile = new File("images/metal1.png");
                        metal[index] = "Puszka po konserwach";
                        break;
                    case 2:
                        imageFile = new File("images/metal2.png");
                        metal[index] = "Nożyczki";
                        break;
                    case 3:
                        imageFile = new File("images/metal3.png");
                        metal[index] = "Zgnieciona puszka po konserwach";
                        break;
                    case 4:
                        imageFile = new File("images/metal4.png");
                        metal[index] = "Zgnieciona puszka";
                        break;
                    case 5:
                        imageFile = new File("images/metal5.png");
                        metal[index] = "Zgnieciona puszka";
                        break;
                    case 6:
                        imageFile = new File("images/metal6.png");
                        metal[index] = "Nożyczki chirurgiczne";
                        break;
                    case 7:
                        imageFile = new File("images/metal7.png");
                        metal[index] = "Bidon na wodę";
                        break;
                    case 8:
                        imageFile = new File("images/metal8.png");
                        metal[index] = "Spray w puszce";
                        break;
                    case 9:
                        imageFile = new File("images/metal9.png");
                        metal[index] = "Puszka coli";
                        break;
                }
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
    
    public boolean intersects(int xi, int yi, int w, int h)
    {
        ///Funkcja służąca do kolizji między 2 odpadami
        if(((this.x>xi && this.x<xi+w) || (this.x+this.img.getWidth()>xi && this.x+this.img.getWidth()<xi+w)) &&
          ((this.y>yi && this.y<yi+h) || (this.y+this.img.getHeight()>yi && this.y+this.img.getHeight()<yi+h)))
            return true;
        else
            return false;
    }
    
    public String getName()
    {
        switch(this.type)
        {
            case 1:
                return paper[this.index];
            case 2:
                return glass[this.index];
            case 3:
                return plastic[this.index];
            case 4:
                return organic[this.index];
            case 5:
                return metal[this.index];
        }
        return null;
    }
    
    public void changePosition()
    {
        x = random.nextInt(960-10)+10;
        y = random.nextInt(720-180)+180;
    }

    public BufferedImage getImage()
    {
        return img;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void setX(int pos)
    {
        x = pos;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setY(int pos)
    {
        y = pos;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setActive(boolean x)
    {
            active = x;
    }
    
    public boolean getActive()
    {
        return active;
    }
    
}
