package com.soap.soapclient.configuration;

import com.soap.soapclient.services.PersonsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    private final String PATH_PREFIX = "/api/v1/ws";
    private final String TARGET_NAMESPACE_PREFIX = "http://localhost:8082" + PATH_PREFIX;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.soap.soapclient.wsdl");
        return marshaller;
    }

    @Bean
    public PersonsClient personsClient(Jaxb2Marshaller marshaller) {
        PersonsClient client = new PersonsClient();
        client.setDefaultUri(TARGET_NAMESPACE_PREFIX);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
