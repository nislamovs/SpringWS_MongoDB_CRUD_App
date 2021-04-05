package com.soap.soapclient.mappers;


import com.soap.soapclient.domain.dto.*;
import com.soap.soapclient.wsdl.*;
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

    @Mapping(source = "id", target = "id", qualifiedByName = "IdFormatConverter")
    @Mapping(source = "timestamp", target = "modifiedDate", qualifiedByName = "StringToInstant")
    AbstractDTO toRestDTO(StatusResponse statusResponse);


    ////////////////////////////////////////////////////////////////////////////////////////



    @Named("QuestStatusSoapMapper")
    @Mapping(source = "id", target = "id",  ignore = false)
    @Mapping(source = "createdDate", target = "createdDate",  ignore = false)
    @Mapping(source = "modifiedDate", target = "modifiedDate",  ignore = false)
    @Mapping(source = "createdBy", target = "createdBy",  ignore = false)
    @Mapping(source = "modifiedBy", target = "modifiedBy",  ignore = false)
    QuestStatus map(QuestStatusDTO questStatus);

    @Named("SkillSetSoapMapper")
    @Mapping(source = "id", target = "id",  ignore = false)
    @Mapping(source = "createdDate", target = "createdDate",  ignore = false)
    @Mapping(source = "modifiedDate", target = "modifiedDate",  ignore = false)
    @Mapping(source = "createdBy", target = "createdBy",  ignore = false)
    @Mapping(source = "modifiedBy", target = "modifiedBy",  ignore = false)
    SkillSet map(SkillSetDTO questStatus);

    @Named("StatsSoapMapper")
    @Mapping(source = "id", target = "id",  ignore = false)
    @Mapping(source = "createdDate", target = "createdDate",  ignore = false)
    @Mapping(source = "modifiedDate", target = "modifiedDate",  ignore = false)
    @Mapping(source = "createdBy", target = "createdBy",  ignore = false)
    @Mapping(source = "modifiedBy", target = "modifiedBy",  ignore = false)
    Stats map(StatsDTO questStatus);
}
