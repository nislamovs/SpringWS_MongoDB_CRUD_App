package com.soap.soapclient.mappers;

import com.soap.soapclient.domain.dto.StatsDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface StatsMapper {

//    @Mapping(source = "id", target = "id")
//    StatsDTO toRestDTO(Stats stats);

}
