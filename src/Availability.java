package src;

import java.time.LocalDate;
import java.time.LocalTime;

//Added Class not in UML
public class Availability {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Availability(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getStartTime() {

        return this.startTime;
    }

    public LocalTime getEndTime() {

        return this.endTime;
    }
}
