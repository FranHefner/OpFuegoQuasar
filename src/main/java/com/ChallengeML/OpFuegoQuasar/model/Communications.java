package com.ChallengeML.OpFuegoQuasar.model;

import java.util.Map;

/**
 * Class representing several simultaneous satellite communications
 *
 * @author Francisco Hefner
 */
public class Communications {

    private volatile Map<String, Communication> communicationMap;

    public Communications() {
    }

    public Communications(Map<String, Communication> map) {

        this.communicationMap = map;
    }

    public Map<String, Communication> getCommunicationMap() {
        return communicationMap;
    }

    public void setCommunicationMap(Map<String, Communication> communicationMap) {
        this.communicationMap = communicationMap;
    }
}
