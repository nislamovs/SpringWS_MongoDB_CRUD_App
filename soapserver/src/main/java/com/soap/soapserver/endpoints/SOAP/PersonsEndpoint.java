package com.soap.soapserver.endpoints.SOAP;

import com.soap.soapserver.converters.mappers.PersonMapper;
import com.soap.soapserver.services.PersonService;
import https.localhost._8443.api.v1.ws.persons.GetPersonsRequest;
import https.localhost._8443.api.v1.ws.persons.GetPersonsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class PersonsEndpoint {

//	private static final String NAMESPACE_URI = "https://localhost:8082/api/v1/ws/persons";
	private static final String NAMESPACE_URI = "https://localhost:8443/api/v1/ws/persons";

	private final PersonService personService;
	private final PersonMapper personMapper;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonsRequest")
	public GetPersonsResponse getPersonsInfoById(@RequestPayload GetPersonsRequest request) {
		GetPersonsResponse response = new GetPersonsResponse();
		response.setPerson(personMapper.toSoapDTO(personService.retrieveFullPersonInfoById(request.getPersonId())));

		System.out.println(response.getPerson().toString());
		return response;
	}
}
