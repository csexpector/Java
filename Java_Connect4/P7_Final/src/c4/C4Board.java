package c4;

/**
 * Represents the state of a Connect Four board on which multiple games can be played. The board consists of a
 * rectangular grid of squares in which playing pieces can be placed. Squares are identified by their zero-based row and
 * column numbers, where rows are numbered starting with the bottom row, and columns are numbered by starting from the
 * leftmost column.
 * 
 * Multiple games can be played on a single board. Whenever a new game begins, the board is empty. There are two
 * players, identified by the constants P1 (Player 1) and P2 (Player 2). P1 moves first in the first game, P2 moves
 * first in the second game, and so on in alternating fashion.
 * 
 * A C4Board also keeps track of the outcomes of the games that have been played on it. It tracks the number of wins
 * by P1, the number of wins by P2, and the number of ties. It does not track games that were abandoned before being
 * completed.
 */
public class C4Board
{
    //
    int rows;
    int cols;
    int currPlayer;
    int[][] board;
    int status;
    int winsP1;
    int winsP2;
    int nTies;
    int whoStart;
    /** The number used to indicate Player 1 */
    public static final int P1 = 1;

    /** The number used to indicate Player 2 */
    public static final int P2 = 2;
    
    /**
     * Creates a C4Board with the specified number of rows and columns. There should be no pieces on the board and it
     * should be player 1's turn to move.
     * 
     * However, if either rows or cols is less than four, throws an IllegalArgumentException.
     */
    public C4Board (int rows, int cols)
    {
        if (rows < 4 || cols < 4) throw new IllegalArgumentException("Rows and Cols must larger than or equal to 4");
        board = new int[rows][cols];
        this.rows = rows;
        this.cols = cols;
        currPlayer = P1;
        whoStart = P2;
        nTies = 0;
        status = 0;
        winsP1 = 0;
        winsP2 = 0;
    }

    /**
     * Sets up the board to play a new game, whether or not the current game is complete. All of the pieces should be
     * removed from the board. The player who had the second move in the previous game should have the first move in the
     * new game.
     */
    public void newGame ()
    {
        status = 0; // uncompleted  
        // new board with all unoccupied ties
        board = new int[rows][cols]; 
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                board[r][c] = 0;
        // select player
        currPlayer = whoStart;
        // who will start next game
        whoStart = whoStart==P1?P2:P1;
    }

    /**
     * Records a move, by the player whose turn it is to move, in the first open square in the specified column. If the
     * column is full, or if the game is over because a win or a tie has occurred, does nothing. If a non-existent
     * column is specified, throws an IllegalArgumentException.
     */
    public void moveTo (int col)
    {
        if (col >= cols || col < 0) throw new IllegalArgumentException("Col must less than " + cols);
        // check if column is full
        int sum = 0;
        for (int r = 0; r < rows; r++)
            sum += (board[r][col]!=0?1:0);
        if (sum == rows) return;
        
        // check if game win or tie
        if (status > 0) return;
        
        
        // everything is okay, make a move
        for (int r = rows - 1; r >= 0; r--)
        {
            if (board[r][col] == 0)
            {
                board[r][col] = currPlayer==P1?1:-1;
                break;
            }            
        }
        updateGameStatus();
        // change player alternatively
        if (currPlayer == P1) currPlayer = P2;
        else currPlayer = P1;           
    }
    /**
     * 
     * Update status of game, 0 is uncompleted, 1 is P1 win, 2 is P2 win, 3 is game tie
     */
    private void updateGameStatus()
    {
        
        status = checkWin();
        switch (status)
        {
            case 0:// uncompleted
                break;
            case 1: // P1 win
                winsP1++;
                break;
            case 2: // P2 win
                winsP2++;
                break;
            case 3: // Tie
                nTies++;
                break;
        }
    }
   
    private int checkDraw() {
        for (int j = 0; j < cols; j++) {
            if (board[0][j] == 0) {
                return 0;
            }
        }
        return 3;
    }
 
    private  int checkWin() {
        // Check Row 
        for (int r = rows - 1; r >= 0; r--) {
            int total = 0;
            for (int c = 0; c < cols - 3; c++) {
                total = board[r][c] + board[r][c + 1] + board[r][c + 2] + board[r][c + 3];
                if (total == 4) {
                    return 1;
                }
                if (total == -4) {
                    return 2;
                }
            }
        }
        // Check Column  
        for (int c = 0; c < cols - 1; c++) {
            int total = 0;
            for (int r = rows - 1 - 3; r >= 0; r--) {
                total = board[r][c] + board[r + 1][c] + board[r + 2][c] + board[r + 3][c];
                if (total == 4) {
                    return 1;
                }
                if (total == -4) {
                    return 2;
                }
            }
        }
        // Check Diagonal \
        /*
         * \\\
         *  \\\
         *   \\\
         *    \\\
         */
        for (int r = rows - 1 - 3; r >= 0; r--) {
            int total = 0;
            for (int c = 0; c <= cols - 1 - 3; c++) {
                total = board[r][c] + board[r + 1][c + 1] + board[r + 2][c + 2]
                        + board[r + 3][c + 3];
                if (total == 4) {
                    return 1;
                }
                if (total == -4) {
                    return 2;
                }
            }
        }
        /* Check Diagonal /
         * 
         *    ///
         *   ///
         *  ///
         * ///
         * 
         */
        for (int r = rows - 1 - 3; r >= 0; r--) {
            int total = 0;
            for (int c = 3; c < cols; c++) {
                total = board[r][c] + board[r + 1][c - 1] + board[r + 2][c - 2] + board[r + 3][c - 3];
                if (total == 4) {
                    return 1;
                }
                if (total == -4) {
                    return 2;
                }
            }
        }
        return checkDraw();
    }
    /**
     * Reports the occupant of the board square at the specified row and column. If the row or column doesn't exist,
     * throws an IllegalArgumentException. If the square is unoccupied, returns 0. Otherwise, returns P1 (if the square
     * is occupied by player 1) or P2 (if the square is occupied by player 2).
     */
    public int getOccupant (int row, int col)
    {
        if (row < 0 || col < 0 || row >= rows || col >= cols) throw new IllegalArgumentException("Row or Column invalid");
        switch (board[rows - 1 - row][col])
        {
            case 0: return 0;
            case 1: return P1;
            case -1: return P2;
            default: return 0;
        }
    }

    /**
     * Reports whose turn it is to move. Returns P1 (if it is player 1's turn to move), P2 (if it is player 2's turn to
     * move, or 0 (if it is neither player's turn to move because the current game is over because of a win or tie).
     */
    public int getToMove ()
    {
        if (status == 0)
            return currPlayer;
        else return 0;
    }

    /**
     * Reports how many games have been won by player 1 since this board was created.
     */
    public int getWinsForP1 ()
    {
        return winsP1;
    }

    /**
     * Reports how many games have been won by player 2 since this board was created.
     */
    public int getWinsForP2 ()
    {
        return winsP2;
    }

    /**
     * Reports how many ties have occurred since this board was created.
     */
    public int getTies ()
    {
        return nTies;
    }
}
