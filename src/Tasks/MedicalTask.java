/**
 * This class extends ScheduledTask, which is used in the schedule.
 * This class represents a medical task in the schedule.
 * @author Erik Öberg
 */
package src.Tasks;

public class MedicalTask  extends ScheduledTask {
    private Integer maxWindow;


    public MedicalTask(String taskId,String description, int duration, Integer maxWindow, String name) {
        super(taskId,description, 0, duration,name);
        this.maxWindow = maxWindow;
    }


    /**
     * 
     * @return Integer the max window of a medical task. 
     */
    public Integer getMaxWindow() {
        return maxWindow;
    }

    /**
     * sets the max window
     * @param maxWindow
     */
    public void setMaxWindow(Integer maxWindow) {
        this.maxWindow = maxWindow;
    }
}

