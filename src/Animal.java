package src;

import java.time.LocalDate;

public class Animal {

    private FeedingTime feedingtime;
    private String animalID;
    private String animalNickname;
    private String animalSpecies;
    private String diurnality;
    private boolean orphan;

    public Animal(String animalID, String animalNickname, String animalSpecies, String diurnality,boolean orphan,FeedingTime feedingtime) {
        this.animalID = animalID;
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
        this.diurnality = diurnality;
        this.orphan = orphan;
        this.feedingtime = feedingtime;
    }

    public LocalDate getFeedtime() {

        return this.feedingtime.getFeedtime();
    }

    public void setFeedingTime(FeedingTime feedingTime) {

        this.feedingtime = feedingTime;
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

