package com.soap.soapclient.services;

import com.soap.soapclient.wsdl.GetFullPersonResponse;
import com.soap.soapclient.wsdl.GetPartialPersonByIdRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


@Slf4j
@RequiredArgsConstructor
@Service
public class SkillsClient extends WebServiceGatewaySupport {

    private final String PATH_PREFIX = "/api/v1/ws/persons";
    private final String TARGET_NAMESPACE_PREFIX = "http://localhost:8082" + PATH_PREFIX;

    public GetFullPersonResponse updateSkillValue(String personEmail, String skillName, int skillValue) {

        GetPartialPersonByIdRequest request = new GetPartialPersonByIdRequest();
        request.setPersonId(personEmail);

        GetFullPersonResponse response = (GetFullPersonResponse) getWebServiceTemplate()
                .marshalSendAndReceive(TARGET_NAMESPACE_PREFIX, request,
                        new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "/GetPersonsRequest"));

        return response;
    }

}
