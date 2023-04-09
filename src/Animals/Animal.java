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

    /**
     * sets animalcare
     * @param feedingTime
     */
    public void setFeedingTime(AnimalCare feedingTime) {

        this.feedingTime = feedingTime;
    }

    /**
     * gets the animal id
     * @return String animal id
     */
    public String getAnimalID() {

        return this.animalID;
    }

    /**
     * gets the animal name
     * @return String gets the animal name
     */
    public String getAnimalNickname() {

        return this.animalNickname;
    }

    /**
     * gets the AnimalSpecies
     * @return String  Species of the animal
     */
    public String getAnimalSpecies() {

        return this.animalSpecies;
    }

    /**
     * gets the Diurnality of the animal
     * @return String Diurnality
     */
    public String getDiurnality() {
        return this.diurnality;
    }

    /**
     * gets the value of the animal is a orphan
     * @return boolean if animal is a orphan
     */
    public boolean getOrphan() {

        return this.orphan;
    }

    /**
     * sets Orphan of the animal 
     * @return nothing 
     */
    public void setOrphan(boolean status) {

        this.orphan = status;
    }
    /**
     * returns a string of the concatenation of the animal
     * @return String returns FeedingTask - and the animal species
     */
    @Override
    public String toString() {
        return "FeedingTask - " +this.animalSpecies;
    }

    /**
     * Overriden to enable the ICompare to works with lists.
     * @return boolean if the id equals or if the object is the same
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof String){
            var os = (String)o;
            return os == toString();
        }
        return o == this;
    }

}

