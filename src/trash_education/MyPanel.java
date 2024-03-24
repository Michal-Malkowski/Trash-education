package trash_education;

import java.awt.*;
import javax.swing.*;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MyPanel extends JPanel implements Runnable{
    private BufferedImage background;
    private Dimension dimension;
    private Thread gameThread;
    private KeyEvents keyEvents;
    private Player player;
    private Dustbin dustbins[];
    private Rubbish rubbish[];
    private double drawInterval;
    private double nextDrawTime;
    private double remainingTime;
    private File imageFile;
    private Menu menu;
    private Font f;
    private MouseEvents mouseEvents;
    private boolean holdingBreak; ///odpowiada za przerwe miedzy podniesieniem a opuszczeniem odpadu
    private String message;
    private boolean mess;
    private long messTimeStart;
    private long messTimeEnd;
    private boolean mouseReleased; ///wymaga drugiego wcisniecia myszki by cos wykonac
    private boolean gameEnded;
    private String gameEndedS;
    
    public MyPanel()
    { 
        ///Wczytywanie zdjęcia tła gry
 	imageFile = new File("images/background.jpg");
 	try
        {
            background = ImageIO.read(imageFile);
 	}
        catch (IOException e)
        {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
 	}
        
        keyEvents = new KeyEvents();
        mouseEvents = new MouseEvents();
        mouseReleased = true;
        gameEnded = false;
        gameEndedS = new String("Gra zakończona!");
 
        nextLevel();
                 
        mess = false;
        message = new String();
        menu = new Menu();
        f = new Font("Times New Roman", Font.BOLD,48);
        this.setFont(f);
 	dimension = new Dimension(Parameters.width, Parameters.height);
        this.setBackground(Color.white);
 	this.setPreferredSize(dimension);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyEvents);
        this.addMouseListener(mouseEvents);
        this.setFocusable(true);
    }
    
    public void nextLevel()
    {
        /*
        Funkcja odpowiadająca za realizację kolejnego poziomu
        generowane losowo są odpady, a także odpowiednia liczba kontenerów na śmieci
        */
        player = new Player();
        dustbins = new Dustbin[Parameters.dustbinsAmount];
        for(int i=0; i<Parameters.dustbinsAmount; i++)
            dustbins[i] = new Dustbin(i+1);
        rubbish = new Rubbish[Parameters.rubbishAmount];
        ///Pętla poniżej odpowiada za odpowiednie rozlokowanie odpadów
        for(int i=0; i<Parameters.rubbishAmount; i++)
        {
            rubbish[i] = new Rubbish();
            for(int j=0; j<i; j++)
            {
                ///W momencie, gdy odpady pokrywają się losowana jest nowa pozycja i
                ///ponownie porównywana z każdym dotychczas leżącym odpadem
                if(rubbish[i].intersects(rubbish[j].getX(), rubbish[j].getY(), rubbish[j].getImage().getWidth(), rubbish[j].getImage().getHeight()))
                {
                    j=-1;
                    rubbish[i].changePosition();
                }
            }
        }
    }
 
    public void startThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run()
    {         
        ///Fragment kodu odpowiadający za występowanie 60 klatek na sekunde
        drawInterval = 1000000000/Parameters.fps;
        nextDrawTime = System.nanoTime() + drawInterval;    
        while(gameThread != null)
        {
            update();
            repaint();
            try
            {
                remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if(remainingTime < 0)
                    remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void update()
    {
        ///Fragment odpowiadający za odczyt klawiatury i poruszanie postaci
        if(keyEvents.up && player.getY() > 0)
            player.setY(player.getSpeed(), false);
        else if(keyEvents.down && player.getY() < Parameters.height-player.getImage().getHeight())
            player.setY(player.getSpeed(), true);
        else if(keyEvents.right && player.getX() < background.getWidth()-player.getImage().getWidth())
            player.setX(player.getSpeed(), true);
        else if(keyEvents.left && player.getX() > 0)
            player.setX(player.getSpeed(), false);
        
        
        ///Informacje o przedmiocie nie są wyświetlane w momencie gdy gracz go upuści
        if(!player.getIsHolding())
            menu.show("");
        
        for(int i=0; i<Parameters.rubbishAmount; i++)
        {
            ///warunki, gdy gracz koliduje z odpadem
            if(((rubbish[i].getX()>player.getX() && rubbish[i].getX()<player.getX()+player.getImage().getWidth()) ||
              (rubbish[i].getX()+rubbish[i].getImage().getWidth()>player.getX() &&
               rubbish[i].getX()+rubbish[i].getImage().getWidth()<player.getX()+player.getImage().getWidth())) &&
              ((rubbish[i].getY()>player.getY() && rubbish[i].getY()<player.getY()+player.getImage().getHeight()) ||
              (rubbish[i].getY()+rubbish[i].getImage().getHeight()>player.getY() &&
               rubbish[i].getY()+rubbish[i].getImage().getHeight()<player.getY()+player.getImage().getHeight())))
            {
                ///Gracz wchodzi na odpad
                if(!rubbish[i].getActive() && !player.getIsHolding())
                {
                    menu.show(rubbish[i].getName());
                    if(keyEvents.space && !holdingBreak)
                    {
                        ///Gracz podnosi odpad
                        rubbish[i].setActive(true);
                        player.setIsHolding(true,i);
                        holdingBreak = true;
                    }
                }  
            }      
        }
        
        for(int i=0; i<Parameters.dustbinsAmount; i++)
        {
            ///warunki, gdy gracz koliduje z kontenerem
            if(((player.getX()>dustbins[i].getX() && player.getX()<dustbins[i].getX()+dustbins[i].getImage().getWidth()) ||
              (player.getX()+player.getImage().getWidth()>dustbins[i].getX() &&
               player.getX()+player.getImage().getWidth()<dustbins[i].getX()+dustbins[i].getImage().getWidth())) &&
              ((player.getY()>dustbins[i].getY() && player.getY()<dustbins[i].getY()+dustbins[i].getImage().getHeight()) ||
              (player.getY()+player.getImage().getHeight()>dustbins[i].getY() &&
               player.getY()+player.getImage().getHeight()<dustbins[i].getY()+dustbins[i].getImage().getHeight())))
            {
                if(keyEvents.space && player.getIsHolding() && !holdingBreak)
                {
                    ///Gracz wyrzuca odpad do kontenera
                    player.setIsHolding(false,player.getRubbishIndex());
                    if(rubbish[player.getRubbishIndex()].getType() == i+1)
                    {
                        ///Dobry kontener
                        menu.increasePoints();
                        message = "Dobrze! +1 punkt!";
                    }
                    else
                    {
                        ///Zły kontener
                        message = "Źle! Próbuj dalej!";
                    }
                    ///Holding break odpowiada za chwilowa przerwe po podniesieniu przedmiotu
                    holdingBreak = true;
                    ///mess odpowiada za wyswietlenie odpowiedniego komunikatu o tym czy dostajemy punkt
                    mess = true;
                    messTimeStart = System.currentTimeMillis();
                    
                    Rubbish.rubbishLeft--;
                }
            }      
        }
        
        if(keyEvents.space && player.getIsHolding() && !holdingBreak)
        {
            ///Gracz wyrzuca odpad na ziemie
            rubbish[player.getRubbishIndex()].setActive(false);
            rubbish[player.getRubbishIndex()].setX(player.getX());
            rubbish[player.getRubbishIndex()].setY(player.getY());
            player.setIsHolding(false,player.getRubbishIndex());
            holdingBreak = true;
        }
        
        if(!keyEvents.space)
            holdingBreak = false;
        
        if(Rubbish.rubbishLeft == 0)
        {
            ///Gdy na mapie nie ma odpadów generowany jest kolejny poziom lub gra jest zakańczana
            if(Parameters.dustbinsAmount < 5)
            {
                Parameters.dustbinsAmount++;
                Parameters.rubbishAmount+=2;
                menu.increaseLevel();
                nextLevel();
            }
            else
            {
                gameEnded = true;
                ///KONIEC GRY
            }
        }
        
        ///Wyświetlenie na 2 sekundy informacji o tym czy dostajemy punkt
        if(mess)
        {
            messTimeEnd = System.currentTimeMillis();
            if(messTimeEnd - messTimeStart > 2000)
                mess = false;
        }
        
        /*
        i == 0 odpowiada napisowi menu
        i == 1 napisowi restart
        i == 2 napisowi koniec gry
        wartość true ustawiana jest w momencie, gdy wybierzemy którąś z tych opcji
        */
        for(int i=0; i<3; i++)
            menu.changeActive(i,false);
        
        /*
        całość do odczytu z myszką zawarta jest w try, ponieważ,
        gdy kursor znajdzie się poza oknem wyskakuje błąd
        */
        try
        {
            if(menu.getMenuRect().inside((int)this.getMousePosition().x,(int)this.getMousePosition().y))
            {
                menu.changeActive(0, true);

                if(mouseEvents.isPressed && mouseReleased)
                {
                    menu.menuClicked();
                    ///zmienna mouseReleased odpowiada za przerwe po kliknięciu(aby ponownie wybrać opcje trzeba kliknąć po raz drugi)
                    mouseReleased = false;
                }
                
                if(!mouseEvents.isPressed)
                    mouseReleased = true;
                
                
            }
            
            if(menu.getMenuClicked())
            {
                if(menu.getRestartRect().inside((int)this.getMousePosition().x,(int)this.getMousePosition().y))
                {
                    menu.changeActive(1, true);
                    
                    if(mouseEvents.isPressed && mouseReleased)
                    {
                        Parameters.dustbinsAmount = 3;
                        Parameters.rubbishAmount = 5;
                        menu.reset();
                        gameEnded = false;
                        nextLevel();
                        mouseReleased = false;
                    }

                    if(!mouseEvents.isPressed)
                        mouseReleased = true;
                }
                else if(menu.getEndGameRect().inside((int)this.getMousePosition().x,(int)this.getMousePosition().y))
                {
                    menu.changeActive(2, true);
                    
                    if(mouseEvents.isPressed && mouseReleased)
                    {
                        System.exit(0);
                        mouseReleased = false;
                    }

                    if(!mouseEvents.isPressed)
                        mouseReleased = true;
                }
            }
        }
        catch (Exception e)
        {
            
 	}
        
        
    }
    
    public void paintComponent(Graphics g)
    {
        ///Fragment odpowiadający za wyświetlanie elementów
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(background, 0, 0, this);
        if(!gameEnded)
        {
            for(int i=0; i<Parameters.dustbinsAmount; i++)
                g2d.drawImage(dustbins[i].getImage(), dustbins[i].getX(), dustbins[i].getY(), this);
            for(int i=0; i<Parameters.rubbishAmount; i++)
                if(!rubbish[i].getActive())
                    g2d.drawImage(rubbish[i].getImage(), rubbish[i].getX(), rubbish[i].getY(), this);
            g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        else
            g2d.drawString(gameEndedS, Parameters.messagePosx, Parameters.messagePosx);
        menu.draw(g2d, gameEnded);
        if(mess)
            g2d.drawString(message, Parameters.messagePosx, Parameters.messagePosx);
        g2d.dispose();
    }
        
        
    
}
