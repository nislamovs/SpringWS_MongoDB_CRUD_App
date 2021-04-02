package com.soap.soapclient.services;

import com.soap.soapclient.domain.dto.properties.WsClientProperties;
import com.soap.soapclient.wsdl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.annotation.PostConstruct;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class PersonsClient extends WebServiceGatewaySupport {

    private String TARGET_NAMESPACE_PREFIX;
    private final WebServiceTemplate webServiceTemplate;
    private final WsClientProperties wsClientProperties;

    @PostConstruct
    void postContruct() {
        TARGET_NAMESPACE_PREFIX = wsClientProperties.getTargetNamespace() + "/persons";
    }

    public PersonPartial getPartialPersonInfoById(String personId) {
        GetPartialPersonByIdRequest request = new GetPartialPersonByIdRequest();
        request.setPersonId(personId);

        GetPartialPersonResponse response = (GetPartialPersonResponse) webServiceTemplate.marshalSendAndReceive(
                TARGET_NAMESPACE_PREFIX,
                request,
                new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "GetPartialPersonInfoByIdRequest"));

        return response.getPerson();
    }

    public PersonFull getFullPersonInfoById(String personId) {
        GetFullPersonByIdRequest request = new GetFullPersonByIdRequest();
        request.setPersonId(personId);

        GetFullPersonResponse response = (GetFullPersonResponse) webServiceTemplate.marshalSendAndReceive(
                TARGET_NAMESPACE_PREFIX,
                request,
                new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "GetFullPersonInfoByIdRequest"));

        return response.getPerson();
    }

    public PersonPartial getPartialPersonInfoByEmail(String personEmail) {

        GetPartialPersonByEmailRequest request = new GetPartialPersonByEmailRequest();
        request.setPersonEmail(personEmail);

        GetPartialPersonResponse response = (GetPartialPersonResponse) webServiceTemplate.marshalSendAndReceive(
                TARGET_NAMESPACE_PREFIX,
                request,
                new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "GetPartialPersonInfoByEmailRequest"));

        return response.getPerson();
    }

    public PersonFull getFullPersonInfoByEmail(String personEmail) {

        GetFullPersonByEmailRequest request = new GetFullPersonByEmailRequest();
        request.setPersonEmail(personEmail);

        GetFullPersonResponse response = (GetFullPersonResponse) webServiceTemplate.marshalSendAndReceive(
                TARGET_NAMESPACE_PREFIX,
                request,
                new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "GetFullPersonInfoByEmailRequest"));

        return response.getPerson();
    }

    public PersonPartial getPartialPersonInfoByNameSurname(String name, String surname) {

        GetPartialPersonByNameSurnameRequest request = new GetPartialPersonByNameSurnameRequest();
        request.setPersonName(name);
        request.setPersonSurname(surname);

        GetPartialPersonResponse response = (GetPartialPersonResponse) webServiceTemplate.marshalSendAndReceive(
                TARGET_NAMESPACE_PREFIX,
                request,
                new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "GetPartialPersonByNameSurnameRequest"));

        return response.getPerson();
    }

    public PersonFull getFullPersonInfoByNameSurname(String name, String surname) {

        GetFullPersonByNameSurnameRequest request = new GetFullPersonByNameSurnameRequest();
        request.setPersonName(name);
        request.setPersonSurname(surname);

        GetFullPersonResponse response = (GetFullPersonResponse) webServiceTemplate.marshalSendAndReceive(
                TARGET_NAMESPACE_PREFIX,
                request,
                new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "GetFullPersonByNameSurnameRequest"));

        return response.getPerson();
    }

    public List<PersonPartial> getPartialPersonInfoList(int page, int size) {

        GetPartialPersonInfoListRequest request = new GetPartialPersonInfoListRequest();
        request.setPage(page);
        request.setSize(size);

        GetPartialPersonInfoListResponse response = (GetPartialPersonInfoListResponse) webServiceTemplate.marshalSendAndReceive(
                TARGET_NAMESPACE_PREFIX,
                request,
                new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "GetPartialPersonInfoListRequest"));

        return response.getPersonList();
    }

    public List<PersonFull> getFullPersonInfoList(int page, int size) {

        GetFullPersonInfoListRequest request = new GetFullPersonInfoListRequest();
        request.setPage(page);
        request.setSize(size);

        GetFullPersonInfoListResponse response = (GetFullPersonInfoListResponse) webServiceTemplate.marshalSendAndReceive(
                TARGET_NAMESPACE_PREFIX,
                request,
                new SoapActionCallback(TARGET_NAMESPACE_PREFIX + "GetFullPersonInfoListRequest"));

        return response.getPersonList();
    }
}
