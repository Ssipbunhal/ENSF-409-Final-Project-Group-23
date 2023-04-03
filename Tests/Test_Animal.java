import static org.junit.Assert.assertThrows;

import org.junit.Test;

import src.Animals.Animal;
import src.Animals.Beaver;
import src.Animals.Coyote;
import src.Animals.Fox;
import src.Animals.Porcupine;
import src.Animals.Raccoon;
import src.Exceptions.InvalidAnimalTypeException;

public class Test_Animal {

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
    
    @Test
    public void testFeedCoyote() {
        // Animal coyote = new Animal("coyote");
        //TODO: Test feeding a coyote
    }

    @Test
    public void testFeedFox() {
        // Animal fox = new Animal("fox");
        //TODO: Test feeding a fox
    }

    @Test
    public void testFeedPorcupine() {
        // Animal porcupine = new Animal("porcupine");
        //TODO: Test feeding a porcupine
    }

    @Test
    public void testFeedRaccoon() {
        // Animal raccoon = new Animal("raccoon");
        //TODO: Test feeding a raccoon
    }

    @Test
    public void testFeedBeaver() {
        // Animal beaver = new Animal("beaver");
        //TODO: Test feeding a beaver
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

