import java.util.Stack;

class FoodStack {
    private Stack<Product> stack;

    public FoodStack() {
        stack = new Stack<>();
        // Initialize each stack with a BigMac created at 11:00
        stack.push(new Product("BigMac", new Time(11, 0)));
    }

    public Product pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    public void push(Product product) {
        stack.push(product);
    }

    public Product peek() {
        return stack.isEmpty() ? null : stack.peek();
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
