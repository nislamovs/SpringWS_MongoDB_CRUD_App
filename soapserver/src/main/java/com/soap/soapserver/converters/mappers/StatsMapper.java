package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.StatsDTO;
import com.soap.soapserver.models.StatsDAO;
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
public interface StatsMapper {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Mapping(source = "id", target = "id")
    StatsDTO toDTO(StatsDAO stats);

    @Named("statsConversion")
    @BeanMapping(nullValueMappingStrategy = RETURN_DEFAULT, qualifiedByName = "IdConversionToDao")
    @Mapping(source = "id", target = "id", qualifiedByName = "IdConversionToDao")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "setCreatedBy")
    @Mapping(source = "modifiedBy", target = "modifiedBy", qualifiedByName = "setModifiedBy")
    StatsDAO toDAO(StatsDTO statsDTO);

    @Named("IdConversionToDao")
    default String idValidator(String id) {
        if (id == null || id.isEmpty())
              return format("ObjectId(\"%s\")", new ObjectId().toString());
        if (!id.startsWith("ObjectId"))
            return format("ObjectId(\"%s\")", id);

        return id;
    }

    @Named("InstantToString")
    default String convertInstantToString(Instant instant) {
        System.out.println(instant);
        System.out.println(dateFormat.format(Instant.now()));
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
