package src.Tasks;


public class ScheduledTask implements Comparable<ScheduledTask> {

    private int quantity;
    private int timeSpent;
    private String id;
    private String animalNames;
    private String normalTaskDescription;

    public String getNormalTaskDescription() {
        return normalTaskDescription;
    }

    public ScheduledTask(String id,String taskDescription, int quantity, int timeSpent, String animalNames) {
        this.normalTaskDescription = taskDescription;
        this.quantity = quantity;
        this.timeSpent = timeSpent;
        this.id = id;
        this.animalNames = animalNames;
    }

    public ScheduledTask(MedicalTask task, String name) {
        this.normalTaskDescription = task.getNormalTaskDescription();
        this.quantity = 0;
        this.timeSpent = task.getTimeSpent();
        this.id = task.getId();
        this.animalNames = task.getAnimalNames();
        this.animalNames = name;
    }

    public String getAnimalNames(){
        return animalNames;
    }
    public void setAnimalNames(String newNames){
        this.animalNames =  newNames;
    }

    public String getId() {
        return id;
    }

    public String getFormattedTaskDescription() {
        return String.format("* %s (%s%s)", this.normalTaskDescription,
                            this.quantity  == 0 ? "" : String.valueOf(quantity)+": ",
                            this.animalNames); 
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
