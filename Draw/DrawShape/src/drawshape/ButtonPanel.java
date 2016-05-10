/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawshape;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ButtonPanel extends JPanel implements ActionListener {
    JButton btnRect, btnCirc, btnTriag;
    DrawPanel canvas;
    public ButtonPanel(DrawPanel p)
    {
        super();
        setup();
        canvas = p;
    }
    public void setup()
    {
        btnRect = new JButton("Rectangle");
        btnCirc = new JButton("Circle");
        btnTriag = new JButton("Triangle");
        add(btnRect, BorderLayout.CENTER);
        add(btnCirc, BorderLayout.CENTER);
        add(btnTriag, BorderLayout.CENTER);
        btnRect.addActionListener(this);
        btnCirc.addActionListener(this);
        btnTriag.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Rectangle":
                canvas.drawMode = 0;
                break;
            case "Circle":
                canvas.drawMode = 1;
                break;
            case "Triangle":
                canvas.drawMode = 2;
                break;
        }
    }
}
