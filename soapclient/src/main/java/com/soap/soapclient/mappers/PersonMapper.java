package com.soap.soapclient.mappers;

import com.soap.soapclient.domain.dto.PersonDTO;
import com.soap.soapclient.wsdl.PersonFull;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface PersonMapper extends BasicMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "IdFormatConverter")
    @Mapping(source = "questStatus", target = "questStatus", qualifiedByName = "QuestStatusMapper")
    @Mapping(source = "skillSet", target = "skillSet", qualifiedByName = "SkillSetMapper")
    @Mapping(source = "stats", target = "stats", qualifiedByName = "StatsMapper")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "StringToInstant")
    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "StringToInstant")
    PersonDTO toRestDTO(PersonFull person);


    @Mapping(source = "questStatus", target = "questStatus", qualifiedByName = "QuestStatusSoapMapper")
    @Mapping(source = "skillSet", target = "skillSet", qualifiedByName = "SkillSetSoapMapper")
    @Mapping(source = "stats", target = "stats", qualifiedByName = "StatsSoapMapper")
    @Mapping(source = "id", target = "id",  ignore = false)
    @Mapping(source = "createdDate", target = "createdDate",  ignore = false)
    @Mapping(source = "modifiedDate", target = "modifiedDate",  ignore = false)
    @Mapping(source = "createdBy", target = "createdBy",  ignore = false)
    @Mapping(source = "modifiedBy", target = "modifiedBy",  ignore = false)
    PersonFull toSoapDTO(PersonDTO person);
}
