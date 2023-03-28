package src;

public class Treatment {
    private Animal animalToTreat;
    private MedicalTask taskToPreform;
    private int startHour;

    public Treatment(Animal animal,MedicalTask task,int startHour){
        this.animalToTreat = animal;
        this.taskToPreform = task;
        this.startHour = startHour;
    }

    public Animal getAnimalToTreat() {
        return animalToTreat;
    }
    public void setAnimalToTreat(Animal animalToTreat) {
        this.animalToTreat = animalToTreat;
    }
    public MedicalTask getTaskToPreform() {
        return taskToPreform;
    }
    public void setTaskToPreform(MedicalTask taskToPreform) {
        this.taskToPreform = taskToPreform;
    }
    public int getStartHour() {
        return startHour;
    }
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
}
