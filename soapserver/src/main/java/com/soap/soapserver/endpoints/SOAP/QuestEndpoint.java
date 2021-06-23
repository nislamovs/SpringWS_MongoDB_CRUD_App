package com.soap.soapserver.endpoints.SOAP;

import static java.time.LocalDateTime.now;

import com.soap.soapserver.converters.mappers.QuestStatusMapper;
import com.soap.soapserver.domain.dto.QuestStatusUpdatedDTO;
import com.soap.soapserver.services.QuestsService;
import https.localhost._8443.api.v1.ws.quests.QuestUpdateRequest;
import https.localhost._8443.api.v1.ws.quests.QuestUpdateResponse;
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
public class QuestEndpoint {

//	private static final String NAMESPACE_URI = "https://localhost:8082/api/v1/ws/quests";
	private static final String NAMESPACE_URI = "https://localhost:8443/api/v1/ws/quests";

	private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(
			ZoneId.systemDefault());

	private final QuestsService questsService;

	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "questUpdateRequest")
	public QuestUpdateResponse updateQuestStatus(@RequestPayload QuestUpdateRequest request) {
		QuestUpdateResponse response = new QuestUpdateResponse();
		QuestStatusUpdatedDTO status = questsService.markQuestAsPassed(request.getEmail(), request.getQuestName());

		response.setQuestName(status.getQuestName());
		response.setStatus(status.getStatus());
		response.setEmail(status.getEmail());
		response.setTimestamp(now().format(dateFormat));

		return response;
	}
}
