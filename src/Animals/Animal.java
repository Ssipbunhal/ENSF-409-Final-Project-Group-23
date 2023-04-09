package src.Animals;

import src.AnimalCare;

public abstract class Animal {

    private String animalID;
    private String animalNickname;
    private String animalSpecies;
    private String diurnality;
    private boolean orphan;
    private AnimalCare feedingTime;


    public Animal(String animalID, String animalNickname, String animalSpecies, String animalType, boolean orphan) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
        this.diurnality = animalType;
        this.orphan = orphan;
    }

    
    public AnimalCare getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(AnimalCare feedingTime) {

        this.feedingTime = feedingTime;
    }

    public String getAnimalID() {

        return this.animalID;
    }


    public String getAnimalNickname() {

        return this.animalNickname;
    }

    public String getAnimalSpecies() {

        return this.animalSpecies;
    }

    public String getDiurnality() {
        return this.diurnality;
    }

    public boolean getOrphan() {

        return this.orphan;
    }

    public void setOrphan(boolean status) {

        this.orphan = status;
    }
    @Override
    public String toString() {
        return "FeedingTask - " +this.animalSpecies;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof String){
            var os = (String)o;
            return os == toString();
        }
        return o == this;
    }

}

