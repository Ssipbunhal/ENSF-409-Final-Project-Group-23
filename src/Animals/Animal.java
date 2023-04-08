package src.Animals;

import src.AnimalCare;

public abstract class Animal {

    private String animalID;
    private String animalNickname;
    private String animalSpecies;
    private String diurnality;
    private boolean orphan;
    private AnimalCare feedingTime;

    // public Animal(String animalID, String animalNickname, String animalSpecies,boolean orphan,FeedingTime feedingtime) {
    //     this.animalID = animalID;
    //     this.animalNickname = animalNickname;
    //     this.animalSpecies = animalSpecies;
    //     this.orphan = orphan;
    //     // this.feedingtime = feedingtime;
    // }

    // public Animal(String animalID, String animalNickname, String animalSpecies) {
    //     this.animalID = animalID;
    //     this.animalNickname = animalNickname;
    //     this.animalSpecies = animalSpecies;
    // }

    // public Animal(String animalID, String animalNickname, String animalSpecies, String animalType) {
    //     this.animalID = animalID;
    //     this.animalNickname = animalNickname;
    //     this.animalSpecies = animalSpecies;
    //     this.diurnality = animalType;
    // }

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
        // TODO Auto-generated method stub
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

