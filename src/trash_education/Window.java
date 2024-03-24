package trash_education;

import javax.swing.*;

public class Window extends JFrame {
    private MyPanel myPanel;
    
    public Window()
    {
 	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle(Parameters.Title);
        myPanel = new MyPanel();
        this.add(myPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        myPanel.startThread();
    }
}
