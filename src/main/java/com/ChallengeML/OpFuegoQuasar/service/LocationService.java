package com.ChallengeML.OpFuegoQuasar.service;

import com.ChallengeML.OpFuegoQuasar.client.OpSatelliteClient;
import com.ChallengeML.OpFuegoQuasar.model.Communication;
import com.ChallengeML.OpFuegoQuasar.model.Communications;
import com.ChallengeML.OpFuegoQuasar.model.Coordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Service that locates a position
 *
 * @author Francisco Hefner
 */
@Service
public class LocationService {

    @Value("${satelite.1.name}")
    private String satelite1Name;

    @Value("${satelite.2.name}")
    private String satelite2Name;

    @Value("${satelite.3.name}")
    private String satelite3Name;

    @Autowired
    private Communications communicationsSingleton;

    @Autowired
    private OpSatelliteClient opSatelliteClient;


    public Coordinates triangulatePosition() {

        Map<String, Communication> communicationMap = communicationsSingleton.getCommunicationMap();

        if (communicationMap.values().size() < 3) {
            throw new ResponseStatusException(NOT_FOUND, "Faltan datos para realizar las operaciones");
        }
        try {
            return opSatelliteClient.getLocation(communicationMap.get(satelite1Name).getDistance(),
                    communicationMap.get(satelite2Name).getDistance(),
                    communicationMap.get(satelite3Name).getDistance());
        } catch (Exception ex) {

            throw new ResponseStatusException(NOT_FOUND, "Por favor, verifique que los datos de los nombres de los satelites sean correctos");
        }


    }


}
