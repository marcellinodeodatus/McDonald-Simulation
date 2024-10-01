class Time {
    private int hour;
    private int minute;

    // Standard constructor
    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    // Default constructor (start time is 11:00)
    public Time() {
        this(11, 0);
    }

    // Copy constructor to create a new Time object based on an existing Time object
    public Time(Time other) {
        this.hour = other.hour;
        this.minute = other.minute;
    }

    // Method to advance time by one minute
    public void advance() {
        minute++;
        if (minute >= 60) {
            minute = 0;
            hour++;
            if (hour >= 24) {
                hour = 0;
            }
        }
    }

    // Get difference in minutes between this time and another time
    public int getDifferenceInMinutes(Time other) {
        int thisMinutes = this.hour * 60 + this.minute;
        int otherMinutes = other.hour * 60 + other.minute;
        return thisMinutes - otherMinutes;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
}
