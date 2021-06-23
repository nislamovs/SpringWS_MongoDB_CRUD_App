package com.soap.soapserver.endpoints.SOAP;

import static java.time.LocalDateTime.now;

import com.soap.soapserver.converters.mappers.StatsMapper;
import com.soap.soapserver.domain.dto.SkillSetUpdatedDTO;
import com.soap.soapserver.domain.dto.StatsUpdatedDTO;
import com.soap.soapserver.services.StatsService;

import https.localhost._8443.api.v1.ws.skills.UpdateSkillValueRequest;
import https.localhost._8443.api.v1.ws.skills.UpdateSkillValueResponse;
import https.localhost._8443.api.v1.ws.stats.UpdateStatValueRequest;
import https.localhost._8443.api.v1.ws.stats.UpdateStatValueResponse;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class StatsEndpoint {

//	private static final String NAMESPACE_URI = "https://localhost:8082/api/v1/ws/stats";
	private static final String NAMESPACE_URI = "https://localhost:8443/api/v1/ws/stats";

	private final StatsService statsService;
	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(
			ZoneId.systemDefault());

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateStatValueRequest")
	public UpdateStatValueResponse updateStats(@RequestPayload UpdateStatValueRequest request) {
		UpdateStatValueResponse response = new UpdateStatValueResponse();
		StatsUpdatedDTO result = statsService.updateStatsValue(request.getEmail(), request.getStatName(), request.getStatValue());

		response.setEmail(result.getEmail());
		response.setTimestamp(now().format(dateFormat));
		response.setNewValue(result.getNewValue());

		return response;
	}
}
