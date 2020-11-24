package com.ChallengeML.OpFuegoQuasar.service;

import com.ChallengeML.OpFuegoQuasar.client.OpSatelliteClient;
import com.ChallengeML.OpFuegoQuasar.model.Communication;
import com.ChallengeML.OpFuegoQuasar.model.Communications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Service that decrypts messages
 *
 * @author Francisco Hefner
 */
@Service
public class DecryptMessageService {


    @Autowired
    private OpSatelliteClient opSatelliteClient;

    @Autowired
    private Communications communicationsSingleton;


    public String decryptMessenger() {

        try {
            Map<String, Communication> communicationMap = communicationsSingleton.getCommunicationMap();

            return opSatelliteClient.GetMessage(communicationMap.values().stream().map(Communication::getMessenge).toArray(String[][]::new));

        } catch (Exception ex) {
            throw new ResponseStatusException(NOT_FOUND, "Según los datos ingresados, no fué posible determinar el mensaje");

        }

    }
}
