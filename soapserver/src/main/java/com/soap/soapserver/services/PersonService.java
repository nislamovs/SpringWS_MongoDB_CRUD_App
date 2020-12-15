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

import static java.lang.String.format;
import static org.springframework.data.domain.PageRequest.of;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonsRepository personRepository;
    private final PersonMapper personMapper;

    @Transactional(readOnly = true)
    public PersonDAO retrievePersonById(String personId) {
        return personRepository.findById(personId)
            .orElseThrow(() -> new PersonNotFoundException(format("Person by id '%s' was not found", personId)));
    }

    @Transactional(readOnly = true)
    public PersonDAO retrieveFullPersonInfoById(String personId) {
        return personRepository.findFullInfoById(personId)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by id '%s' was not found", personId)));
    }

    @Transactional(readOnly = true)
    public Page<PersonDAO> retrievePersonList(int page, int size) {
        return personRepository.findAll(of(page, size));
    }

    @Transactional(readOnly = true)
    public PersonDAO retrievePersonByNameSurname(String firstname, String lastname) {
        return personRepository.findPersonByNameAndSurname(firstname, lastname)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by name, surname '%s, %s' was not found", firstname, lastname)));
    }

    @Transactional(readOnly = true)
    public PersonDAO retrievePersonByEmail(String email) {
        return personRepository.findPersonByEmail(email)
                .orElseThrow(() -> new PersonNotFoundException(format("Person by email '%s' was not found", email)));
    }



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
