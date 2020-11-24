package com.ChallengeML.OpFuegoQuasar.dto;

import java.util.ArrayList;
import java.util.Objects;


/**
 * List of message that is received as a parameter of some endpoints
 *
 * @author Francisco Hefner
 */
public class MessagesDTO {

    ArrayList<MessageDTO> satellites;

    public MessagesDTO() {
    }

    public MessagesDTO(ArrayList<MessageDTO> satellites) {
        this.satellites = satellites;
    }

    public ArrayList<MessageDTO> getSatellites() {
        return satellites;
    }

    public void setSatellites(ArrayList<MessageDTO> satellites) {
        this.satellites = satellites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagesDTO that = (MessagesDTO) o;

        return Objects.equals(satellites, that.satellites);
    }

    @Override
    public int hashCode() {
        return satellites != null ? satellites.hashCode() : 0;
    }
}
