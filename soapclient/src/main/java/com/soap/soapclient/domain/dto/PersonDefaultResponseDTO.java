package com.soap.soapclient.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDefaultResponseDTO extends AbstractDTO {

    //This class is created because AbstractDTO is abstract :)

}
