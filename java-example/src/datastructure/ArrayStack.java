package datastructure;

/**
 * Created by 1002703 on 2017. 1. 24..
 */
public class ArrayStack<T> {
    // T로 선언하지 않고 Object로 선언함. ArrayList 구현도 마찬가지.
    private Object[] elements;
    private int top;

    public ArrayStack(int size) {
        elements = new Object[size];
    }

    public void push(T element) {
        elements[top++] = element;
    }

    public T pop() {
        return (T) elements[--top];
    }
}
