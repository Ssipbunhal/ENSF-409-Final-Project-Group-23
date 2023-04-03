

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.Test;

import src.Treatment;
import src.Animals.Animal;
import src.Animals.Beaver;
import src.Animals.Coyote;
import src.Animals.Fox;
import src.Animals.Porcupine;
import src.Animals.Raccoon;
import src.Exceptions.InvalidAnimalTypeException;
import src.Schedules.Schedule;

public class Test_Schedule {
    
    
    /*
     * The schedule needs to know if the schedule requires a 
     * backup. This will test this method by passing 
     * 18 animals to the schedule. The first hour (19) will be filled
     * but not the second hour (20)
     */
    @Test
    public void validateScheduleMethodForBackup() {
        var animals = new ArrayList<Animal>();
        try {
            
        for(int i = 0; i < 18; i++){
            var coyote = new Coyote("test " + i, "nickname ", "coyote", false);
            animals.add(coyote);
        }
        var schedule = new Schedule();
        schedule.createSchedule(animals, new ArrayList<Treatment>());
        
        
        assertEquals("The feeding tasks filled the entire hour; 19", schedule.scheduleFullOnHour(19),false);
        assertEquals("The feeding tasks should not fille the entire hour; 20", schedule.scheduleFullOnHour(20),true);
    } catch (InvalidAnimalTypeException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }


    /*
     * The time to feed animal should be dynamic and change 
     * the time of feeding if the hour is already filled up
     * with other tasks. 
     */
    @Test
    public void newTimesForFeedingTask(){
        var animals = new ArrayList<Animal>();
        try {
            
        for(int i = 0; i < 20; i++){
            var coyote = new Coyote("test " + i, "nickname ", "coyote", false);
            animals.add(coyote);
        }
        var schedule = new Schedule();
        schedule.createSchedule(animals, new ArrayList<Treatment>());

        assertNotEquals("The schedule did not reassign animals to a later timeslot",
            schedule.getSchedule().size(), 1);
    } catch (InvalidAnimalTypeException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }

    /*
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_CoyoteThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Coyote("Test", "coyote test", "coyoteWRONG",false));
    }

    /*
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_PorcupinesThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Porcupine("Test", "coyote test", "porcupineWRONG",false));
    }

        /*
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_RaccoonThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Raccoon("Test", "Raccoon test", "RaccoonWRONG",false));
    }

    /*
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_FoxThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Fox("Test", "fox test", "foxWRONG",false));
    }

        /*
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_BeaverThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Beaver("Test", "beaver test", "beaverWRONG",false));
    }



    /*
     * Nocturnal animals are fed in a 3-hour window starting at midnight (0). 
     * That is, feeding is scheduled for 12 AM, 1 AM, or 2 AM.
     * This tests: foxes and raccoons 
     */
    @Test
    public void nocturnalFeedingSchedule() {
        try {
            var coyote = new Fox("Test", "coyote test", "fox",false);
            var scheduleTaskList = new ArrayList<Animal>();
            scheduleTaskList.add(coyote);
            var schedule = new Schedule();
            var result = schedule.createSchedule(scheduleTaskList, new ArrayList<Treatment>());    
            assertEquals("The feeding window for coyotes are between 19-21."+
            " With one object in the list, the return value should be 19.",result.containsKey(0),true);
        } catch (Exception e) {
            
        }
    }

    /*
     * Diurnal animals are fed in a 3-hour window starting at 8 AM (8).
     * This tests: beavers
     */
    @Test
    public void diurnalFeedingSchedule() {
        try {
            var coyote = new Beaver("Test", "beaver test", "beaver",false);
            var scheduleTaskList = new ArrayList<Animal>();
            scheduleTaskList.add(coyote);
            var schedule = new Schedule();
            var result = schedule.createSchedule(scheduleTaskList, new ArrayList<Treatment>());    
            assertEquals("The feeding window for coyotes are between 19-21."+
            " With one object in the list, the return value should be 19.",result.containsKey(8),true);
        } catch (Exception e) {
            
        }
    }

    /*
     * Crepuscular animals are fed in a 3-hour 
     * window starting at 7 PM (19) - 9 PM (21).
     * This tests: coyote and Porcupines
     */
    @Test
    public void crepuscularFeedingSchedule() {
        try {
            var coyote = new Coyote("Test", "coyote test", "coyote",false);
            var scheduleTaskList = new ArrayList<Animal>();
            scheduleTaskList.add(coyote);
            var schedule = new Schedule();
            var result = schedule.createSchedule(scheduleTaskList, new ArrayList<Treatment>());    
            assertEquals("The feeding window for coyotes are between 19-21."+
            " With one object in the list, the return value should be 19.",result.containsKey(19),true);
        } catch (Exception e) {
            
        }
    }
    


}
