package com.soap.soapclient.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SkillSetUpdatedDTO extends AbstractDTO {

    private String email;
    private String newValue;
}
