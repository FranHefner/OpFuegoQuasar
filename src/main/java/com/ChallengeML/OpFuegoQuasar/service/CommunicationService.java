package com.ChallengeML.OpFuegoQuasar.service;

import com.ChallengeML.OpFuegoQuasar.dto.MessageDTO;
import com.ChallengeML.OpFuegoQuasar.dto.MessagesDTO;
import com.ChallengeML.OpFuegoQuasar.dto.ResponseDTO;
import com.ChallengeML.OpFuegoQuasar.model.Communication;
import com.ChallengeML.OpFuegoQuasar.model.Communications;
import com.ChallengeML.OpFuegoQuasar.model.Satellite;
import com.ChallengeML.OpFuegoQuasar.repository.DataQuasarImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Service that responds to requests from the controller
 *
 * @author Francisco Hefner
 */
@Service
public class CommunicationService {

    @Autowired
    private DecryptMessageService decryptMessageService;
    @Autowired
    private LocationService locationService;

    @Autowired
    private Communications communicationsSingleton;

    @Autowired
    private DataQuasarImplementation infoSatellites;


    private void insertNewCommunication(MessageDTO message) {


        Map<String, Communication> communicationMap = communicationsSingleton.getCommunicationMap();
        Map<String, Satellite> currentSatellites = infoSatellites.getInfoSatellites();

        if (message.getName() == null || message.getDistance() == 0 || message.getMessage().length == 0) {
            throw new ResponseStatusException(NOT_FOUND, "Faltan datos para realizar las operaciones");
            //   throw new QuasarValidationException("Faltan datos para reallizar la operación");
        }
        Communication newCommunication = new Communication(currentSatellites.get(message.getName()), message.getDistance(), message.getMessage());
        communicationMap.put(message.getName(), newCommunication);
    }


    public ResponseDTO execute() {
        ResponseDTO response = new ResponseDTO();
        response.setPosition(locationService.triangulatePosition());
        response.setMessage(decryptMessageService.decryptMessenger());


        if (response.getPosition() == null) {
            throw new ResponseStatusException(NOT_FOUND, "Según los datos ingresados, no fué posible determinar la ubicación");
        }

        return response;
    }

    public ResponseDTO execute(MessagesDTO messages) {

        for (MessageDTO message : messages.getSatellites()) {
            insertNewCommunication(message);
        }
        return this.execute();
    }

    public ResponseDTO execute(MessageDTO message) {

        insertNewCommunication(message);

        return this.execute();
    }

}
