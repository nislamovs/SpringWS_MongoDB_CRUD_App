package com.soap.soapserver.endpoints.REST;


import com.soap.soapserver.services.QuestsService;
import com.soap.soapserver.services.SkillsService;
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
public class QuestController {

    private final QuestsService questsService;

    @PutMapping("/persons/quests")
    public ResponseEntity<?> updateQuestStatus(@RequestParam("email") @NotBlank String email,
                                               @RequestParam("questName") @NotBlank String questName) {

        return ok(questsService.markQuestAsPassed(email, questName));
    }
}
