
package drawshape;

import java.awt.Graphics;

public class Shape {
    protected int xpos;
    protected int ypos;
    Shape(int x, int y)
    {
        xpos = x;
        ypos = y;
    }
    Shape()
    {
        xpos = 0;
        ypos = 0;
    }
    public void setPos(int x, int y)
    {
        xpos = x;
        ypos = y;
    }
    public int getX()
    {
        return xpos;
    }
    public int getY()
    {
        return ypos;
    }
    public void draw(Graphics g)
    {
        // do nothing
    }
}

class Rectangle extends Shape {
    int width;
    int height;
    Rectangle(int x, int y, int w, int h)
    {
        super(x, y);
        width = w;
        height = h;
    }
    public int getW()
    {
        return width;
    }
    public int getH()
    {
        return height;
    }
    public void draw(Graphics g)
    {
        g.drawRect(xpos, ypos, width, height);
    }
}
class Circle extends Shape {
    int radius;
    Circle(int x, int y, int r)
    {
        super(x, y);
        radius = r;
    }
    public int getR()
    {
        return radius;
    }
    public void draw(Graphics g)
    {
        g.drawOval(xpos - radius / 2, ypos - radius / 2, radius, radius);
    }
}
class Triangle extends Shape {
    int size;
    Triangle(int x, int y, int s)
    {
        super(x, y);
        size = s;
    }
    public int getSize()
    {
        return size;
    }
    public void draw(Graphics g)
    {
        g.drawLine(xpos, ypos, xpos + size/2, (int) (ypos + size * Math.sqrt(3)/2));
        g.drawLine(xpos + size/2, (int) (ypos + size * Math.sqrt(3)/2), xpos - size/2, (int) (ypos + size * Math.sqrt(3)/2));
        g.drawLine(xpos - size/2, (int) (ypos + size * Math.sqrt(3)/2), xpos, ypos);
    }
}
