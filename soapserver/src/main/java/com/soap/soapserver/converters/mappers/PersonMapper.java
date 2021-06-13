package com.soap.soapserver.converters.mappers;

import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.models.PersonDAO;
import https.localhost._8443.api.v1.ws.persons.PersonFull;
import https.localhost._8443.api.v1.ws.persons.PersonPartial;
import https.localhost._8443.api.v1.ws.persons.Stats;
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
public interface PersonMapper extends QuestStatusMapper, SkillsetMapper, StatsMapper {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Mapping(source = "id", target = "id")
    PersonDTO toDTO(PersonDAO person);

    @BeanMapping(nullValueMappingStrategy = RETURN_DEFAULT, qualifiedByName = "IdConversionToDao")
    @Mapping(source = "id", target = "id", qualifiedByName = "IdConversionToDao")
    @Mapping(source = "questStatus", target = "questStatus", qualifiedByName = "questStatusConversion")
    @Mapping(source = "stats", target = "stats", qualifiedByName = "statsConversion")
    @Mapping(source = "skillSet", target = "skillSet", qualifiedByName = "skillsetConversion")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "setCreatedBy")
    @Mapping(source = "modifiedBy", target = "modifiedBy", qualifiedByName = "setModifiedBy")
    PersonDAO toDAO(PersonDTO personDTO);

    @Mapping(source = "id", target = "id", ignore = true)
    @Mapping(source = "createdDate", target = "createdDate", ignore = true)
    @Mapping(source = "modifiedDate", target = "modifiedDate", ignore = true)
    PersonDTO toDTO(PersonFull personFull);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdDate", target = "createdDate", qualifiedByName = "InstantToString")
    @Mapping(source = "modifiedDate", target = "modifiedDate", qualifiedByName = "InstantToString")
    PersonFull toSoapDTO(PersonDAO person);

    @Mapping(source = "id", target = "id")
    PersonPartial simplify(PersonFull personFull);



//    void updatePersonDAO(PersonDAO newPerson, PersonDAO oldPerson);

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
    @Named("setCreatedBy")
    default String setCreatedBy(String createdBy) {
        return createdBy == null ? "Admin" : createdBy;
    }

    @Named("setModifiedBy")
    default String setModifiedBy(String modifiedBy) {
        return modifiedBy == null ? "Admin" : modifiedBy;
    }

    @Named("InstantToString")
    default String convertInstantToString(Instant instant) {
        System.out.println(instant);
        System.out.println(dateFormat.format(Instant.now()));
        return (instant == null) ? dateFormat.format(Instant.now()) : dateFormat.format(instant);
    }

    @Named("IdConversionToDao")
    default String idValidator(String id) {
        if (id == null || id.isEmpty())
            return format("ObjectId(\"%s\")", new ObjectId().toString());
        if (!id.startsWith("ObjectId"))
            return format("ObjectId(\"%s\")", id);

        return id;
    }
}
