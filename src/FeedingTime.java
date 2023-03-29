package src;

import java.time.LocalDateTime;


public class FeedingTime {
    private LocalDateTime feedTime;
    private int feedingInterval;
    private int foodPrepTime;
    private int timeToFeed;
    private int cageCleaningTime;


    public FeedingTime(LocalDateTime feedTime, int feedingInterval, int foodPrepTime, int timeToFeed, int cageCleaningTime) {
        this.feedTime = feedTime;
        this.feedingInterval = feedingInterval;
        this.foodPrepTime = foodPrepTime;
        this.timeToFeed = timeToFeed;
        this.cageCleaningTime = cageCleaningTime;
    }

    public int getTotalTime(){
        return foodPrepTime + timeToFeed + cageCleaningTime;
    }

    public int getFoodPrepTime() {
        return foodPrepTime;
    }

    public int getTimeToFeed() {
        return timeToFeed;
    }

    public int getCageCleaningTime() {
        return cageCleaningTime;
    }

    public LocalDateTime getFeedtime() {

        return this.feedTime;
    }

    public int getFeedingInterval() {
        return feedingInterval;
    }

}
