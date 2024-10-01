import java.util.Scanner;

public class Main {
    private static Time currentTime = new Time();  // Start at 11:00 AM
    private static CustomerQueue[] lines = { new CustomerQueue(), new CustomerQueue(), new CustomerQueue() };
    private static FoodStack[] foodStacks = { new FoodStack(), new FoodStack(), new FoodStack(), new FoodStack(), new FoodStack() };
    private static OrderHistory orderHistory = new OrderHistory();
    private static int currentStackIndex = 0;  // Track the stack where new food is placed

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to McDonald's!");
        while (running) {
            System.out.println("Please choose one of the following options:");
            System.out.println("1) Enqueue a customer.");
            System.out.println("2) Advance one minute in time.");
            System.out.println("3) Quit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    enqueueCustomer(sc);
                    break;
                case 2:
                    advanceTime();
                    break;
                case 3:
                    running = false;
                    printStats();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    private static void enqueueCustomer(Scanner sc) {
        System.out.print("What line does the customer want to be in (1, 2, or 3)? ");
        int lineNum = sc.nextInt();
        sc.nextLine();  // Consume newline

        if (lineNum < 1 || lineNum > 3) {
            System.out.println("Invalid line number.");
            return;
        }

        System.out.print("What is the first name of the customer? ");
        String firstName = sc.nextLine();

        System.out.print("What is the last name of the customer? ");
        String lastName = sc.nextLine();

        String fullName = firstName + " " + lastName;

        String order;
        if (orderHistory.hasOrderedBefore(fullName)) {
            String previousOrder = orderHistory.getPreviousOrder(fullName);
            System.out.printf("Would %s like a %s again? (yes/no) ", fullName, previousOrder);
            String response = sc.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                order = previousOrder;
            } else {
                System.out.print("What does the customer want to order? ");
                order = sc.nextLine();
                orderHistory.addOrder(fullName, order);
            }
        } else {
            System.out.print("What does the customer want to order? ");
            order = sc.nextLine();
            orderHistory.addOrder(fullName, order);
        }

        Customer customer = new Customer(firstName, lastName, order, new Time(currentTime));
        lines[lineNum - 1].enqueue(customer);
    }

    private static void advanceTime() {
        currentTime.advance();
        for (int i = 0; i < lines.length; i++) {
            serveCustomerInLine(i);
        }
    }

    private static void serveCustomerInLine(int lineIndex) {
        CustomerQueue line = lines[lineIndex];
        if (!line.isEmpty()) {
            Customer customer = line.peek();
            String order = customer.getOrder();

            // Check food stacks for the item
            boolean found = false;
            for (FoodStack stack : foodStacks) {
                if (!stack.isEmpty() && stack.peek().getName().equals(order)) {
                    Product food = stack.pop();
                    int foodAge = currentTime.getDifferenceInMinutes(food.getCreatedTime());
                    int waitTime = currentTime.getDifferenceInMinutes(customer.getArrivalTime());
                    System.out.printf("%s from line %d has been served %s. The %s was %d minutes old. %s waited for %d minutes.\n",
                            customer.getFullName(), lineIndex + 1, order, order, foodAge, customer.getFullName(), waitTime);
                    line.dequeue();  // Remove the customer from the line
                    found = true;
                    break;
                }
            }

            // If not found, chef needs to cook
            if (!found) {
                System.out.printf("%s's %s is being prepared. Please wait 3 minutes.\n", customer.getFullName(), order);
                currentStackIndex = (currentStackIndex + 1) % foodStacks.length;
                Product newFood = new Product(order, new Time(currentTime));  // Chef prepares food
                foodStacks[currentStackIndex].push(newFood);  // Add to the next stack
            }
        }
    }

    private static void printStats() {
        // For now, just a placeholder for average wait time and food age calculation.
        System.out.println("Thanks for choosing McDonald's!");
        // Calculate average wait time and average food age here if needed.
    }
}
