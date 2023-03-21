package Tests;

import org.junit.Test;

public class Test_Schedule {
    
    
    /*
     * Usually only one volunteer is scheduled for each hour, 
     * but a backup volunteer can be called in if it is
     * impossible to fit all the tasks in otherwise.
     * If the medical task has a requirment for a backup 
     * and a backup is added to the schedule the list of 
     * 'volunteer' should have a size of 2. 
     */
    @Test
    public void validateScheduleHasBackup() {
        
    }

    /*
     * Validate that the list of volunteer is not empty.
     */
    @Test
    public void volunteerListIsNotEmpty() {
        
    }

    /*
     * Tests that it's impossible to add an invalid time
     * to the schedule. 
     */
    @Test
    public void validateTimeOfScheduleHasAValidTime() {
        
    }

    /*
     * Change the value of backup required
     */
    @Test
    public void changeBackupRequiredValue() {
        
    }

    /*
     * Usually only one volunteer is scheduled for each hour, 
     * but a backup volunteer can be called in if it is
     * impossible to fit all the tasks in otherwise
     * If the medical task has a requirment for a backup 
     * and a backup is added to the schedule the list of 
     * 'volunteer' should have a size of 2. If the backup required
     * changes the required number of volunteers should change. 
     */
    @Test
    public void changeBackupRequiredValue_validBackup() {
        
    }

    /*
     * Feed them within a 3-hour window, between 7 and 9 PM
     */
    @Test
    public void validateCoyoteFeedingWindow() {
        
    }

     /*
     *  orphaned kits which require bottle feeding every 2 hours
     */
    @Test
    public void validateKittenFeedingWindow() {
        
    }
    /*
     * validate porcupines feeding window 7 and 9 PM
     */
    @Test
    public void validatePorcupinesFeedingWindow() {
        
    }


    /*
     * Nocturnal animals are fed in a 3-hour window starting at midnight (0). 
     * That is, feeding is scheduled for 12 AM, 1 AM, or 2 AM.
     */
    @Test
    public void nocturnalFeedingSchedule() {
        
    }

    /*
     * Diurnal animals are fed in a 3-hour window starting at 8 AM (8).
     */
    @Test
    public void diurnalFeedingSchedule() {
        
    }

    /*
     * Crepuscular animals are fed in a 3-hour window starting at 7 PM (19).
     */
    @Test
    public void crepuscularFeedingSchedule() {
        
    }
    

    /*
     * Schedule can not violate the time window. If it does
     * a volenteer needs to be added to the schedule. 
     */
    @Test
    public void validateMaxWindowNotViolated() {
        
    }
}
