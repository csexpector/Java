
public class EmptyStackException extends NullPointerException {
    public EmptyStackException()
    {
        super("Error: Stack is empty!");
    }
    public EmptyStackException(String msg)
    {
        super(msg);
    }
}
