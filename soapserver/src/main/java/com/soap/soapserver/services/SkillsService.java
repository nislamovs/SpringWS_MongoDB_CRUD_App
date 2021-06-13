package com.soap.soapserver.services;

import com.soap.soapserver.domain.dto.SkillSetUpdatedDTO;
import com.soap.soapserver.repository.MongoOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static java.time.Instant.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class SkillsService {

    private final MongoOperations mongoOperations;

    @Transactional
    public SkillSetUpdatedDTO updateSkillValue(String personEmail, String skillName, int skillValue) {
        mongoOperations.updateSkillValue(personEmail, skillName, skillValue);

        return SkillSetUpdatedDTO.builder().email(personEmail).newValue(skillName+'['+skillValue+']').modifiedDate(now()).build();
    }
}
