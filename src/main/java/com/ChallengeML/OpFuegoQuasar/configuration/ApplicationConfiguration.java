package com.ChallengeML.OpFuegoQuasar.configuration;


import com.ChallengeML.OpFuegoQuasar.model.Communications;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

/**
 * Bean that manages data and communications
 *
 * @author Francisco Hefner
 */
@Configuration
@PropertySource("classpath:satellites-config.properties")
public class ApplicationConfiguration {

    @Bean
    @Scope("singleton")
    public Communications communicationsSingleton() {


        return new Communications(new HashMap<>());
    }


}



