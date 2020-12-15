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

    private boolean findJohn = false;
    private boolean killMary = false;
    private boolean steelCar = false;
    private boolean casinoRobbery = false;
    private boolean steelPainting = false;
}
