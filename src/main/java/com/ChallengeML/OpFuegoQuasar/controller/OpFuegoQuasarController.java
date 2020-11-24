package com.ChallengeML.OpFuegoQuasar.controller;

import com.ChallengeML.OpFuegoQuasar.dto.MessageDTO;
import com.ChallengeML.OpFuegoQuasar.dto.MessagesDTO;
import com.ChallengeML.OpFuegoQuasar.dto.ResponseDTO;
import com.ChallengeML.OpFuegoQuasar.service.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller that defines the endpoints that the application accepts
 *
 * @author Francisco Hefner
 */
@RestController
public class OpFuegoQuasarController {


    @Autowired
    private CommunicationService communicationService;


    /**
     * @param messages message containing a list of satellite communications
     * @return returns the response the satellites were able to locate the location of the source and the message
     * @author Francisco Hefner
     */
    @PostMapping("/topsecret")
    public ResponseDTO topSecret(@RequestBody MessagesDTO messages) {

        ResponseDTO responseDTO = null;

        responseDTO = communicationService.execute(messages);

        return responseDTO;

    }


    /**
     * @param satelliteName name of the satellite containing the communication
     * @param message       satellite message reported
     * @return returns the response the satellites were able to locate the location of the source and the message
     * @author Francisco Hefner
     */
    @PostMapping("topsecret_split/{satelliteName}")
    public ResponseDTO topsecretSplit(@PathVariable String satelliteName, @RequestBody MessageDTO message) {

        ResponseDTO responseDTO = null;
        message.setName(satelliteName);

        responseDTO = communicationService.execute(message);

        return responseDTO;
    }

    /**
     * @return returns the response the satellites were able to locate the location of the source and the message
     * @author Francisco Hefner
     */
    @GetMapping("/topsecret_split/")
    public ResponseDTO topsecretSplit() {
        ResponseDTO responseDTO = null;

        responseDTO = communicationService.execute();

        return responseDTO;

    }


}
