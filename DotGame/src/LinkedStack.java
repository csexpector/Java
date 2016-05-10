
public class LinkedStack<E> implements Stack<E>{

    private static class Elem<T>{
        private T info;
        private Elem<T> next;
        private Elem( T info, Elem<T> next) {
            this.info = info;
            this.next = next;
        }
    }

    private Elem<E> top; // instance variable

    public LinkedStack() {
        top = null;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push( E info ) {
        if (info == null) throw new NullPointerException("Error: info is null!");
        top = new Elem<>( info, top );
    }

    @Override
    public E peek(){
        if (top == null) throw new EmptyStackException();
        return top.info;
    }

    @Override
    public E pop() {

        if (top == null) throw new EmptyStackException();
        
        E savedInfo = top.info;

        Elem<E> oldTop = top;
        Elem<E> newTop = top.next;

        top = newTop;

        oldTop.info = null; // scrubbing the memory
        oldTop.next = null; // scrubbing the memory

        return savedInfo;
    }
}
