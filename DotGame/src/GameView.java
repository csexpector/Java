import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out an instance of  <b>BoardView</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

    private static final long serialVersionUID = 1L;

    /**
     * Reference to the view of the board
     */
    private BoardView board;
    private GameModel gameModel;
 
    private JButton buttonUndo;
    private JButton buttonRedo;
  
    /**
     * Constructor used for initializing the Frame
     * 
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel model, GameController gameController) {
        super("Circle the Dot");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBackground(Color.WHITE);

        gameModel = model;
    	board = new BoardView(model, gameController);
    	add(board, BorderLayout.CENTER);

 
        JButton buttonReset = new JButton("Reset");
        buttonReset.setFocusPainted(false);
        buttonReset.addActionListener(gameController);

        JButton buttonExit = new JButton("Quit");
        buttonExit.setFocusPainted(false);
        buttonExit.addActionListener(gameController);
        
        buttonUndo = new JButton("Undo");
        buttonUndo.setFocusPainted(false);
        buttonUndo.addActionListener(gameController);
        buttonUndo.setEnabled(false);
        
        buttonRedo = new JButton("Redo");
        buttonRedo.setFocusPainted(false);
        buttonRedo.addActionListener(gameController);
        buttonRedo.setEnabled(false);
        
        JButton buttonSave = new JButton("Save");
        buttonSave.setFocusPainted(false);
        buttonSave.addActionListener(gameController);
        
        JButton buttonLoad = new JButton("Load");
        buttonLoad.setFocusPainted(false);
        buttonLoad.addActionListener(gameController);

    	JPanel control = new JPanel();
    	control.setBackground(Color.WHITE);
        control.add(buttonReset);
        control.add(buttonExit);
        control.add(buttonUndo);
        control.add(buttonRedo);
        control.add(buttonSave);
        control.add(buttonLoad);
        
    	add(control, BorderLayout.SOUTH);

    	pack();
    	setResizable(false);
    	setVisible(true);

    }

    public void setGameModel(GameModel model)
    {
        gameModel = model;
        board.setGameModel(gameModel);
    }

    public void update(){
        board.update();
  
    }

    public void enableUndoButton(boolean status)
    {
        buttonUndo.setEnabled(status);
    }
    public void enableRedoButon(boolean status)
    {
        buttonRedo.setEnabled(status);
    }
}
