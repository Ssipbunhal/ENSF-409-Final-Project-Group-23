/**
 * This class extends ScheduledTask, which is used in the schedule.
 * This class stores relevant data related to feeding 1 or more animals. 
 * Animals can be added to the task by using addAnimalToTask. 
 * @author Erik Öberg
 */
package src.Tasks;

import src.AnimalCare;

public class FeedingTask extends ScheduledTask{
    private String feedingId;
    private AnimalCare feedingTime;
    
    public FeedingTask(String feedingId, AnimalCare feedingTask, String desc, String name) {
        super(feedingId,desc , 1, feedingTask.getTotalFeedingTime(1),name);
        this.feedingId = feedingId;
        this.feedingTime = feedingTask;
    }

    public String getFeedingId() {
        return feedingId;
    }

    /**
     * Increments the task with a new animal.
     * @param name name of the animal
     */
    public void addAnimalToTask(String name){
        setQuantity(getQuantity() +1);
        setTimeSpent(feedingTime.getTotalFeedingTime(getQuantity()));
        this.setAnimalNames(this.getAnimalNames() + ", " + name);
    }
    
    /**
     * static helper method which has the a string concatenation.
     * @param name
     * @param animalSpecies
     * @return
     */
    public static String getInitialDesc(String name,String animalSpecies){
        return "Feeding - " + animalSpecies;
    }
}
