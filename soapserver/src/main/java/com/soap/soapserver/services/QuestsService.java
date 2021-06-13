package com.soap.soapserver.services;

import com.soap.soapserver.domain.dto.QuestStatusUpdatedDTO;
import com.soap.soapserver.repository.MongoOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestsService {

    private final MongoOperations mongoOperations;

    @Transactional
    public QuestStatusUpdatedDTO markQuestAsPassed(String personEmail, String questName) {
        mongoOperations.updateQuestStatus(personEmail, questName);

        return QuestStatusUpdatedDTO.builder().email(personEmail).questName(questName).status("updated").build();
    }
}
