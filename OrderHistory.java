import java.util.HashMap;
import java.util.Map;

class OrderHistory {
    private Map<String, String> history;

    public OrderHistory() {
        history = new HashMap<>();
    }

    public boolean hasOrderedBefore(String fullName) {
        return history.containsKey(fullName);
    }

    public String getPreviousOrder(String fullName) {
        return history.get(fullName);
    }

    public void addOrder(String fullName, String order) {
        history.put(fullName, order);
    }
}
