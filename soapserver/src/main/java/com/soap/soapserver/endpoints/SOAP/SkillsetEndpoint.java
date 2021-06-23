package com.soap.soapserver.endpoints.SOAP;

import static java.time.LocalDateTime.now;

import com.soap.soapserver.converters.mappers.SkillsetMapper;
import com.soap.soapserver.domain.dto.SkillSetUpdatedDTO;
import com.soap.soapserver.services.SkillsService;

import https.localhost._8443.api.v1.ws.skills.UpdateSkillValueRequest;
import https.localhost._8443.api.v1.ws.skills.UpdateSkillValueResponse;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class SkillsetEndpoint {

//	private static final String NAMESPACE_URI = "https://localhost:8082/api/v1/ws/skills";
	private static final String NAMESPACE_URI = "https://localhost:8443/api/v1/ws/skills";
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(
			ZoneId.systemDefault());

	private final SkillsService skillsService;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateSkillValueRequest")
	public UpdateSkillValueResponse updateSkillValue(@RequestPayload UpdateSkillValueRequest request) {
		UpdateSkillValueResponse response = new UpdateSkillValueResponse();
		SkillSetUpdatedDTO result = skillsService.updateSkillValue(request.getEmail(), request.getSkillName(), request.getSkillValue());

		response.setEmail(result.getEmail());
		response.setTimestamp(now().format(dateFormat));
		response.setNewValue(result.getNewValue());

		return response;
	}
}
