package com.soap.soapserver.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillSetDTO extends AbstractDTO {

    private int gambling;
    private int pickpocketing;
    private int lockpicking;
    private int technology;
    private int martialArts;
    private int lightWeapons;
    private int heavyWeapons;
    private int barter;
    private int speechcraft;
}
