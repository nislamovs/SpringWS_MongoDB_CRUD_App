package com.soap.soapserver.endpoints.SOAP;

import com.soap.soapserver.converters.mappers.PersonMapper;
import com.soap.soapserver.services.PersonService;
import https.localhost._8443.api.v1.ws.persons.*;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.toList;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class PersonsEndpoint {

    //	private static final String NAMESPACE_URI = "https://localhost:8082/api/v1/ws/persons";
    private static final String NAMESPACE_URI = "https://localhost:8443/api/v1/ws/persons";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    private final PersonService personService;
    private final PersonMapper personMapper;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPartialPersonByIdRequest")
    public GetPartialPersonResponse getPartialPersonInfoById(@RequestPayload GetPartialPersonByIdRequest request) {

        GetPartialPersonResponse response = new GetPartialPersonResponse();
        response.setPerson(
                personMapper.simplify(
                        personMapper.toSoapDTO(
                                personService.retrievePartialPersonInfoById(request.getPersonId())
                        )
                )
        );

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFullPersonByIdRequest")
    public GetFullPersonResponse getFullPersonInfoById(@RequestPayload GetFullPersonByIdRequest request) {

        GetFullPersonResponse response = new GetFullPersonResponse();
        response.setPerson(
                personMapper.toSoapDTO(
                        personService.retrieveFullPersonInfoById(request.getPersonId())
                )
        );

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPartialPersonByEmailRequest")
    public GetPartialPersonResponse getPartialPersonInfoByEmail(@RequestPayload GetPartialPersonByEmailRequest request) {

        GetPartialPersonResponse response = new GetPartialPersonResponse();
        response.setPerson(
                personMapper.simplify(
                        personMapper.toSoapDTO(
                                personService.retrievePartialPersonInfoByEmail(request.getPersonEmail())
                        )
                )
        );

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFullPersonByEmailRequest")
    public GetFullPersonResponse getFullPersonInfoByEmail(@RequestPayload GetFullPersonByEmailRequest request) {

        GetFullPersonResponse response = new GetFullPersonResponse();
        response.setPerson(
                personMapper.toSoapDTO(
                        personService.retrieveFullPersonInfoByEmail(request.getPersonEmail())
                )
        );

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPartialPersonByNameSurnameRequest")
    public GetPartialPersonResponse getPartialPersonInfoByNameSurname(@RequestPayload GetPartialPersonByNameSurnameRequest request) {

        GetPartialPersonResponse response = new GetPartialPersonResponse();
        response.setPerson(
                personMapper.simplify(
                        personMapper.toSoapDTO(
                                personService.retrievePartialPersonInfoByNameSurname(request.getPersonName(), request.getPersonSurname())
                        )
                )
        );

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFullPersonByNameSurnameRequest")
    public GetFullPersonResponse getFullPersonInfoByNameSurname(@RequestPayload GetFullPersonByNameSurnameRequest request) {

        GetFullPersonResponse response = new GetFullPersonResponse();
        response.setPerson(
                personMapper.toSoapDTO(
                        personService.retrieveFullPersonInfoByNameSurname(request.getPersonName(), request.getPersonSurname())
                )
        );

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPartialPersonInfoListRequest")
    public GetPartialPersonInfoListResponse getPartialPersonInfoList(@RequestPayload GetPartialPersonInfoListRequest request) {

        List<PersonPartial> personPartialInfoList = personService.retrievePartialPersonInfoList(request.getPage(), request.getSize())
                .stream().map(personMapper::toSoapDTO).map(personMapper::simplify).collect(toList());

        GetPartialPersonInfoListResponse response = new GetPartialPersonInfoListResponse();
        response.getPersonList().addAll(personPartialInfoList);

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFullPersonInfoListRequest")
    public GetFullPersonInfoListResponse getFullPersonInfoList(@RequestPayload GetFullPersonInfoListRequest request) {

        List<PersonFull> personFullInfoList = personService.retrieveFullPersonInfoList(request.getPage(), request.getSize())
                .stream().map(personMapper::toSoapDTO).collect(toList());

        GetFullPersonInfoListResponse response = new GetFullPersonInfoListResponse();
        response.getPersonList().addAll(personFullInfoList);

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNewPersonRequest")
    public StatusResponse createNewPerson(@RequestPayload CreateNewPersonRequest request) {

        personService.createNewPerson(personMapper.toDTO(request.getPerson()));

        StatusResponse response = new StatusResponse();
        response.setId(request.getPerson().getId());
        response.setTimestamp(now().format(dateFormat));

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateExistingPersonRequest")
    public StatusResponse updatePerson(@RequestPayload UpdateExistingPersonRequest request) {

        personService.editPersonData(personMapper.toDTO(request.getPerson()), request.getPerson().getId());

        StatusResponse response = new StatusResponse();
        response.setId(request.getPerson().getId());
        response.setTimestamp(now().format(dateFormat));

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    public StatusResponse deletePerson(@RequestPayload DeletePersonRequest request) {

        personService.deletePersonById(request.getPersonId());

        StatusResponse response = new StatusResponse();
        response.setId(request.getPersonId());
        response.setTimestamp(now().format(dateFormat));

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "isExistsRequest")
    public IsExistsResponse isExisting(@RequestPayload IsExistsRequest request) {
        IsExistsResponse response = new IsExistsResponse();
        if (request.getPersonName() != null && request.getPersonSurname() != null && request.getPersonEmail() == null) {
            response.setSearchCriteria(request.getPersonName() + ", " + request.getPersonSurname());
            response.setIsExists(personService.exists(request.getPersonName(), request.getPersonSurname()));
        } else if (request.getPersonName() == null && request.getPersonSurname() == null && request.getPersonEmail() != null) {
            response.setSearchCriteria(request.getPersonEmail());
            response.setIsExists(personService.exists(request.getPersonEmail()));
        } else {
            throw new ValidationException(format("Invalid params: firstname : [%s], lastname : [%s], email : [%s]", request.getPersonName(),
                request.getPersonSurname(), request.getPersonEmail()));
        }

        response.setTimestamp(now().format(dateFormat));

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "personCountRequest")
    public PersonCountResponse personCount(@RequestPayload PersonCountRequest request) {
        PersonCountResponse response = new PersonCountResponse();
        if (request.getEmailProvider() != null) {
            response.setSearchCriteria(format("Person count by email provider [%s].", request.getEmailProvider()));
            response.setCount(personService.personCountByEmailProvider(request.getEmailProvider()));
        } else {
            response.setSearchCriteria(format("Person count.", request.getEmailProvider()));
            response.setCount(personService.personCount());
        }

        response.setTimestamp(now().format(dateFormat));

        return response;
    }
}
