package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.models.PersonDAO;
import https.localhost._8443.api.v1.ws.persons.PersonFull;
import https.localhost._8443.api.v1.ws.persons.PersonPartial;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface PersonMapper {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Mapping(source = "id", target = "id")
    PersonDTO toDTO(PersonDAO person);

    @Mapping(source = "id", target = "id")
    PersonDAO toDAO(PersonDTO personDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "InstantToString")
    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "InstantToString")
    PersonFull toSoapDTO(PersonDAO person);

    @Mapping(source = "id", target = "id")
    PersonPartial simplify(PersonFull personFull);

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "date", target = "date", qualifiedByName = "DateToLocalDate")
//    SubjectMarkDTO toDTO(SubjectMarkDAO subjectMarkDAO);
//
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "date", target = "date", qualifiedByName = "LocalDateToDate")
//    SubjectMarkDAO toDAO(SubjectMarkDTO subjectMarkDTO);
//
//    @Named("DateToLocalDate")
//    default LocalDate convertDateToLocalDate(Date value) {
//        return Instant.ofEpochMilli(value.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
//    }
//
    @Named("InstantToString")
    default String convertInstantToString(Instant instant) {
        return dateFormat.format(instant);
    }
}
