package com.ChallengeML.OpFuegoQuasar.dto;

import com.ChallengeML.OpFuegoQuasar.model.Coordinates;


/**
 * Response that is returned to the user if location is found and decrypts the message
 *
 * @author Francisco Hefner
 */
public class ResponseDTO {


    private Coordinates position;
    private String message;


    public ResponseDTO() {

    }


    public ResponseDTO(Coordinates position, String message) {
        this.position = position;
        this.message = message;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseDTO that = (ResponseDTO) o;

        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

