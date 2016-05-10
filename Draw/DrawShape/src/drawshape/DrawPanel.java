
package drawshape;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
public class DrawPanel extends JPanel {
    int drawMode; // 0 - rect, 1 - circle
    final int shapeSize = 50;
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    public DrawPanel()
    {
        super();
        this.addMouseListener(new DrawPanel.MsListener());
        drawMode = 0;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int i = 0; i < shapes.size(); i++)
        {
            shapes.get(i).draw(g);
        }
    }
    class MsListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            switch (drawMode)
            {
                case 0:
                    Rectangle reg = new Rectangle(x, y, shapeSize, shapeSize / 2);
                    shapes.add(reg);
                    reg.draw(getGraphics());
                    break;
                case 1:
                    Circle cir = new Circle(x, y, shapeSize);
                    shapes.add(cir);
                    cir.draw(getGraphics());
                    break;
                case 2:
                    Triangle tri = new Triangle(x, y, shapeSize);
                    shapes.add(tri);
                    tri.draw(getGraphics());
                    break;
            }
            
        }
    }
}
