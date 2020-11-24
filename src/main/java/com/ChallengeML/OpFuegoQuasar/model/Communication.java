package com.ChallengeML.OpFuegoQuasar.model;


/**
 * Class representing a communication from a satellite
 *
 * @author Francisco Hefner
 */
public class Communication {

    private Satellite satellite;
    private float distance;
    private String[] messenge;

    public Communication(Satellite satellite, float distance, String[] messenge) {
        this.satellite = satellite;
        this.distance = distance;
        this.messenge = messenge;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String[] getMessenge() {
        return messenge;
    }

    public void setMessenge(String[] messenge) {
        this.messenge = messenge;
    }
}
