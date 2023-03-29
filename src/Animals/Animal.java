package src.Animals;

import src.FeedingTime;

public abstract class Animal {

    private String animalID;
    private String animalNickname;
    private String animalSpecies;
    private String diurnality;
    private boolean orphan;
    private FeedingTime feedingTime;

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

    public Animal(String animalID, String animalNickname, String animalSpecies, String animalType) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
        this.diurnality = animalType;
    }

    public Animal(String animalID, String animalNickname, String animalSpecies, String animalType, boolean orphan) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
        this.diurnality = animalType;
        this.orphan = true;
    }

    
    public FeedingTime getFeedingTime() {
        return feedingTime;
    }

    public void setFeedingTime(FeedingTime feedingTime) {

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
}

