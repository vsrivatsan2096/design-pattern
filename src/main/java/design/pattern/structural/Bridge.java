package design.pattern.structural;

import java.util.LinkedList;

/**
 * {@link Bridge} is used when we want to isolate the implementation {@link LinkedList}
 *          with the abstraction {@link FIFOCollection}
 * */
public interface Bridge {}

class FIFOCollection<T> implements Bridge {
    private final LinkedList<T> list;

    public FIFOCollection(LinkedList<T> list) {
        this.list = list;
    }

    public void offer(T data) {
        this.list.addLast(data);
    }

    public T poll() {
        return this.list.removeFirst();
    }

    public static void main(String[] args) {
        FIFOCollection<String> fifoCollection = new FIFOCollection<>(new LinkedList<>());

        fifoCollection.offer("A");
        fifoCollection.offer("B");
        fifoCollection.offer("C");
        fifoCollection.offer("D");

        for (int i = 0; i < 4; i ++) {
            System.out.println(fifoCollection.poll());
        }
    }
}
