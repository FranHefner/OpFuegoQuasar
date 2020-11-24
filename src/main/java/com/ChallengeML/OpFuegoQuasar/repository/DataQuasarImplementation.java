package com.ChallengeML.OpFuegoQuasar.repository;

import com.ChallengeML.OpFuegoQuasar.model.Coordinates;
import com.ChallengeML.OpFuegoQuasar.model.Satellite;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Implementation of the interface that loads the information from the satellites
 * @author Francisco Hefner
 */
@Component
public class DataQuasarImplementation implements DataQuasar {

    @Value("${satelite.1.name}")
    private String satelite1Name;
    @Value("${satelite.1.coor.x}")
    private float satelite1CoorX;
    @Value("${satelite.1.coor.y}")
    private float satelite1CoorY;

    @Value("${satelite.2.name}")
    private String satelite2Name;
    @Value("${satelite.2.coor.x}")
    private float satelite2CoorX;
    @Value("${satelite.2.coor.y}")
    private float satelite2CoorY;


    @Value("${satelite.3.name}")
    private String satelite3Name;
    @Value("${satelite.3.coor.x}")
    private float satelite3CoorX;
    @Value("${satelite.3.coor.y}")
    private float satelite3CoorY;

    private Map<String, Satellite> satelliteMap;

    @Override
    public Map<String, Satellite> getInfoSatellites() {

        return satelliteMap;
    }


    @PostConstruct
    private void postConstruct() {

        Satellite satellite1 = new Satellite(satelite1Name, new Coordinates(satelite1CoorX, satelite1CoorY));
        Satellite satellite2 = new Satellite(satelite2Name, new Coordinates(satelite2CoorX, satelite2CoorY));
        Satellite satellite3 = new Satellite(satelite3Name, new Coordinates(satelite3CoorX, satelite3CoorY));

        this.satelliteMap = new HashMap<>();

        satelliteMap.put(satellite1.getName(), satellite1);
        satelliteMap.put(satellite2.getName(), satellite2);
        satelliteMap.put(satellite3.getName(), satellite3);
    }


}