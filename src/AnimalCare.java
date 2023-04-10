/**
 * This class represents the feeding task which an animal 
 * type is required to receive. This includes cleaning the cage and 
 * feeding.
 * @author Erik Öberg
 */
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

    /**
     * gets the total feeding time of the animal. Which is the time it takes
     * to feed each animal * the number of animal +  the time it takes to 
     * prepare the food.
     * @param amountOfAnimals
     * @return int the total minutes it takes to feed X number of animals
     */
    public int getTotalFeedingTime(int amountOfAnimals){
        var feedTime = timeToFeed;
        return foodPrepTime + (feedTime * amountOfAnimals);
    }

    /**
     * the method calculates the total time needed to clean for a number of animals.
     * @param amountOfAnimals
     * @return int the amount of time it takes to clean x amount of animals
     */
    public int getTotalCleaningTime(int amountOfAnimals){
        return cageCleaningTime * amountOfAnimals;
    }

    /**
     * The method calculates the minutes needed 
     *  to prepare the food for the animal
     * @return int how long time it takes to prepare the food for the animal
     */
    public int getFoodPrepTime() {
        return foodPrepTime;
    }

    /**
     * How long time it takes to feed an animal, in minutes.
     * @return the time needed to feed each animal
     */
    public int getTimeToFeed() {
        return timeToFeed;
    }

    /**
     * How long time it takes to clean an animals cage, in minutes.
     * @return the time needed to clean a cage of an animal
     */
    public int getCageCleaningTime() {
        return cageCleaningTime;
    }

    /**
     * The method return the local date time of when
     * an animal should be feed.
     * @return LocalDateTime when an animal is expected to be fed.
     */
    public LocalDateTime getFeedtime() {

        return this.feedTime;
    }

    /**
     * In what time window an animal needs to be fed after its feed time.
     * @return int in what interval an animal needs to be feed after the getFeedtime
     * @see getFeedtime()
     */
    public int getFeedingInterval() {
        return feedingInterval;
    }

}
