package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.QuestStatusDTO;
import com.soap.soapserver.models.QuestStatusDAO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface QuestStatusMapper {

    @Mapping(source = "id", target = "id")
    QuestStatusDTO toDTO(QuestStatusDAO questStatus);

    @Mapping(source = "id", target = "id")
    QuestStatusDAO toDAO(QuestStatusDTO questStatusDTO);
}
