package com.soap.soapserver.endpoints.SOAP;

import com.soap.soapserver.converters.mappers.PersonMapper;
import com.soap.soapserver.services.PersonService;
import https.localhost._8443.api.v1.ws.persons.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.addressing.server.annotation.Action;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class PersonsEndpoint {

//	private static final String NAMESPACE_URI = "https://localhost:8082/api/v1/ws/persons";
	private static final String NAMESPACE_URI = "https://localhost:8443/api/v1/ws/persons";

	private final PersonService personService;
	private final PersonMapper personMapper;

	//https://localhost:8443/api/v1/ws/persons/}getFullPersonByIdRequest

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

		System.out.println(response.getPerson().toString());
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

		System.out.println(response.getPerson().toString());
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

		System.out.println(response.getPerson().toString());
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

		System.out.println(response.getPerson().toString());
		return response;
	}


//
//
//	@ResponsePayload
//	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetFullPersonByEmailRequest")
//	public GetFullPersonResponse getFullPersonsInfoByEmail(@RequestPayload GetPersonByEmailRequest request) {
//		GetFullPersonResponse response = new GetFullPersonResponse();
//		response.setPerson(
//			personMapper.toSoapDTO(
//				personService.retrievePersonByEmail(request.getPersonEmail())
//			)
//		);
//
//		System.out.println(response.getPerson().toString());
//		return response;
//	}
}
