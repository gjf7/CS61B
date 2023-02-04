/*
invariable:
    1. sentinel's prev always point to last item of deque;
    2. last item of deque always point to sentinel node;
*/
public class LinkedListDeque<T> {
    private final Node<T> sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node<T> node = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item) {
        Node<T> node = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<T> firstNode = sentinel.next;
        firstNode.next.prev = sentinel;
        sentinel.next = firstNode.next;

        firstNode.prev = null;
        firstNode.next = null;

        size -= 1;
        return firstNode.getItem();
    }

    public T removeLast() {
        if (isEmpty()) {
           return null;
        }

        Node<T> lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;

        lastNode.prev = null;
        lastNode.next = null;

        size -= 1;
        return lastNode.getItem();
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    public T get(int index) {
        if (!isValidIndex(index)) {
            return null;
        }

        Node<T> p = sentinel;

        while (index >= 0) {
            p = p.next;
            index -= 1;
        }

        return p.getItem();
    }

    private T getHelper(int currentIndex, Node<T> item, int index) {
        if (currentIndex == index) {
            return item.getItem();
        }
        return getHelper(currentIndex + 1, item.next, index);
    }

    public T getRecursive(int index) {
        if (!isValidIndex(index)) {
            return null;
        }

        return getHelper(0, sentinel.next, index);
    }

    public boolean isEmpty() {
       return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String result = "";
        Node<T> p = sentinel.next;
        int itemPrintNeededCount = size;

        while (itemPrintNeededCount > 0) {
           result = result +  p.getItem() + " ";
           p = p.next;
           itemPrintNeededCount -= 1;
        }
        System.out.println((result.substring(0, result.length() - 1)));
    }

}
