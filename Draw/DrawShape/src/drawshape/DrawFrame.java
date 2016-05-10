
package drawshape;

import javax.swing.*;
import java.awt.*;


public class DrawFrame extends JFrame
{
    
    public static void main(String[] args) {
            DrawFrame myFrame = new DrawFrame();
    }

    public DrawFrame(){
            super();
            frameSetup();
    }
	
    public void frameSetup(){
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(new BorderLayout());
            
            DrawPanel canvas = new DrawPanel();
            JPanel pnlButtons = new ButtonPanel(canvas);
            canvas.setBorder(BorderFactory.createTitledBorder(""));
            this.add(canvas, BorderLayout.CENTER);
            this.add(pnlButtons, BorderLayout.NORTH);
            this.setSize(450, 300);
            this.setVisible(true);			
    }
}
		


