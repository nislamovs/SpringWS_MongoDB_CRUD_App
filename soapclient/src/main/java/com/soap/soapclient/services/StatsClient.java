package com.soap.soapclient.services;

import com.soap.soapclient.wsdl.GetPersonsRequest;
import com.soap.soapclient.wsdl.GetPersonsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import static java.lang.String.format;
import static java.time.Instant.now;


@Service
@Slf4j
public class StatsClient extends WebServiceGatewaySupport {

    private final String PATH_PREFIX = "/api/v1/ws/persons";
    private final String TARGET_NAMESPACE_PREFIX = "http://localhost:8082" + PATH_PREFIX;

    public GetPersonsResponse updateStatsValue(String email, String statName, String statValue) {

        GetPersonsRequest request = new GetPersonsRequest();
        request.setPersonId(email);

        GetPersonsResponse response = (GetPersonsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(TARGET_NAMESPACE_PREFIX, request,
                        new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "/GetPersonsRequest"));

        return response;
    }

}
