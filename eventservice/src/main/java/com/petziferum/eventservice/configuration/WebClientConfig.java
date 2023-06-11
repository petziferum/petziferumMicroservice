package com.petziferum.eventservice.configuration;

import com.petziferum.eventservice.client.ParticipantClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.util.Logger;

@Slf4j
@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient ParticipantWebClient() {
        return WebClient.builder()
                .baseUrl("http://participant-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public ParticipantClient participantClient() {
        log.info("####### get ParticipantClient");
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(ParticipantWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(ParticipantClient.class);
    }

/*
This code defines a configuration class for a Spring WebClient that will be used to consume a RESTful web service
provided by a microservice called participant-service. The configuration class is annotated with @Configuration,
indicating that it provides configuration information to the Spring application context.

The class has two methods:

ParticipantWebClient(): This method defines a bean for the WebClient. The WebClient is created using the
WebClient.builder() method. The base URL for the WebClient is set to "http://participant-service".
The LoadBalancedExchangeFilterFunction is used to add load balancing capabilities to the WebClient,
which enables it to make requests to multiple instances of the participant-service service.
The method returns the WebClient bean.

participantClient(): This method creates a client for the ParticipantClient interface, which represents the
RESTful web service provided by the participant-service microservice. The HttpServiceProxyFactory is used
to create a proxy for the ParticipantClient interface. The WebClient created by the ParticipantWebClient()
method is used as the underlying HTTP client for the proxy. The method returns the ParticipantClient proxy.

Overall, this configuration class provides a WebClient bean that can be used to consume the RESTful web service
provided by the participant-service microservice, and a client proxy that can be used to call the methods
defined in the ParticipantClient interface. The use of LoadBalancedExchangeFilterFunction enables the WebClient
to make requests to multiple instances of the participant-service service for load balancing purposes.
 */
}
