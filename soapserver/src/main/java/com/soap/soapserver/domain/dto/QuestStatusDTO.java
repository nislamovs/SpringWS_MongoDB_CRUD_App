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
public class QuestStatusDTO extends AbstractDTO {

    private Boolean findJohn;
    private Boolean killMary;
    private Boolean steelCar;
    private Boolean casinoRobbery;
    private Boolean steelPainting;
}
