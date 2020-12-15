package com.soap.soapclient.controllers;

import com.soap.soapclient.services.SkillsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

import static java.lang.Integer.parseInt;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rest")
public class SkillSetController {

    private final SkillsClient skillsService;

    @PutMapping("/persons/skillSet")
    public ResponseEntity<?> updateSkillValue(@RequestParam("email") @NotBlank String email,
                                              @RequestParam("skillName") @NotBlank String skillName,
                                              @RequestParam("skillValue") @NotBlank String skillValue) {

        return ok(skillsService.updateSkillValue(email, skillName, parseInt(skillValue)));
    }
}
