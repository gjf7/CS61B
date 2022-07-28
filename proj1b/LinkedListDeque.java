public class LinkedListDeque<T> implements Deque<T> {
    /*
    * invariant:
    *       1.sentinel's prev always point to last node
    *       2.last node's next always point to sentinel
    */
    private final Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<T>(null, null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next; // circular magic!
        size += 1;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int total = size;
        Node<T> ptr = sentinel.next;
        String ret = "";
        while (total > 0) {
            ret = ret + ptr.value + " ";
            total -= 1;
            ptr = ptr.next;
        }
        System.out.println(ret.substring(0, ret.length() - 1));
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.next.value;
        sentinel.next.prev = null;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return value;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.prev.value;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return value;
    }

    @Override
    public T get(int index) {
       if (index > size - 1 || index < 0) {
           return null;
       }
       if (index > size / 2) {
           // from last to front
           int curIdx = size - 1;
           Node<T> ptr = sentinel.prev;
           while (curIdx >= index) {
               if (curIdx == index) {
                   return ptr.value;
               }
               curIdx -= 1;
               ptr = ptr.prev;
           }
       } else {
           // from front to last
           int curIdx = 0;
           Node<T> ptr = sentinel.next;
           while (curIdx <= index) {
               if (curIdx == index) {
                   return ptr.value;
               }
               curIdx += 1;
               ptr = ptr.next;
           }
       }
        return null;
    }

    private T getElementHelper(Node<T> s, int index, int cur, boolean reverse) {
        if (index == cur) {
           return s.value;
        }
        if (reverse) {
            return getElementHelper(s.prev, index, cur - 1, true);
        } else {
            return getElementHelper(s.next, index, cur + 1, false);
        }

    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        boolean reverse = index > size / 2;
        return reverse ? getElementHelper(sentinel.prev, index, size - 1, true)
                : getElementHelper(sentinel.next, index, 0, false);
    }
}
