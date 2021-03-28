package com.soap.soapserver.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soap.soapserver.domain.enums.Perks;
import com.soap.soapserver.models.Address;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
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

    @Builder.Default
    private QuestStatusDTO questStatus = new QuestStatusDTO();
    @Builder.Default
    private SkillSetDTO skillSet = new SkillSetDTO();
    @Builder.Default
    private StatsDTO stats = new StatsDTO();

    private Set<Perks> perksSet;
}
