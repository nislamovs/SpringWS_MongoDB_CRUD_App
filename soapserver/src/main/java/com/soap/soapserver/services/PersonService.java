package com.soap.soapserver.services;

import com.soap.soapserver.converters.mappers.PersonMapper;
import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.domain.exceptions.PersonNotFoundException;
import com.soap.soapserver.models.PersonDAO;
import com.soap.soapserver.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.data.domain.PageRequest.of;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonsRepository personRepository;
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
        System.out.println("Partial >>>   "+size + "  "+ page);
        return personRepository.findAllPartialPersonInfo(page*size, size);
    }

    @Transactional(readOnly = true)
    public List<PersonDAO> retrieveFullPersonInfoList(int page, int size) {
        System.out.println("Full >>>   "+size + "  "+ page);
        return personRepository.findAllFullPersonInfo(page*size, size);
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
    ///////////////////////



    @Transactional
    public PersonDAO createNewPerson(PersonDTO personDTO) {
        return personRepository.save(personMapper.toDAO(personDTO));
    }

    @Transactional
    public PersonDAO editPersonData(PersonDTO personDTO, String personId) {
        return personRepository.save(personMapper.toDAO(personDTO).toBuilder().id(personId).build());
    }

    @Transactional
    public void deletePersonById(String personId) {
        personRepository.deleteById(personId);
    }
}
