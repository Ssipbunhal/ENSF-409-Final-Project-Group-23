package src.Tasks;


public class ScheduledTask implements Comparable<ScheduledTask> {

    private int quantity;
    private int timeSpent;
    private String id;
    private String animalNames;
    private String normalTaskDescription;



    /**
     * constructor which takes 3 strings and 2 ints.
     * @param id id of the task
     * @param taskDescription the description of the task.
     * @param quantity the quantity of the task - the number of animals.
     * @param timeSpent the time needed for the task.
     * @param animalNames names of the animals in the task
     */
    public ScheduledTask(String id,String taskDescription, int quantity, int timeSpent, String animalNames) {
        this.normalTaskDescription = taskDescription;
        this.quantity = quantity;
        this.timeSpent = timeSpent;
        this.id = id;
        this.animalNames = animalNames;
    }

    /**
     * constructor which takes in a medical task and a name
     * @param task
     * @param name
     */
    public ScheduledTask(MedicalTask task, String name) {
        this.normalTaskDescription = task.getNormalTaskDescription();
        this.quantity = 0;
        this.timeSpent = task.getTimeSpent();
        this.id = task.getId();
        this.animalNames = task.getAnimalNames();
        this.animalNames = name;
    }

    /**
     * returns a unformatted task description
     * @return String the task description
     */
    public String getNormalTaskDescription() {
        return normalTaskDescription;
    }
    /**
     * returns a comma separated string  of the animals in the task
     * @return String the names of the animals in the task.
     */
    public String getAnimalNames(){
        return animalNames;
    }

    /**
     * sets the name of the animals in the task
     * @param newNames 
     */
    public void setAnimalNames(String newNames){
        this.animalNames =  newNames;
    }

    /**
     * retuns the task id
     * @return String task id
     */
    public String getId() {
        return id;
    }

    /**
     * formatts the task description
     * @return string formatted description
     */
    public String getFormattedTaskDescription() {
        return String.format("* %s (%s%s)", this.normalTaskDescription,
                            this.quantity  == 0 ? "" : String.valueOf(quantity)+": ",
                            this.animalNames); 
    }

    /**
     * the number of animals in the taks.
     * @return int the number of animals.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * set the number of animals in the task
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * returns the time needed for the taks.
     * @return int time needed to complete the task
     */
    public int getTimeSpent() {
        return timeSpent;
    }

    /**
     * setthe time needed for the taks.
     * @param timeSpent
     */
    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    /**
     * string if a back up is required
     * @return String formatted string if a back up is required.
     */
    public String backupReqMessage(){
        return timeSpent > 60 ? "* Backup volunteer required" : "";
    }
    

    /**
     * allows the java classes, in this case the list to compare objects.
     */
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

     /**
     * allows the java classes, in this case the list to compare objects.
     */
    @Override
    public int compareTo(ScheduledTask o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return -1;
        FeedingTask feedingTask = (FeedingTask) o;
        return this.id.equals(feedingTask.getId()) ? 0 : -1;
    }
}
