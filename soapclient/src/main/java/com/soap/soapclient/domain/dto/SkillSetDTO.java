package com.soap.soapclient.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
