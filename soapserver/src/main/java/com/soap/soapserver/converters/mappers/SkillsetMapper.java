package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.SkillSetDTO;
import com.soap.soapserver.models.SkillSetDAO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SkillsetMapper {

    @Mapping(source = "id", target = "id")
    SkillSetDTO toDTO(SkillSetDAO skillSet);

    @Mapping(source = "id", target = "id")
    SkillSetDAO toDAO(SkillSetDTO skillSetDTO);
}
