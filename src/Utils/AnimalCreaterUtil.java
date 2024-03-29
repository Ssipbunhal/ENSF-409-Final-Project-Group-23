/**
 * This class is a helper class which is corrently used by the database
 * to create animals from the database query.
 * @author Erik Öberg
 * @since 2023-04-11
 */
package src.Utils;

import src.Animals.Animal;
import src.Animals.Beaver;
import src.Animals.Coyote;
import src.Animals.Fox;
import src.Animals.Porcupine;
import src.Animals.Raccoon;
import src.Exceptions.InvalidAnimalTypeException;

public class AnimalCreaterUtil {
    private final static String FOX = "fox";
    private final static String COYOTE = "coyote";
    private final static String PORCUPINE = "porcupine";
    private final static String RACCON = "raccoon";
    private final static String BEAVER = "beaver";

    /**
     * Helpers method which the database uses to create animal 
     * based on a magic string in the database.
     * @param animalId
     * @param animalNickName
     * @param animalSpecies
     * @param orp
     * @return
     * @throws InvalidAnimalTypeException
     */
    public static Animal createAnimal(String animalId, String animalNickName, String animalSpecies, boolean orp) throws InvalidAnimalTypeException{
        if(animalSpecies.equals(FOX)){
            return new Fox(animalId, animalNickName, animalSpecies, orp);
        } else if(animalSpecies.equals(COYOTE)){
            return new Coyote(animalId, animalNickName, animalSpecies, orp);
        }else if(animalSpecies.equals(PORCUPINE)){
            return new Porcupine(animalId, animalNickName, animalSpecies, orp);
        }else if(animalSpecies.equals(RACCON)){
            return new Raccoon(animalId, animalNickName, animalSpecies, orp);
        }else if(animalSpecies.equals(PORCUPINE)){
            return new Beaver(animalId, animalNickName, animalSpecies, orp);
        }else {
            throw new InvalidAnimalTypeException();
        }
        
    }
    
    /**
     * Gets the type of animal based on the species in the database.
     * @param databaseName
     * @return
     * @throws InvalidAnimalTypeException
     */
    public static String GetAnimalType(String databaseName) throws InvalidAnimalTypeException{
        if(databaseName.equals(FOX) || databaseName.equals(RACCON)){
            return "Nocturnal";
        } else if(databaseName.equals(BEAVER)){
            return "Diurnal";
        } else if(databaseName.equals(COYOTE)||databaseName.equals(PORCUPINE)){
            return "Crepuscular";
        }else {
            throw new InvalidAnimalTypeException();
        }
    }


}
