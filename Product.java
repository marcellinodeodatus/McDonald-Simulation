class Product {
    private String name;
    private Time createdTime;

    public Product(String name, Time createdTime) {
        this.name = name;
        this.createdTime = createdTime;
    }

    public String getName() {
        return name;
    }

    public Time getCreatedTime() {
        return createdTime;
    }
}
