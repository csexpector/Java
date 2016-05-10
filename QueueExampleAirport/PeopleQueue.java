/*
This class implement a queue by using an array
There are two pointers in queue: back and front to point to 2 endings of the queue
Everytime we enqueu or dequeue, these pointers will be increased by 1
To avoid index out of bound we use 'wraparound' technique.
That means whenever back (or front) point to the end of the array (defined by capacity)
the increment will put this pointer go back to 0
Here is an example:
the array has size of 25
after some enqueue, dequeue, now the pointer front is at 6, back is at 24 (end of array)
If at this moment, we call enqueue, back will be increased by 1 to be 25 but that will
be the error index out of bound, therefore, back will be set to 0
The same happens with front.
*/
import java.lang.Exception;
public class PeopleQueue
{
    private String [ ] people;
    private int        length;
    private int        front;
    private int        back;
    private int        capacity;
    private final int CAPACITY = 20;
    /**
     * Default constructor of the queue.
     */
    public PeopleQueue( )
    {
        // create a new array
        people = new String [CAPACITY];
        // make queue empty
        makeEmpty();
        // set queue capacity
        capacity = CAPACITY;
    }

    /**
     * Constructor of the queue.
     */
    public PeopleQueue( int capacity )
    {
        // TODO1: write commands to construct new queue
        // Take default constructor as an example
        
    }

    /**
     * Test if the queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return length == 0;
    }

    /**
     * Test if the queue is logically full.
     * @return true if full, false otherwise.
     */
    public boolean isFull( )
    {
        return length == people.length;
    }

    /**
     * Make the queue logically empty.
     */
    public void makeEmpty( )
    {
        length = 0;
        front = 0;
        back = -1;
    }

    /**
     * Get the least recently inserted item in the queue.
     * Does not alter the queue.
     * @return the least recently inserted item in the queue, or null, if empty.
     */
    public String getFront( )
    {
        if( isEmpty( ) )
            return null;
        return people[ front ];
    }

    /**
     * Return and remove the least recently inserted item from the queue.
     * @return the least recently inserted item in the queue, or null, if empty.
     */
    public String dequeue( )
    {
        if( isEmpty( ) )
            return null;
        length--;

        String frontItem = people[ front ];
        people[ front ] = null;
        // increase the front pointer
        front = increment( front );
        return frontItem;
    }

    /**
     * Insert a new item into the queue.
     * @param x the item to insert.
     * @exception Overflow if queue is full.
     */
    public void enqueue( String x ) throws Exception
    {
        if( isFull( ) )
            throw new Exception("ERROR: Queue is full");

        // TODO2: use method increment to Increase the back pointer
        // Hint: See dequeue 
        
        

        people[ back ] = x;
        length++;
    }

    /**
     * Internal method to increment with wraparound.
     * @param x any index in theArray's range.
     * @return x+1, or 0, if x is at the end of theArray.
     */
    private int increment( int x )
    {
        if( ++x == people.length )
            x = 0;
        return x;
    }
    /**
     * Print the content of the queue
     */
    public String toString()
    {
        String msg = "Queue (" + length + " people): ";

        for (int i = 0; i < length; i++)
        {
            msg += (people[(i+front)%capacity] + " ");
        }

        return msg;
    }
}



// ANSWERS

/* TODO1
        people = new String[ capacity ];
        makeEmpty();
        this.capacity = capacity;
/*

// TODO2: back = increment( back );