package com.soap.soapclient.mappers;

import com.soap.soapclient.domain.dto.QuestStatusDTO;
import com.soap.soapclient.wsdl.QuestStatus;
import org.mapstruct.*;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface QuestStatusMapper extends BasicMapper {

}
