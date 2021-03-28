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

    private Integer gambling;
    private Integer pickpocketing;
    private Integer lockpicking;
    private Integer technology;
    private Integer martialArts;
    private Integer lightWeapons;
    private Integer heavyWeapons;
    private Integer barter;
    private Integer speechcraft;
}
