package src.Utils;

import src.Animals.Animal;
import src.Animals.Beaver;
import src.Animals.Coyote;
import src.Animals.Fox;
import src.Animals.Porcupine;
import src.Animals.Raccoon;
import src.Exceptions.InvalidAnimalType;

public class AnimalCreaterUtil {
    private final static String FOX = "fox";
    private final static String COYOTE = "coyote";
    private final static String PORCUPINE = "porcupine";
    private final static String RACCON = "raccoon";
    private final static String BEAVER = "beaver";


    public static Animal createAnimal(String animalId, String animalNickName, String animalSpecies) throws InvalidAnimalType{
        if(animalSpecies.equals(FOX)){
            return new Fox(animalId, animalNickName, animalSpecies);
        } else if(animalSpecies.equals(COYOTE)){
            return new Coyote(animalId, animalNickName, animalSpecies);
        }else if(animalSpecies.equals(PORCUPINE)){
            return new Porcupine(animalId, animalNickName, animalSpecies);
        }else if(animalSpecies.equals(RACCON)){
            return new Raccoon(animalId, animalNickName, animalSpecies);
        }else if(animalSpecies.equals(PORCUPINE)){
            return new Beaver(animalId, animalNickName, animalSpecies);
        }else {
            throw new InvalidAnimalType();
        }
        
    }
    
    public static String GetAnimalType(String databaseName) throws InvalidAnimalType{
        if(databaseName.equals(FOX) || databaseName.equals(RACCON)){
            return "Nocturnal";
        } else if(databaseName.equals(BEAVER)){
            return "Diurnal";
        } else if(databaseName.equals(COYOTE)||databaseName.equals(PORCUPINE)){
            return "Crepuscular";
        }else {
            throw new InvalidAnimalType();
        }
    }


}
