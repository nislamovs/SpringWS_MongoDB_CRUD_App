package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.StatsDTO;
import com.soap.soapserver.models.StatsDAO;
import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface StatsMapper {

    @Mapping(source = "id", target = "id")
    StatsDTO toDTO(StatsDAO stats);

    @BeanMapping(nullValueMappingStrategy = RETURN_DEFAULT, qualifiedByName = "IdConversionToDao")
    @Mapping(source = "id", target = "id", qualifiedByName = "IdConversionToDao")
    @Named("statsConversion")
    StatsDAO toDAO(StatsDTO statsDTO);

    @Named("IdConversionToDao")
    default String idValidator(String id) {
        return (id == null || id.isEmpty()) ? format("ObjectId(\"%s\")", new ObjectId().toString()) : id;
    }
}
