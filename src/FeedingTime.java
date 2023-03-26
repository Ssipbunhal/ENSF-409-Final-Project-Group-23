package src;
import java.time.LocalDate;

public class FeedingTime {
    private LocalDate feedTime;

    public FeedingTime(LocalDate feedTime) {
        this.feedTime = feedTime;
        
    }
    public LocalDate getFeedtime() {

        return this.feedTime;
    }

}
