
public class EmptyQueueException extends NullPointerException{
    public EmptyQueueException()
    {
        super("Error: Queue is empty!");
    }
    public EmptyQueueException(String msg)
    {
        super(msg);
    }
}
