import static org.junit.Assert.assertThrows;

import org.junit.Test;

import src.Animals.Beaver;
import src.Animals.Coyote;
import src.Animals.Fox;
import src.Animals.Porcupine;
import src.Animals.Raccoon;
import src.Exceptions.InvalidAnimalTypeException;

public class Test_Animal {

     /**
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_CoyoteThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Coyote("Test", "coyote test", "coyoteWRONG",false));
    }

    /**
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_PorcupinesThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Porcupine("Test", "coyote test", "porcupineWRONG",false));
    }

    /**
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_RaccoonThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Raccoon("Test", "Raccoon test", "RaccoonWRONG",false));
    }

    /**
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_FoxThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Fox("Test", "fox test", "foxWRONG",false));
    }

    /**
     * If an invalid argument of "animalSpecies is passed to the 
     * constructor. The class should thorw InvalidAnimalTypeException.ö
     */
    @Test
    public void invalidAnimalType_BeaverThorws(){
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> new Beaver("Test", "beaver test", "beaverWRONG",false));
    }
    @Test
    public void invalidMultipleIds(){
        assertThrows("Should throw invalidMultipleIdsException if same AnimalIds assigned",
                InvalidAnimalTypeException.class,
                () -> {
                    new Beaver("Test", "beaver test", "beaver",false);
                    new Beaver("Test", "beaver test", "beaver",false);
                }

        );
    }

    @Test
    public void testFeedCoyote() {
        assertThrows("Should throw InvalidAnimalTypeException if invalid animal type",
            InvalidAnimalTypeException.class, 
        () -> {
            Coyote coyote = new Coyote("Test", "coyote test", "coyote", false);
            coyote.feed(); // calling the feed method on coyote object
        });
    }

    @Test
    public void testFeedFox() {
        Fox fox = new Fox("Test", "fox test", "fox", false); // Create a new instance of Fox
        String expected = "Feeding fox"; // Set the expected result
        String actual = fox.feed(); // Call the feed method on the fox instance
        assertEquals(expected, actual); // Assert that the expected result matches the actual result
    }

    @Test
    public void testFeedPorcupine() {
        Porcupine porcupine = new Porcupine("Test", "porcupine test", "porcupine", true); // Create a new instance of Porcupine
        String expected = "Feeding porcupine"; // Set the expected result
        String actual = porcupine.feed(); // Call the feed method on the porcupine instance
        assertEquals(expected, actual); // Assert that the expected result matches the actual result
    }

    @Test
    public void testFeedRaccoon() {
        Raccoon raccoon = new Raccoon("Test", "raccoon test", "raccoon", false); // Create a new instance of Raccoon
        String expected = "Feeding raccoon"; // Set the expected result
        String actual = raccoon.feed(); // Call the feed method on the fox instance
        assertEquals(expected, actual); // Assert that the expected result matches the actual result
    }

    @Test
    public void testFeedBeaver() {
        Beaver beaver = new Beaver("Test", "beaver test", "beaver", false); // Create a new instance of Beaver
        String expected = "Feeding beaver"; // Set the expected result
        String actual = beaver.feed(); // Call the feed method on the beaver instance
        assertEquals(expected, actual); // Assert that the expected result matches the actual result
    }

    @Test
    public void testCleanCage() {
        // Animal animal = new Animal("coyote");
        //TODO: Test cleaning a cage
    }

    @Test
    public void testFeedOrphanedAnimals() {
        // Animal animal1 = new Animal("coyote");
        // Animal animal2 = new Animal("coyote");
        //TODO: Test feeding orphaned animals
    }

    @Test
    public void testNocturnalFeeding() {
        // Animal animal = new Animal("fox");
        //TODO: Test nocturnal feeding
    }

    @Test
    public void testDiurnalFeeding() {
        // Animal animal = new Animal("beaver");
        //TODO: Test diurnal feeding
    }

    @Test
    public void testCrepuscularFeeding() {
        // Animal animal = new Animal("coyote");
        //TODO: Test crepuscular feeding
    }
}

