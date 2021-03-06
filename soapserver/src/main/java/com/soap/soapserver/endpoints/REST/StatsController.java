package com.soap.soapserver.endpoints.REST;


import com.soap.soapserver.services.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rest")
public class StatsController {

    private final StatsService statsService;

    @PutMapping("/persons/stats")
    public ResponseEntity<?> updateStats(@RequestParam("email") @NotBlank String email,
                                         @RequestParam("statName") @NotBlank String statName,
                                         @RequestParam("statValue") @NotBlank String statValue) {
        return ok(statsService.updateStatsValue(email, statName, statValue));
    }
}
