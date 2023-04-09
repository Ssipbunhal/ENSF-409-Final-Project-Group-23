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

    public void addAnimalToTask(String name){
        setQuantity(getQuantity() +1);
        setTimeSpent(feedingTime.getTotalFeedingTime(getQuantity()));
        this.setAnimalNames(this.getAnimalNames() + ", " + name);
    }
    
    public static String getInitialDesc(String name,String animalSpecies){
        return "Feeding - " + animalSpecies;
    }
}
