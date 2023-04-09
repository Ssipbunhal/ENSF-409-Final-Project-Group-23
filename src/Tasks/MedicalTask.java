package src.Tasks;

public class MedicalTask  extends ScheduledTask {
    private Integer maxWindow;


    public MedicalTask(String taskId,String description, int duration, Integer maxWindow, String name) {
        super(taskId,description, 0, duration,name);
        this.maxWindow = maxWindow;
    }


    public Integer getMaxWindow() {
        return maxWindow;
    }

    public void setMaxWindow(Integer maxWindow) {
        this.maxWindow = maxWindow;
    }
}

