package src.Tasks;

public class MedicalTask  extends ScheduledTask {
    private int maxWindow;


    public MedicalTask(String taskId,String description, int duration, int maxWindow, String name) {
        super(taskId,description, 0, duration,name);
        this.maxWindow = maxWindow;
    }


    public int getMaxWindow() {
        return maxWindow;
    }

    public void setMaxWindow(int maxWindow) {
        this.maxWindow = maxWindow;
    }
}

