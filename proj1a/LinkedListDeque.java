
public class LinkedListDeque<T> {
    /*
     * invariant:
     * 1. sentinel node's pre always point to last node;
     * 2. last node's next always point to sentinel node
     */
    private final Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<T>(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node<T>(item, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        sentinel.pre.next = new Node<T>(item, sentinel.pre, sentinel);
        sentinel.pre = sentinel.pre.next;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedItemValue = sentinel.next.getValue();
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removedItemValue;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removedItemValue = sentinel.pre.getValue();
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        size -= 1;
        return removedItemValue;
    }

    public T get(int index) {
        if (index < 0 && index >= size) {
            return null;
        }
        int currentIndex;
        Node<T> node;
        if (index < size / 2) {
            currentIndex = 0;
            node = sentinel.next;

            while (currentIndex < index) {
                node = node.next;
                currentIndex += 1;
            }
        } else {
            currentIndex = size - 1;
            node = sentinel.pre;

            while (currentIndex > index) {
                node = node.pre;
                currentIndex -= 1;
            }
        }
        return node.getValue();
    }

    public T getRecursive(int index) {
        if (index < 0 && index >= size) {
            return null;
        }
        return getHelper(index).getValue();
    }

    public void printDeque() {
        int total = size;
        Node<T> ptr = sentinel.next;
        String ret = "";
        while (total > 0) {
            ret = ret + ptr.getValue() + " ";
            ptr = ptr.next;
            total -= 1;
        }
        System.out.println(ret.substring(0, ret.length() - 1));
    }
    /*
     * get index's Node
     */
    private Node<T> getHelper(int index) {
        if (index == 0) {
            return sentinel.next;
        } else {
            return getHelper(index - 1).next;
        }
    }
}
