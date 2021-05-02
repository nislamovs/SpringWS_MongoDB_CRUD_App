package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.SkillSetDTO;
import com.soap.soapserver.models.SkillSetDAO;
import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.lang.String.format;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SkillsetMapper {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Mapping(source = "id", target = "id")
    SkillSetDTO toDTO(SkillSetDAO skillSet);

    @Named("skillsetConversion")
    @BeanMapping(nullValueMappingStrategy = RETURN_DEFAULT, qualifiedByName = "IdConversionToDao")
    @Mapping(source = "id", target = "id", qualifiedByName = "IdConversionToDao")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "InstantToString")
    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "InstantToString")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "setCreatedBy")
    @Mapping(source = "modifiedBy", target = "modifiedBy", qualifiedByName = "setModifiedBy")
    SkillSetDAO toDAO(SkillSetDTO skillSetDTO);

    @Named("IdConversionToDao")
    default String idValidator(String id) {
        return (id == null || id.isEmpty()) ? format("ObjectId(\"%s\")", new ObjectId().toString()) : id;
    }

    @Named("InstantToString")
    default String convertInstantToString(Instant instant) {
        return (instant == null) ? dateFormat.format(Instant.now()) : dateFormat.format(instant);
    }

    @Named("setCreatedBy")
    default String setCreatedBy(String createdBy) {
        return createdBy == null ? "Admin" : createdBy;
    }

    @Named("setModifiedBy")
    default String setModifiedBy(String modifiedBy) {
        return modifiedBy == null ? "Admin" : modifiedBy;
    }
}
