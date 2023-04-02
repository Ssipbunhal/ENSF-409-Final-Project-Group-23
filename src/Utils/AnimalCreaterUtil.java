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
