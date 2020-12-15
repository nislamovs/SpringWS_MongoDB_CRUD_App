package com.soap.soapclient.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SkillsetMapper {

//    @Mapping(source = "id", target = "id")
//    SkillSetDTO toRestDTO(SkillSet skillSet);

}
