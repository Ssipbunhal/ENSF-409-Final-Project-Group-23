package src;

import java.time.LocalDateTime;


public class AnimalCare {
    private LocalDateTime feedTime;
    private int feedingInterval;
    private int foodPrepTime;
    private int timeToFeed;
    private int cageCleaningTime;


    public AnimalCare(LocalDateTime feedTime, int feedingInterval, int foodPrepTime, int timeToFeed, int cageCleaningTime) {
        this.feedTime = feedTime;
        this.feedingInterval = feedingInterval;
        this.foodPrepTime = foodPrepTime;
        this.timeToFeed = timeToFeed;
        this.cageCleaningTime = cageCleaningTime;
    }

    public int getTotalFeedingTime(int amountOfAnimals){
        var feedTime = timeToFeed;
        return foodPrepTime + (feedTime * amountOfAnimals);
    }

    public int getTotalCleaningTime(int amountOfAnimals){
        return cageCleaningTime * amountOfAnimals;
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
