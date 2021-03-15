package com.soap.soapclient.services;

import com.soap.soapclient.domain.dto.properties.WsClientProperties;
import com.soap.soapclient.wsdl.GetPersonsRequest;
import com.soap.soapclient.wsdl.GetPersonsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


@Slf4j
@RequiredArgsConstructor
public class PersonsClient extends WebServiceGatewaySupport {

//    private final String PATH_PREFIX = "/api/v1/ws/persons";
//    private final String TARGET_NAMESPACE_PREFIX = "https://localhost:8443" + PATH_PREFIX;

    private final WebServiceTemplate webServiceTemplate;
    private final WsClientProperties wsClientProperties;

    public GetPersonsResponse getPersonById(String personId) {

        GetPersonsRequest request = new GetPersonsRequest();
        request.setPersonId(personId);

        GetPersonsResponse response = (GetPersonsResponse) webServiceTemplate.marshalSendAndReceive(
                wsClientProperties.getTargetNamespace(),
                request,
                new SoapActionCallback(wsClientProperties.getTargetNamespace() + "/GetPersonsRequest"));

        return response;
    }

}
