package src;
import java.util.ArrayList;

public class Schedule {

    private ArrayList<Volunteer> volunteer;
    private ArrayList<MedicalTask> medicalTasks;
    private boolean backupRequired;

    public Schedule() {
        this.volunteer = new ArrayList<Volunteer>();
        this.medicalTasks = new ArrayList<MedicalTask>();
        this.backupRequired = false;
    }

    public void addVolunteer(Volunteer volunteer) {

        this.volunteer.add(volunteer);
    }


    public void addMedicalTask(MedicalTask medicalTask) {

        this.medicalTasks.add(medicalTask);
    }

    public void setBackuprequired(boolean backupRequired) {

        this.backupRequired = backupRequired;
    }
}
