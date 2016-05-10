import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It implements 
 * the interface ActionListener to be called back when the player makes a move. It computes
 * the next step of the game, and then updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    /**
     * Reference to the view of the game
     */
    private GameView gameView;

    /**
     * Reference to the model of the game
     */
    private GameModel gameModel;
 
    /**
     * Reference to the stack of saved models of the game
     */
    private LinkedStack<GameModel> savedModels = new LinkedStack<>();
    /**
     * Reference to the stack of redo models
     */
    private LinkedStack<GameModel> redoModels = new LinkedStack<>();
    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param size
     *            the size of the board on which the game will be played
     */
    public GameController(int size) {
        File f = new File("savedGame.ser");
        if (f.exists())
        {
            gameModel = new GameModel(size);
            gameView = new GameView(gameModel, this);
            load();
        }
        else
        {
            gameModel = new GameModel(size);
            gameView = new GameView(gameModel, this);
            gameView.update();
        }
    }


 
    /**
     * resets the game
     */
    public void reset(){
        gameModel.reset();
        gameView.update();
        savedModels = new LinkedStack<>();
        redoModels = new LinkedStack<>();
        gameView.enableRedoButon(false);
        gameView.enableUndoButton(false);
    }
    
    /**
     * undo the game
     */
    public void undo() {
        try {
            // put current model to redo list
            redoModels.push((GameModel) gameModel.clone());
            // get model from saved list
            gameModel = savedModels.pop();
            gameView.setGameModel(gameModel);
            // update view with new model
            gameView.update();
            if (savedModels.isEmpty())
                gameView.enableUndoButton(false);
            gameView.enableRedoButon(true);
            
        } catch (EmptyStackException ex)
        {
            System.out.println(ex.getMessage());
            gameView.enableUndoButton(false);
        }
    }
    /**
     * Redo the game
     * 
     */
    private void redo()
    {
        try 
        {
            savedModels.push((GameModel) gameModel.clone());
            gameModel = redoModels.pop();
            gameView.setGameModel(gameModel);
            gameView.update();
            gameView.enableUndoButton(true);
            
            if (redoModels.isEmpty()) gameView.enableRedoButon(false);
            
        } catch (EmptyStackException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Save game
     */
    private void save()
    {
        try {
            FileOutputStream fileOut = new FileOutputStream("savedGame.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameModel);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException fex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, fex);
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    /**
     * Load game
     */
    private void load()
    {
        try {
            FileInputStream fileIn = new FileInputStream("savedGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gameModel = (GameModel) in.readObject();
            gameView.setGameModel(gameModel);
            // update view with new model
            gameView.update();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Callback used when the user clicks a button or one of the dots. 
     * Implements the logic of the game
     *
     * @param e
     *            the ActionEvent    /**
     * Callback used when the user clicks a button or one of the dots. 
     * Implements the logic of the game
     *
     * @param e
     *            the ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() instanceof DotButton) 
        {
            DotButton clicked = (DotButton)(e.getSource());

            if (gameModel.getCurrentStatus(clicked.getColumn(),clicked.getRow()) ==  GameModel.AVAILABLE)
            {
                // save current model
                savedModels.push((GameModel) gameModel.clone());
                // enable undo
                gameView.enableUndoButton(true);
                // do it
                gameModel.select(clicked.getColumn(),clicked.getRow());
                oneStep();
            }
        } else if (e.getSource() instanceof JButton) {
            JButton clicked = (JButton)(e.getSource());

            if (clicked.getText().equals("Quit")) {
                 System.exit(0);
            } else if (clicked.getText().equals("Reset")){
                reset();
            } else if (clicked.getText().equals("Undo")) {
                undo();
            } else if (clicked.getText().equals("Redo")) {
                redo();
            } else if (clicked.getText().equals("Save")) {
                save();
                JOptionPane.showMessageDialog(null, "Game saved!");
            } else if (clicked.getText().equals("Load")) {
                load();
                JOptionPane.showMessageDialog(null, "Game loaded!");
            }
        } 
    }

    /**
     * Computes the next step of the game. If the player has lost, it 
     * shows a dialog offering to replay.
     * If the user has won, it shows a dialog showing the number of 
     * steps that had been required in order to win. 
     * Else, it finds one of the shortest path for the blue dot to 
     * exit the board and moves it one step in that direction.
     */
    private void oneStep(){
        Point currentDot = gameModel.getCurrentDot();
        if(isOnBorder(currentDot)) {
            gameModel.setCurrentDot(-1,-1);
            gameView.update();
 
            Object[] options = {"Play Again",
                    "Quit"};
            int n = JOptionPane.showOptionDialog(gameView,
                    "You lost! Would you like to play again?",
                    "Lost",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(n == 0){
                reset();
            } else{
                System.exit(0);
            }
        }
        else{
            Point direction = findDirection();
            if(direction.getX() == -1){
                gameView.update();
                Object[] options = {"Play Again",
                        "Quit"};
                int n = JOptionPane.showOptionDialog(gameView,
                        "Congratualtions, you won in " + gameModel.getNumberOfSteps() 
                            +" steps!\n Would you like to play again?",
                        "Won",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if(n == 0){
                    reset();
                } else{
                    System.exit(0);
                }
            }
            else{
                gameModel.setCurrentDot(direction.getX(), direction.getY());
                gameView.update();
            }
        }
 
    }

    /**
     * Does a ``breadth-first'' search from the current location of the blue dot to find
     * one of the shortest available path to exit the board. 
     *
     * @return the location (as a Point) of the next step for the blue dot toward the exit.
     * If the blue dot is encircled and cannot exit, returns an instance of the class Point 
     * at location (-1,-1)
     */

    private Point findDirection(){
        boolean[][] blocked = new boolean[gameModel.getSize()][gameModel.getSize()];

        for(int i = 0; i < gameModel.getSize(); i ++){
            for (int j = 0; j < gameModel.getSize(); j ++){
                blocked[i][j] = 
                    !(gameModel.getCurrentStatus(i,j) == GameModel.AVAILABLE);
            }
        }

        Queue<Pair<Point>> myQueue = new LinkedQueue<Pair<Point>>();
        
        LinkedList<Point> possibleNeighbours = new  LinkedList<Point>();

        // start with neighbours of the current dot
        // (note: we know the current dot isn't on the border)
        Point currentDot = gameModel.getCurrentDot();

        possibleNeighbours = findPossibleNeighbours(currentDot, blocked);

        // adding some non determinism into the game !
        java.util.Collections.shuffle(possibleNeighbours);

        for(int i = 0; i < possibleNeighbours.size() ; i++){
            Point p = possibleNeighbours.get(i);
            if(isOnBorder(p)){
                return p;                
            }
            myQueue.enqueue(new Pair<Point>(p,p));
            blocked[p.getX()][p.getY()] = true;
        }


        // start the search
        while(!myQueue.isEmpty()){
            Pair<Point> pointPair = myQueue.dequeue();
            possibleNeighbours = findPossibleNeighbours(pointPair.getFirst(), blocked);
             
            for(int i = 0; i < possibleNeighbours.size() ; i++){
                Point p = possibleNeighbours.get(i);
                if(isOnBorder(p)){
                    return pointPair.getSecond();                
                }
                myQueue.enqueue(new Pair<Point>(p,pointPair.getSecond()));
                blocked[p.getX()][p.getY()]=true;
            }

       }

        // could not find a way out. Return an outside direction
        return new Point(-1,-1);

    }

   /**
     * Helper method: checks if a point is on the border of the board
     *
     * @param p
     *            the point to check
     *
     * @return true iff p is on the border of the board
     */
     
    private boolean isOnBorder(Point p){
        return (p.getX() == 0 || p.getX() == gameModel.getSize() - 1 ||
                p.getY() == 0 || p.getY() == gameModel.getSize() - 1 );
    }

   /**
     * Helper method: find the list of direct neighbours of a point that are not
     * currenbtly blocked
     *
     * @param point
     *            the point to check
     * @param blocked
     *            a 2 dimentionnal array of booleans specifying the points that 
     *              are currently blocked
     *
     * @return an instance of a LinkedList class, holding a list of instances of 
     *      the class Points representing the neighbours of parameter point that 
     *      are not currently blocked.
     */
    private LinkedList<Point> findPossibleNeighbours(Point point, 
            boolean[][] blocked){

        LinkedList<Point> list = new LinkedList<Point>();
        int delta = (point.getY() %2 == 0) ? 1 : 0;
        if(!blocked[point.getX()-delta][point.getY()-1]){
            list.add(new Point(point.getX()-delta, point.getY()-1));
        }
        if(!blocked[point.getX()-delta+1][point.getY()-1]){
            list.add(new Point(point.getX()-delta+1, point.getY()-1));
        }
        if(!blocked[point.getX()-1][point.getY()]){
            list.add(new Point(point.getX()-1, point.getY()));
        }
        if(!blocked[point.getX()+1][point.getY()]){
            list.add(new Point(point.getX()+1, point.getY()));
        }
        if(!blocked[point.getX()-delta][point.getY()+1]){
            list.add(new Point(point.getX()-delta, point.getY()+1));
        }
        if(!blocked[point.getX()-delta+1][point.getY()+1]){
            list.add(new Point(point.getX()-delta+1, point.getY()+1));
        }
        return list;
    }


}
