package com.soap.soapserver.endpoints.REST;


import com.soap.soapserver.converters.mappers.PersonMapper;
import com.soap.soapserver.domain.dto.AbstractDTO;
import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.domain.dto.PersonDefaultResponseDTO;
import com.soap.soapserver.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import java.util.stream.Collectors;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rest")
public class PersonsController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping("/persons/{personId}")
    public ResponseEntity<?> getPersonsInfoById(@PathVariable("personId") @NotBlank String personId,
                                                @RequestParam(value = "fullInfo", defaultValue = "false") @NotBlank boolean isFullInfo) {

        return isFullInfo ? ok(personMapper.toDTO(personService.retrieveFullPersonInfoById(personId)))
                          : ok(personMapper.toDTO(personService.retrievePartialPersonInfoById(personId)));

    }

    @GetMapping("/persons/email/{personEmail}")
    public ResponseEntity<?> getPersonsInfoByEmail(@PathVariable("personEmail") @NotBlank String personEmail,
                                                   @RequestParam(value = "fullInfo", defaultValue = "false") @NotBlank boolean isFullInfo) {

        return isFullInfo ? ok(personMapper.toDTO(personService.retrieveFullPersonInfoByEmail(personEmail)))
                          : ok(personMapper.toDTO(personService.retrievePartialPersonInfoByEmail(personEmail)));
    }

    @GetMapping("/persons")
    public ResponseEntity<?> getPersonByNameAndSurname(@RequestParam("firstname") @NotBlank String firstname,
                                                       @RequestParam("lastname") @NotBlank String lastname,
                                                       @RequestParam(value = "fullInfo", defaultValue = "false") @NotBlank boolean isFullInfo) {

        return isFullInfo ? ok(personMapper.toDTO(personService.retrieveFullPersonInfoByNameSurname(firstname, lastname)))
                          : ok(personMapper.toDTO(personService.retrievePartialPersonInfoByNameSurname(firstname, lastname)));
    }

    @GetMapping("/persons/all")
    public ResponseEntity<?> getPersonsList(@RequestParam("page") @NotBlank int page,
                                            @RequestParam("size") @NotBlank int size,
                                            @RequestParam(value = "fullInfo", defaultValue = "false") @NotBlank boolean isFullInfo) {

        return isFullInfo ? ok(personService.retrieveFullPersonInfoList(page, size).stream().map(personMapper::toDTO).collect(toList()))
                          : ok(personService.retrievePartialPersonInfoList(page, size).stream().map(personMapper::toDTO).collect(toList()));
    }


    @PostMapping("/persons")
    public ResponseEntity<?> createPerson(@RequestBody PersonDTO person) {

        PersonDTO newPerson =  personService.createNewPerson(person);

        return ok(AbstractDTO.builder().createdDate(now()).id(newPerson.getId()).build());
    }

    @PutMapping("/persons/{personId}")
    public ResponseEntity<?> editPerson(@PathVariable("personId") @NotBlank String personId,
                                        @RequestBody PersonDTO person) {

        personService.editPersonData(person, personId);

        return ok(AbstractDTO.builder().modifiedDate(now()).id(personId).build());
    }

    @DeleteMapping("/persons/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable("personId") @NotBlank String personId) {
        personService.deletePersonById(personId);

        return ok(AbstractDTO.builder().modifiedDate(now()).id(personId).build());
    }
}
