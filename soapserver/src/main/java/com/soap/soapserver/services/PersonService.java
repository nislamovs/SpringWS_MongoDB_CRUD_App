package com.soap.soapserver.services;

import com.mongodb.BasicDBObject;
import com.soap.soapserver.converters.mappers.PersonMapper;
import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.domain.exceptions.PersonNotFoundException;
import com.soap.soapserver.models.FuncDAO;
import com.soap.soapserver.models.PersonDAO;
import com.soap.soapserver.repository.FuncRepository;
import com.soap.soapserver.repository.MongoOperations;
import com.soap.soapserver.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.data.mongodb.core.script.NamedMongoScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonsRepository personRepository;
    private final MongoOperations mongoOps;
    private final MongoTemplate mongo;
    private final PersonMapper personMapper;

    @Transactional(readOnly = true)
    public PersonDAO retrievePartialPersonInfoById(String personId) {
        return personRepository.findPartialPersonInfoById(personId)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by id '%s' was not found", personId)));
    }

    @Transactional(readOnly = true)
    public PersonDAO retrieveFullPersonInfoById(String personId) {
        return personRepository.findFullPersonInfoById(personId)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by id '%s' was not found", personId)));
    }

    @Transactional(readOnly = true)
    public PersonDAO retrievePartialPersonInfoByEmail(String email) {
        return personRepository.findPartialPersonInfoByEmail(email)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by email '%s' was not found", email)));
    }

    @Transactional(readOnly = true)
    public PersonDAO retrieveFullPersonInfoByEmail(String email) {
        return personRepository.findFullPersonInfoByEmail(email)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by email '%s' was not found", email)));
    }

    @Transactional(readOnly = true)
    public List<PersonDAO> retrievePartialPersonInfoList(int page, int size) {
        return personRepository.findAllPartialPersonInfo(page * size, size);
    }

    @Transactional(readOnly = true)
    public List<PersonDAO> retrieveFullPersonInfoList(int page, int size) {
        return personRepository.findAllFullPersonInfo(page * size, size);
    }

    @Transactional(readOnly = true)
    public PersonDAO retrievePartialPersonInfoByNameSurname(String firstname, String lastname) {
        return personRepository.findPartialPersonInfoByNameAndSurname(firstname, lastname)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by name, surname '%s, %s' was not found", firstname, lastname)));
    }

    @Transactional(readOnly = true)
    public PersonDAO retrieveFullPersonInfoByNameSurname(String firstname, String lastname) {
        return personRepository.findFullPersonInfoByNameAndSurname(firstname, lastname)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by name, surname '%s, %s' was not found", firstname, lastname)));
    }

    @Transactional
    public PersonDTO createNewPerson(PersonDTO personDTO) {
        PersonDAO newPerson = personRepository.save(personMapper.toDAO(personDTO));
        return personMapper.toDTO(newPerson);
    }

    @Transactional
    public void editPersonData(PersonDTO personDTO, String personId) {

        PersonDAO oldPerson = personRepository.findFullPersonInfoById(personId)
                .orElseThrow(() ->  new PersonNotFoundException(format("Person by id [%s] not found.", personId)));

        personRepository.save(oldPerson);
//        mongo.save(personMapper.toDAO(personDTO), "persons");
//
//
//        mongoOps.
//        personRepository.save(oldPerson);
        //https://softwareengineering.stackexchange.com/questions/408145/spring-data-mongodb-update-document-based-on-multiple-identifiers-with-composit




//        Query query = new Query(new Criteria().andOperator(
//                Criteria.where("_id").is("myClassId"),
//                Criteria.where("contacts.collection.value").is("2"),
//                Criteria.where("contacts.collection._class").is("SubClass2"));
//
//        Update update = new Update();
//
//        update.set("contacts.0.collection.$.value", "3");
//        mongo.updateFirst(query, update, PersonDAO.class);

//        mongo.upda


//        Update updateDefinition = Update.fromDocument(personMapper.toDAO(personDTO).);
//        updateDefinition.g
//        mongo.updateFirst()


//        FuncDAO funcDAO = funcRepository.findById("addsers").get();
//        System.out.println(">>>>   "+funcDAO.getValue().getCode());
//
//        Document ssdd =mongoOperations.executeCommand("{addsers(3,2)}");
//        System.out.println(ssdd.toString());

//        Document document = new Document();
//        document.put("eval", "function() { return addNumb(17, 25); }");
//
//        mongoOperations.executeCommand(document);

//        personRepository.updateExistingPerson(personMapper.toDAO(personDTO).toBuilder().id(personId).build());

//        personRepository.findFullPersonInfoById(personId).ifPresent(personRepository::save);

//        personRepository.findFullPersonInfoById(personId).get().get


//        System.out.println(personMapper.toDAO(personDTO));
//        PersonDAO personDAO = personRepository.save(personMapper.toDAO(personDTO));
//        personRepository.save(personMapper.toDAO(personDTO).toBuilder().id(personId).build());
    }

    @Transactional
    public boolean deletePersonById(String personId) {
        personRepository.findFullPersonInfoById(personId)
                .ifPresentOrElse(personRepository::delete, () -> { throw new PersonNotFoundException(format("Person by id [%s] not found.", personId)); }) ;

        return !personRepository.existsById(personId);
    }

    @Transactional
    public boolean exists(String name, String surname) {
        return personRepository.existByNameSurname(name, surname);
    }

    @Transactional
    public boolean exists(String email) {
        return personRepository.existByEmail(email);
    }

    @Transactional
    public int personCount() {
        return personRepository.personsCount();
    }

    @Transactional
    public int personCountByEmailProvider(String emailProvider) {
        return personRepository.emailDomainCountByEmailProviderName(emailProvider);
    }
}
