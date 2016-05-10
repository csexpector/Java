package edu.carrollu.csc600.week3;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;


public class JavaGraphics extends JFrame{

	public static void main(String[] args) {
		JavaGraphics myFrame = new JavaGraphics();
	}
	
	public JavaGraphics(){
		super();
		frameSetup();
	}
	
	public void frameSetup(){
		//JFrame frame  = new JFrame("Try GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new GridLayout(1, 2));
		
		OpPanel pnlOp = new OpPanel();
		pnlOp.setBorder(BorderFactory.createTitledBorder("Operation Pane"));
		JPanel pnlGraphics = new ColorPanel(pnlOp);	
		pnlGraphics.setBorder(BorderFactory.createTitledBorder("Graphics Pane"));
		
		this.add(pnlGraphics);
		this.add(pnlOp);
		
		this.setSize(450, 300);
		this.setVisible(true);			
	}
}

class ColorPanel extends  JPanel{
	JButton btnChangeColor;
	DrawingPanel pnlDrawing;	
	public ColorPanel(OpPanel pnlOp){
		super();
		setup(pnlOp);
	}	
	private void setup(OpPanel pnlOp){
		setLayout(new BorderLayout());
		btnChangeColor = new JButton("Change Color");
		btnChangeColor.addActionListener(new ButtonListener());
		btnChangeColor.addActionListener(new ButtonTextListener());
		pnlDrawing = new DrawingPanel(pnlOp);	
		add(pnlDrawing, BorderLayout.CENTER);
		add(btnChangeColor, BorderLayout.SOUTH);
	}	
	class ButtonListener implements ActionListener{		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			pnlDrawing.repaint();
			btnChangeColor.setText("Changed");
		}
	}
	
	class ButtonTextListener implements ActionListener{		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//btnChangeColor.setText("Changed");
		}
	}
}

class DrawingPanel extends JPanel{
	 OpPanel pnlOp;
	
	public DrawingPanel( OpPanel pnlOp){
		super();
		this.addMouseListener(new MsListener());
		this.pnlOp = pnlOp;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g.create();		
		int green = (int) (Math.random() * 255);
		g2.setColor(new Color(200, green, 100));
		Ellipse2D.Double el = new Ellipse2D.Double(50, 50, 150, 100);
		g2.fill(el);		
		//g.fillOval(50, 50, 100, 100);
	}
	
	class MsListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			pnlOp.txtOutput.append("\n" + e.getPoint());
			getGraphics().drawRect(e.getX(), e.getY(), 20, 20);
		}
	}
}

class OpPanel extends JPanel{	
	JTextField txtOperant1 = new JTextField(5);
	JTextField txtOperant2 = new JTextField(5);
	JTextArea txtOutput = new JTextArea(5, 10);
	JButton btnAdd = new JButton("Add");
	JButton btnMul = new JButton("Multiply");
	JPanel pnlOperants = new JPanel();
	JPanel pnlDisplay = new JPanel();
	JPanel pnlButtons = new JPanel();	
	public OpPanel(){ 
		super();
		setup(); 
	}	
	private void setup(){

		pnlOperants.add(txtOperant1);
		pnlOperants.add(txtOperant2);
		pnlDisplay.add(txtOutput);
		pnlButtons.add(btnAdd);
		pnlButtons.add(btnMul);
		this.setLayout(new BorderLayout());
		this.add(pnlOperants, BorderLayout.NORTH);
		this.add(pnlDisplay, BorderLayout.CENTER);
		this.add(pnlButtons, BorderLayout.SOUTH);
		btnAdd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtOutput.setText("" + (Double.parseDouble(txtOperant1.getText()) + Double.parseDouble(txtOperant2.getText())));
			}			
		});
		btnMul.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtOutput.setText("" + (Double.parseDouble(txtOperant1.getText()) * Double.parseDouble(txtOperant2.getText())));
			}			
		});
	}		
}


