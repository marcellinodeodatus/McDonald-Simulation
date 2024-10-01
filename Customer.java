class Customer {
    private String firstName;
    private String lastName;
    private String order;
    private Time arrivalTime;

    public Customer(String firstName, String lastName, String order, Time arrivalTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.order = order;
        this.arrivalTime = arrivalTime;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getOrder() {
        return order;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }
}
