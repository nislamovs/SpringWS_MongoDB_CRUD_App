package com.soap.soapclient.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soap.soapclient.domain.enums.Perks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDTO extends AbstractDTO {

    private String name;
    private String surname;
    private String phone;
    private String email;
    private Address address;
    private QuestStatusDTO questStatus = new QuestStatusDTO();
    private SkillSetDTO skillSet = new SkillSetDTO();
    private StatsDTO stats = new StatsDTO();
    private Set<Perks> perksSet;
}
