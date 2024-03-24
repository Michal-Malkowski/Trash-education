package trash_education;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashSet;

public class Menu {
    private String menuS;
    private String levelS;
    private String pointsS;
    private String timeS;
    private String infoS;
    private String restartS;
    private String endGameS;
    private int level;
    private int points;
    private long time;
    private long timeStart;
    private boolean menuB;
    private Rectangle menuR;
    private Rectangle restartR;
    private Rectangle endGameR;
    private boolean active[];
    
    public Menu()
    {
        level = 1;
        points = 0;
        timeStart = System.currentTimeMillis();
        
        menuS = new String("MENU");
        levelS = new String("Poziom: "+level);
        pointsS = new String("Punkty: "+ points);
        timeS = new String();
        infoS = new String();
        restartS = new String("Nowa gra");
        endGameS = new String("Koniec gry");
        menuR = new Rectangle(Parameters.menuPosx,Parameters.menuPosy-32,145,32);
        restartR = new Rectangle(Parameters.levelPosx,Parameters.levelPosy-32,200,32);
        endGameR = new Rectangle(Parameters.pointsPosx,Parameters.pointsPosy-32,225,32);        
        
        menuB = false;
        active = new boolean[3]; /// 0 - menu  1 - restart  2 - end
    }
    
    public void show(String name)
    {
        infoS = name;
    }
    
    public void increasePoints()
    {
        points++;
        pointsS = ("Punkty: " + points);
    }
    
    public void increaseLevel()
    {
        level++;
        levelS = ("Poziom: " + level);
    }
    
    public void draw(Graphics2D g2d, boolean gameEnded)
    {
        ///odliczanie od początku gry
        time = System.currentTimeMillis() - timeStart;
        if(!gameEnded)
            timeS = "Czas: " + (int)time/1000 + "s";
        ///Zmienianie koloru napisu, po najechaniu na niego myszką
        if(active[0])
            g2d.setColor(Color.red);
        else
            g2d.setColor(Color.black);
        
        ///Wyświetlanie na ekranie odpowiednich napisów
        g2d.drawString(menuS, Parameters.menuPosx, Parameters.menuPosy);
        g2d.setColor(Color.black);
        ///Gdy menu nie jest kliknięte
        if(!menuB)
        {
            g2d.drawString(levelS, Parameters.levelPosx, Parameters.levelPosy);
            g2d.drawString(pointsS, Parameters.pointsPosx, Parameters.pointsPosy);
            g2d.drawString(timeS, Parameters.timePosx, Parameters.timePosy);
            drawStringMultiLine(g2d, infoS, 10, Parameters.infoPosx, Parameters.infoPosy);
        }
        ///Gdy menu jest kliknięte
        else
        {
            if(active[1])
                g2d.setColor(Color.red);
            else
                g2d.setColor(Color.black);
            g2d.drawString(restartS, Parameters.levelPosx, Parameters.levelPosy);
            
            if(active[2])
                g2d.setColor(Color.red);
            else
                g2d.setColor(Color.black); 
            g2d.drawString(endGameS, Parameters.pointsPosx, Parameters.pointsPosy);
        }
    }
    
    ///Funkcja odpowiadająca za wyświetlanie napisów ze spacją pod sobą (inaczej by się nie zmieściły
    private static void drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y)
    {
        FontMetrics m = g.getFontMetrics();
        if(m.stringWidth(text) < lineWidth)
        {
            g.drawString(text, x, y);
        }
        else
        {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for(int i = 1; i < words.length; i++) {
                if(m.stringWidth(currentLine+words[i]) < lineWidth)
                {
                    currentLine += " "+words[i];
                }
                else
                {
                    g.drawString(currentLine, x, y);
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            if(currentLine.trim().length() > 0) {
                g.drawString(currentLine, x, y);
            }
        }
    }
    
    public void reset()
    {
        points = 0;
        pointsS = ("Punkty: " + points);
        level = 0;
        levelS = ("Poziom: " + level);
        timeStart = System.currentTimeMillis();
        menuB = false;
    }
     
    public Rectangle getMenuRect()
    {
        return menuR;
    }
    
    public Rectangle getRestartRect()
    {
        return restartR;
    }
    
    public Rectangle getEndGameRect()
    {
        return endGameR;
    }
     
    public void changeActive(int x, boolean a)
    {
        active[x] = a;
    }
     
    public void menuClicked()
    {
        if(menuB)
            menuB = false;
        else
            menuB = true;
    }
    
    public boolean getMenuClicked()
    {
        return menuB;
    }
}
