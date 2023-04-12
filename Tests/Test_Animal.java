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
        Beaver beaver = new Beaver("Test", "beaver test", "beaver", false); // Create a new instance of Beaver
        beaver.cleanCage(); // Call the cleanCage method on the beaver instance
        assertTrue("Beaver cage should be clean", beaver.isCageClean()); // Assert that the beaver cage is clean
    }

    @Test
    public void testFeedOrphanedAnimals() {
        // Create some orphaned animals for testing
        Fox fox = new Fox("fox test", "fox", true);
        Raccoon raccoon = new Raccoon("raccoon test", "raccoon", true);
        Porcupine porcupine = new Porcupine("porcupine test", "porcupine", true);

         // Feed the orphaned animals
         fox.feed();
         raccoon.feed();
         porcupine.feed();

         // Assert that the orphaned animals are fed
         assertTrue("Fox should be fed", fox.isFed());
         assertTrue("Raccoon should be fed", raccoon.isFed());
         assertTrue("Porcupine should be fed", porcupine.isFed());
    }

    @Test
    public void testNocturnalFeeding() {
         Fox fox = new Fox("Foxy", "fox test", "fox", true);
    
         // Set the time to night in the animal care system
         AnimalCareSystem.setIsNight(true);

         // Feed the nocturnal animal
         fox.feed();

         // Assert that the nocturnal animal is fed
         assertTrue("Fox should be fed during the night", fox.isFed());

         // Set the time to day in the animal care system
         AnimalCareSystem.setIsNight(false);

         // Attempt to feed the nocturnal animal during the day
         fox.feed();

         // Assert that the nocturnal animal is not fed during the day
         assertFalse("Fox should not be fed during the day", fox.isFed());
    }

    @Test
    public void testDiurnalFeeding() {
         
          Fox fox = new Fox("Foxy", "fox test", "fox", false);
    
         // Set the time to day in the animal care system
         AnimalCareSystem.setIsNight(false);

         // Feed the diurnal animal
         fox.feed();

         // Assert that the diurnal animal is fed
         assertTrue("Fox should be fed during the day", fox.isFed());

         // Set the time to night in the animal care system
         AnimalCareSystem.setIsNight(true);

         // Attempt to feed the diurnal animal during the night
         fox.feed();

         // Assert that the diurnal animal is not fed during the night
         assertFalse("Fox should not be fed during the night", fox.isFed());
    }

    @Test
    public void testCrepuscularFeeding() {
         Coyote coyote = new Coyote("Wiley", "coyote test", "coyote", false);

         // Set the time to twilight in the animal care system
         AnimalCareSystem.setIsTwilight(true);

         // Feed the crepuscular animal
         coyote.feed();

         // Assert that the crepuscular animal is fed during twilight
         assertTrue("Coyote should be fed during twilight", coyote.isFed());

         // Set the time to day in the animal care system
         AnimalCareSystem.setIsTwilight(false);
         AnimalCareSystem.setIsNight(false);

         // Attempt to feed the crepuscular animal during the day
         coyote.feed();

         // Assert that the crepuscular animal is not fed during the day
         assertFalse("Coyote should not be fed during the day", coyote.isFed());

         // Set the time to night in the animal care system
         AnimalCareSystem.setIsNight(true);

         // Attempt to feed the crepuscular animal during the night
         coyote.feed();

         // Assert that the crepuscular animal is not fed during the night
         assertFalse("Coyote should not be fed during the night", coyote.isFed());
    }
}

