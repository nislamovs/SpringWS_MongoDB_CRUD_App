package com.soap.soapserver.services;

import com.soap.soapserver.domain.dto.StatsUpdatedDTO;
import com.soap.soapserver.domain.exceptions.StatNotFoundException;
import com.soap.soapserver.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;
import static java.time.Instant.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatsService {

    private final StatsRepository statsRepository;

    @Transactional
    public StatsUpdatedDTO updateStatsValue(String email, String statName, String statValue) {
        statsRepository.updateStatsValue(email, statName, statValue)
                .orElseThrow(() -> new StatNotFoundException(format("Stat by name '%s' was not found", statName)));

        return StatsUpdatedDTO.builder().email(email).newValue(statName+'['+statValue+']').modifiedDate(now()).build();
    }
}
