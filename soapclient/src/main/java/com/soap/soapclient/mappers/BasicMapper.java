package com.soap.soapclient.mappers;


import com.soap.soapclient.domain.dto.PersonDTO;
import com.soap.soapclient.domain.dto.QuestStatusDTO;
import com.soap.soapclient.domain.dto.SkillSetDTO;
import com.soap.soapclient.domain.dto.StatsDTO;
import com.soap.soapclient.wsdl.PersonPartial;
import com.soap.soapclient.wsdl.QuestStatus;
import com.soap.soapclient.wsdl.SkillSet;
import com.soap.soapclient.wsdl.Stats;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public interface BasicMapper {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Named("StringToInstant")
    default Instant convertStringToInstant(String dateTime) {
        LocalDateTime ldt = LocalDateTime.parse( dateTime , dateFormat );

        return ldt.atZone(ZoneId.systemDefault()).toInstant();
    }

    @Named("IdFormatConverter")
    default String convertIdStringVal(String idValue) {
        return idValue.replace("ObjectId","")
                .replace("(","")
                .replace(")","")
                .replace("\"","");

    }

    @Mapping(source = "id", target = "id", qualifiedByName = "IdFormatConverter")
    @Named("QuestStatusMapper")
    QuestStatusDTO QuestStatusToRestDTO(QuestStatus questStatus);

    @Mapping(source = "id", target = "id", qualifiedByName = "IdFormatConverter")
    @Named("SkillSetMapper")
    SkillSetDTO SkillSetToRestDTO(SkillSet questStatus);

    @Mapping(source = "id", target = "id", qualifiedByName = "IdFormatConverter")
    @Named("StatsMapper")
    StatsDTO StatsToRestDTO(Stats questStatus);

    @Mapping(source = "id", target = "id", qualifiedByName = "IdFormatConverter")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "StringToInstant")
    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "StringToInstant")
    PersonDTO toRestDTO(PersonPartial person);
}
