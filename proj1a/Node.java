public class Node<T> {
    public T value;
    public Node<T> next;

    public Node<T> pre;

    public Node(T value, Node<T> pre, Node<T> next) {
        this.value = value;
        this.next = next;
        this.pre = pre;
    }
    public T getValue() {
        return value;
    }
}
