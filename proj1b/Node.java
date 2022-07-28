public class Node<T> {
    public T value;
    public Node<T> next;

    public Node<T> prev;

    public Node(T value, Node<T> prev, Node<T> next) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }
}
