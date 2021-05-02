package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.QuestStatusDTO;
import com.soap.soapserver.models.QuestStatusDAO;
import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface QuestStatusMapper {

    @Mapping(source = "id", target = "id")
    QuestStatusDTO toDTO(QuestStatusDAO questStatus);

    @BeanMapping(nullValueMappingStrategy = RETURN_DEFAULT, qualifiedByName = "IdConversionToDao")
    @Mapping(source = "id", target = "id", qualifiedByName = "IdConversionToDao")
    @Named("questStatusConversion")
    QuestStatusDAO toDAO(QuestStatusDTO questStatusDTO);

    @Named("IdConversionToDao")
    default String idValidator(String id) {
        return (id == null || id.isEmpty()) ? format("ObjectId(\"%s\")", new ObjectId().toString()) : id;
    }
}
