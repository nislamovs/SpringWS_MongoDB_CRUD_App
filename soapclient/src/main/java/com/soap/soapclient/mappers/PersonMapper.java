package com.soap.soapclient.mappers;

import com.soap.soapclient.domain.dto.PersonDTO;
import com.soap.soapclient.wsdl.Person;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface PersonMapper {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "StringToInstant")
    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "StringToInstant")
    PersonDTO toRestDTO(Person person);

    @Named("StringToInstant")
    default Instant convertStringToInstant(String dateTime) {
        LocalDateTime ldt = LocalDateTime.parse( dateTime , dateFormat );

        return ldt.atZone(ZoneId.systemDefault()).toInstant();
    }
}
