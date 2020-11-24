package com.ChallengeML.OpFuegoQuasar.repository;

import com.ChallengeML.OpFuegoQuasar.model.Satellite;

import java.util.Map;

/**
 * Interface to obtain information from satellites
 *
 * @author Francisco Hefner
 */
public interface DataQuasar {


    Map<String, Satellite> getInfoSatellites();


}
