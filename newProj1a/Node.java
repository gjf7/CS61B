package newProj1a;

public class Node<T> {
    public Node<T> pre;
    public Node<T> next;
    public T value;
    public Node(T value, Node<T> pre, Node<T> next) {
        this.value = value;
        this.pre = pre;
        this.next = next;
    }
    public T getValue() {
        return value;
    }
}
