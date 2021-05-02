package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.SkillSetDTO;
import com.soap.soapserver.models.SkillSetDAO;
import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SkillsetMapper {

    @Mapping(source = "id", target = "id")
    SkillSetDTO toDTO(SkillSetDAO skillSet);

    @BeanMapping(nullValueMappingStrategy = RETURN_DEFAULT, qualifiedByName = "IdConversionToDao")
    @Mapping(source = "id", target = "id", qualifiedByName = "IdConversionToDao")
    @Named("skillsetConversion")
    SkillSetDAO toDAO(SkillSetDTO skillSetDTO);

    @Named("IdConversionToDao")
    default String idValidator(String id) {
        return (id == null || id.isEmpty()) ? format("ObjectId(\"%s\")", new ObjectId().toString()) : id;
    }
}
