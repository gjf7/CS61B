public class ArrayDeque<T> {
    /* array to save data */
    private  T[] array;

    /* size of deque */
    private int size;

    /* size of array */
    private int length;

    /* front index */
    private int front;

    /* last element next index */
    private int last;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4;
        last = 4;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * return "index - 1"
     * when index == 0, return end index of array
     */
    private int minusOne(int index) {
        if (index == 0) {
            return length - 1;
        }

        return index - 1;
    }

    private int plusOne(int index, int module) {
        index %= module;
        if (index == module - 1) {
            return 0;
        }

        return index + 1;
    }
    private void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = front;
        int ptr2 = length;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 += 1;
        }
        front = length;
        last = ptr2;
        length = length * 2;
        array = newArray;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = front;
        int ptr2 = length / 4;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 += 1;
        }
        front = length / 4;
        last = ptr2;
        length /= 2;
        array = newArray;
    }

    public void addFirst(T item) {
        if (size == length - 1) {
            grow();
        }
        front = minusOne(front);
        array[front] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == length - 1) {
            grow();
        }
        array[last] = item;
        last = plusOne(last, length);
        size += 1;
    }

    public T removeFirst() {
        // make sure array length always more than 8
        if (length >= 16 && length > 4 * size) {
            shrink();
        }

        if (size == 0) {
            return null;
        }

        T val = array[front];
        array[front] = null;
        front = plusOne(front, length);
        size -= 1;
        return val;
    }

    public T removeLast() {
        if (length >= 16 && length > 4 * size) {
            shrink();
        }

        if (size == 0) {
            return null;
        }

        last = minusOne(last);
        T val = array[last];
        array[last] = null;
        size -= 1;
        return val;
    }

    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }
        int actualIdx = (front + index) % length;
        return array[actualIdx];
    }

    public void printDeque() {
        int ptr = front;
        while (ptr != last) {
            System.out.print(array[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
    }
}