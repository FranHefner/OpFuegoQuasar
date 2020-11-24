package com.ChallengeML.OpFuegoQuasar.client;


import com.ChallengeML.OpFuegoQuasar.model.Communication;
import com.ChallengeML.OpFuegoQuasar.model.Communications;
import com.ChallengeML.OpFuegoQuasar.model.Coordinates;
import com.ChallengeML.OpFuegoQuasar.model.Satellite;
import com.ChallengeML.OpFuegoQuasar.repository.DataQuasarImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Client that contains the functionality to execute the operations
 *
 * @author Francisco Hefner
 */
@Component
public class OpSatelliteClient {

    @Autowired
    private DataQuasarImplementation infoSatellites;
    @Autowired
    private Communications communicationsSingleton;

    @Value("${satelite.1.name}")
    private String satelite1Name;

    @Value("${satelite.2.name}")
    private String satelite2Name;

    @Value("${satelite.3.name}")
    private String satelite3Name;


    private static ArrayList<Coordinates> circle_circle_intersection(double c1CoorX, double c1CoorY, double c1Radius,
                                                                     double c2CoorX, double c2CoorY, double c2Radius) {


        ArrayList<Coordinates> coorFound = new ArrayList<>();

        double a, distX, distY, totalDist, hypotenuse, rx, ry;
        double x2, y2;
        double xi, yi, xi_prime, yi_prime;

        /* distX y distY are the vertical and horizontal distances between
         * the circle centers.
         */
        distX = c2CoorX - c1CoorX;
        distY = c2CoorY - c1CoorY;

        /* Determine the straight-line distance between the centers. */

        totalDist = Math.hypot(distX, distY);

        /* Check for solvability. */
        if (totalDist > (c1Radius + c2Radius)) {
            /* no solution. circles do not intersect. */
            return coorFound;
        }


        if (totalDist < (Math.abs(c1Radius - c2Radius))) {
            /* no solution. one circle is contained in the other */
            return coorFound;
        }

        /* 'point 2' is the point where the line through the circle
         * intersection points crosses the line between the circle
         * centers.
         */

        /* Determine the distance from point 0 to point 2. */
        a = ((c1Radius * c1Radius) - (c2Radius * c2Radius) + (totalDist * totalDist)) / (2.0 * totalDist);

        /* Determine the coordinates of point 2. */
        x2 = c1CoorX + (distX * a / totalDist);
        y2 = c1CoorY + (distY * a / totalDist);

        /* Determine the distance from point 2 to either of the
         * intersection points.
         */
        hypotenuse = Math.sqrt((c1Radius * c1Radius) - (a * a));

        /* Now determine the offsets of the intersection points from
         * point 2.
         */
        rx = -distY * (hypotenuse / totalDist);
        ry = distX * (hypotenuse / totalDist);

        /* Determine the absolute intersection points. */
        xi = x2 + rx;
        xi_prime = x2 - rx;
        yi = y2 + ry;
        yi_prime = y2 - ry;

        Coordinates coorIntersection1 = new Coordinates((float) xi, (float) yi);
        Coordinates coorIntersection2 = new Coordinates((float) xi_prime, (float) yi_prime);

        coorFound.add(coorIntersection1);
        coorFound.add(coorIntersection2);

        return coorFound;
    }

    public Coordinates getLocation(float... distances) {


        try {

            Map<String, Satellite> currentSatellites = infoSatellites.getInfoSatellites();
            Map<String, Communication> communicationMap = communicationsSingleton.getCommunicationMap();

            Coordinates satellite1Coor = currentSatellites.get(satelite1Name).getLocation();
            Coordinates satellite2Coor = currentSatellites.get(satelite2Name).getLocation();
            Coordinates satellite3Coor = currentSatellites.get(satelite3Name).getLocation();

            float satellite1Dist = communicationMap.get(satelite1Name).getDistance();
            float satellite2Dist = communicationMap.get(satelite2Name).getDistance();
            float satellite3Dist = communicationMap.get(satelite3Name).getDistance();


            ArrayList<Coordinates> coorFound = new ArrayList<>();
            ArrayList<Coordinates> triangulateCoorFound = new ArrayList<>();


            coorFound = circle_circle_intersection(satellite1Coor.getCoordinateX(), satellite1Coor.getCoordinateY(), satellite1Dist,
                    satellite2Coor.getCoordinateX(), satellite2Coor.getCoordinateY(), satellite2Dist);


            if (coorFound.isEmpty()) {
                return null;
            } else {
                if (coorFound.size() == 2 &&
                        coorFound.get(0).getCoordinateX() == coorFound.get(1).getCoordinateX() &&
                        coorFound.get(0).getCoordinateY() == coorFound.get(1).getCoordinateY()) {
                    //Find only one coor ! Unique result, satellite 3 no is necessary
                    return coorFound.get(0);
                } else {
                    // Found two result, need satellite 3
                    triangulateCoorFound = circle_circle_intersection(satellite1Coor.getCoordinateX(), satellite1Coor.getCoordinateY(), satellite1Dist,
                            satellite3Coor.getCoordinateX(), satellite3Coor.getCoordinateY(), satellite3Dist);

                    if (triangulateCoorFound.isEmpty()) {
                        return null; // Bad lucky
                    } else {
                        if (triangulateCoorFound.size() == 1) {

                            return triangulateCoorFound.get(0); // Found !
                        } else {
                            if (coorFound.contains(triangulateCoorFound.get(0))) {
                                return coorFound.get(0);
                            } // Found !
                            if (coorFound.contains(triangulateCoorFound.get(1))) {
                                return coorFound.get(1);
                            } // Found !
                        }
                    }

                }
            }

        } catch (Exception ex) {
            throw new ResponseStatusException(NOT_FOUND, "No se pudo calcular la posicion, por favor verifique los datos ingresados");

        }

        return null;
    }

    public String GetMessage(String[]... messenges) {


        String finalMessenge = "";
        int posistionWord = 0;
        int indexMessenges = 0;

        for (String messageWord : messenges[0]) {
            if (messageWord.isEmpty() && messenges.length > 1) {
                indexMessenges = 1;
                while (messageWord.isEmpty() && indexMessenges < messenges.length) {

                    messageWord = messenges[indexMessenges][posistionWord];
                    indexMessenges++;
                }
                if (posistionWord == 0) {
                    finalMessenge = messageWord;
                } else {
                    finalMessenge += " " + messageWord;
                }
            } else {
                if (posistionWord == 0) {
                    finalMessenge = messageWord;
                } else {
                    finalMessenge += " " + messageWord;
                }
            }
            if (messageWord.isEmpty()) {
                throw new ResponseStatusException(NOT_FOUND, "Según los datos ingresados, no fué posible determinar el mensaje");
            }
            posistionWord++;
        }
        return finalMessenge;
    }
}
