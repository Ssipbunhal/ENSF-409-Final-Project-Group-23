package src.Tasks;

import src.AnimalCare;

public class FeedingTask extends ScheduledTask{
    private String feedingId;
    private AnimalCare feedingTime;
    
    public FeedingTask(String feedingId, AnimalCare feedingTask) {
        super(feedingId,feedingId, 1, feedingTask.getTotalFeedingTime(1));
        this.feedingId = feedingId;
        this.feedingTime = feedingTask;
    }

    public String getFeedingId() {
        return feedingId;
    }

    public void addAnimalToTask(String name){
        setQuantity(getQuantity() +1);
        setTimeSpent(feedingTime.getTotalFeedingTime(getQuantity()));
        setTaskDescription(getTaskDescription()+", " + name);
    }
    
}
