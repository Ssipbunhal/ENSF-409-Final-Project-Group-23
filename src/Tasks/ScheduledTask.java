package src.Tasks;

import src.Employee;

public abstract class ScheduledTask implements Comparable<ScheduledTask> {

    private String taskDescription;
    private int quantity;
    private int timeSpent;
    private String id;

    public ScheduledTask(String id,String taskDescription, int quantity, int timeSpent) {
        this.taskDescription = taskDescription;
        this.quantity = quantity;
        this.timeSpent = timeSpent;
        this.id = id;
    }


    public String getId() {
        return id;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getTimeSpent() {
        return timeSpent;
    }
    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }
    public boolean isBackupRequired() {
        return timeSpent > 60;
    }
    public String backupReqMessage(){
        return timeSpent > 60 ? "* Backup volunteer required" : "";
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof String){
            var compid = (String) o ;
            return compid.equals(id);
        }
        if (o == null || getClass() != o.getClass()) return false;
        FeedingTask ftask = (FeedingTask) o;
        return id.equals(ftask.getId());
    }

    @Override
    public int compareTo(ScheduledTask o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return -1;
        FeedingTask feedingTask = (FeedingTask) o;
        return this.id.equals(feedingTask.getId()) ? 0 : -1;
    }
}
