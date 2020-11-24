package com.ChallengeML.OpFuegoQuasar.model;


/**
 * Class that represents the coordinates used in the satellites and in the response of the services
 *
 * @author Francisco Hefner
 */
public class Coordinates {
    float coordinateX;
    float coordinateY;

    public Coordinates(float coordinateX, float coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }
}
