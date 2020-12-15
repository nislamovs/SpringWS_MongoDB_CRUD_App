package com.soap.soapserver.endpoints.REST;


import com.soap.soapserver.converters.mappers.PersonMapper;
import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.domain.dto.PersonDefaultResponseDTO;
import com.soap.soapserver.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.time.Instant.now;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rest")
public class PersonsController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @GetMapping("/persons/{personId}")
    public ResponseEntity<?> getPersonsInfoById(@PathVariable("personId") @NotBlank String personId) {
        return ok(personMapper.toDTO(personService.retrievePersonById(personId)));
    }

    @GetMapping("/persons/{personEmail}")
    public ResponseEntity<?> getPersonsFullInfoByEmail(@PathVariable("personEmail") @NotBlank String personEmail,
                                                       @RequestParam(value = "fullInfo", required = false) @NotBlank boolean isFullInfo) {

        return isFullInfo ? ok(personMapper.toDTO(personService.retrieveFullPersonInfoById(personEmail)))
                          : ok(personMapper.toDTO(personService.retrievePersonByEmail(personEmail)));
    }

    @GetMapping("/persons/all")
    public ResponseEntity<?> getPersonsList(@RequestParam("page") @NotBlank int page,
                                            @RequestParam("size") @NotBlank int size ) {

        return ok(personService.retrievePersonList(page, size).stream().map(personMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/persons")
    public ResponseEntity<?> getPersonByNameAndSurname(@RequestParam("firstname") @NotBlank String firstname,
                                                       @RequestParam("lastname") @NotBlank String lastname ) {

        return ok(personMapper.toDTO(personService.retrievePersonByNameSurname(firstname, lastname)));
    }

    @PostMapping("/persons")
    public ResponseEntity<?> createPerson(@RequestBody PersonDTO person) {

        return ok(personMapper.toDTO(personService.createNewPerson(person)));
    }

    @PutMapping("/persons/{personId}")
    public ResponseEntity<?> editPerson(@PathVariable("personId") @NotBlank String personId,
                                        @RequestBody PersonDTO person) {

        return ok(personMapper.toDTO(personService.editPersonData(person, personId)));
    }

    @DeleteMapping("/persons/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable("personId") @NotBlank String personId) {
        personService.deletePersonById(personId);

        return ok(PersonDefaultResponseDTO.builder().id(personId).modifiedDate(now()).build());
    }
}
