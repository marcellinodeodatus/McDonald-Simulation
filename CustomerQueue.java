import java.util.LinkedList;
import java.util.Queue;

class CustomerQueue {
    private Queue<Customer> queue;

    public CustomerQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(Customer customer) {
        queue.add(customer);
    }

    public Customer dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public Customer peek() {
        return queue.peek();
    }
}
