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
public class StatsDTO extends AbstractDTO {

    private Integer strength;
    private Integer endurance;
    private Integer dexterity;
    private Integer intellect;
    private Integer attention;
    private Integer personality;
    private Integer luck;
}
