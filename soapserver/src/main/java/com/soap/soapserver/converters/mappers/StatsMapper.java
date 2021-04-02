package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.StatsDTO;
import com.soap.soapserver.models.StatsDAO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface StatsMapper {

    @Mapping(source = "id", target = "id")
    StatsDTO toDTO(StatsDAO stats);

    @Mapping(source = "id", target = "id")
    StatsDAO toDAO(StatsDTO statsDTO);
}
