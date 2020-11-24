package com.ChallengeML.OpFuegoQuasar.model;


/**
 * Class that represents a satellite with name and location
 *
 * @author Francisco Hefner
 */
public class Satellite {

    String name;
    Coordinates location;


    public Satellite(String name, Coordinates location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }
}
