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

    private int strength;
    private int endurance;
    private int dexterity;
    private int intellect;
    private int attention;
    private int personality;
    private int luck;
}
