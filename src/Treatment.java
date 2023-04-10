/**
 * This class is connecting class between the animal
 * and the treatment it needs to receive. 
 * @author Erik Öberg
 */
package src;

import src.Animals.Animal;
import src.Tasks.MedicalTask;

public class Treatment {
    private Animal animalToTreat;
    private MedicalTask taskToPreform;
    private int startHour;

    public Treatment(Animal animal,MedicalTask task,int startHour){
        this.animalToTreat = animal;
        this.taskToPreform = task;
        this.startHour = startHour;
    }
    
    /**
     * the animal in associated with a treatment
     * @return Animal the animal in the medical task
     */
    public Animal getAnimalToTreat() {
        return animalToTreat;
    }

    /**
     * sets the animal to treat 
     * @param animalToTreat
     */
    public void setAnimalToTreat(Animal animalToTreat) {
        this.animalToTreat = animalToTreat;
    }

    /**
     * the medical task associated with the treatment 
     * @return MedicalTask the medical task to be preformed 
     */
    public MedicalTask getTaskToPreform() {
        return taskToPreform;
    }
    /**
     * sets the medical task
     * @param taskToPreform
     */
    public void setTaskToPreform(MedicalTask taskToPreform) {
        this.taskToPreform = taskToPreform;
    }

    /**
     * at what hour a treatment should be started.
     * @return int when a treatment should be started
     */
    public int getStartHour() {
        return startHour;
    }
    /**
     * sets when a treatment should be started.
     * @param startHour
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * string concatenation of 'MedicalTask -'' and  the id of the task 
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "MedicalTask - " +this.taskToPreform.getId();
    }
}
