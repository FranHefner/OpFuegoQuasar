package com.ChallengeML.OpFuegoQuasar.dto;

import java.util.Arrays;


/**
 * Message that is received as a parameter of some endpoints *
 *
 * @author Francisco Hefner
 */
public class MessageDTO {

    private String name;
    private float distance;
    private String[] message;

    public MessageDTO(String name, float distance, String[] message) {
        this.name = name;
        this.distance = distance;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageDTO that = (MessageDTO) o;

        if (Float.compare(that.distance, distance) != 0) return false;
        if (!name.equals(that.name)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (distance != +0.0f ? Float.floatToIntBits(distance) : 0);
        result = 31 * result + Arrays.hashCode(message);
        return result;
    }
}
