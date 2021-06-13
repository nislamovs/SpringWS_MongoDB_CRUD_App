package com.soap.soapserver.services;

import com.soap.soapserver.domain.dto.StatsUpdatedDTO;
import com.soap.soapserver.repository.MongoOperations;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Integer.parseInt;
import static java.time.Instant.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatsService {

    private final MongoOperations mongoOperations;

    @Transactional
    public StatsUpdatedDTO updateStatsValue(String email, String statName, String statValue) {
        mongoOperations.updateStatsValue(email, statName, parseInt(statValue));

        return StatsUpdatedDTO.builder().email(email).newValue(statName+'['+statValue+']').modifiedDate(now()).build();
    }
}
