package com.soap.soapserver.services;

import com.soap.soapserver.domain.dto.QuestStatusUpdatedDTO;
import com.soap.soapserver.domain.exceptions.QuestNotFoundException;
import com.soap.soapserver.repository.QuestsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static java.time.Instant.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestsService {

    private final QuestsRepository questsRepository;

    @Transactional
    public QuestStatusUpdatedDTO markQuestAsPassed(String personEmail, String questName) {
        questsRepository.markQuestAsPassed(personEmail, questName)
                .orElseThrow(() -> new QuestNotFoundException(format("Quest by name '%s' was not found", questName)));

        return QuestStatusUpdatedDTO.builder().email(personEmail).newValue(questName + '[' + "PASSED" + ']').modifiedDate(now()).build();
    }
}
