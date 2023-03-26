import org.junit.Test;
import src.Animal;
import src.Volunteer;

import static org.junit.Assert.assertEquals;

public class Test_Volunteer {
    
    @Test
    public void testFeedCoyote() {
        Animal coyote = new Animal("coyote");
        Cage cage = new Cage(coyote);
        Volunteer volunteer = new Volunteer();
        int expectedTime = 20; // 5 minutes to feed, 10 minutes of preparation, and 5 minutes to clean
        int actualTime = volunteer.feedAnimal(cage);
        assertEquals(expectedTime, actualTime);
    }
    
    @Test
    public void testFeedPorcupine() {
        Animal porcupine = new Animal("porcupine");
        Cage cage = new Cage(porcupine);
        Volunteer volunteer = new Volunteer();
        int expectedTime = 15; // 5 minutes to feed and 10 minutes to clean
        int actualTime = volunteer.feedAnimal(cage);
        assertEquals(expectedTime, actualTime);
    }
    
    @Test
    public void testFeedFox() {
        Animal fox = new Animal("fox");
        Cage cage = new Cage(fox);
        Volunteer volunteer = new Volunteer();
        int expectedTime = 15; // 5 minutes to feed and 5 minutes of preparation and cleaning
        int actualTime = volunteer.feedAnimal(cage);
        assertEquals(expectedTime, actualTime);
    }
    
    @Test
    public void testFeedRaccoon() {
        Animal raccoon = new Animal("raccoon");
        Cage cage = new Cage(raccoon);
        Volunteer volunteer = new Volunteer();
        int expectedTime = 10; // 5 minutes to feed and 5 minutes to clean
        int actualTime = volunteer.feedAnimal(cage);
        assertEquals(expectedTime, actualTime);
    }
    
    @Test
    public void testFeedBeaver() {
        Animal beaver = new Animal("beaver");
        Cage cage = new Cage(beaver);
        Volunteer volunteer = new Volunteer();
        int expectedTime = 10; // 5 minutes to feed and 5 minutes to clean
        int actualTime = volunteer.feedAnimal(cage);
        assertEquals(expectedTime, actualTime);
    }
    
    @Test
    public void testCleanCage() {
        Animal raccoon = new Animal("raccoon");
        Cage cage = new Cage(raccoon);
        Volunteer volunteer = new Volunteer();
        int expectedTime = 5; // 5 minutes to clean
        int actualTime = volunteer.cleanCage(cage);
        assertEquals(expectedTime, actualTime);
    }
}
