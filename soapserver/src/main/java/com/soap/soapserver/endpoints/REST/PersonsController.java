package com.soap.soapserver.endpoints.REST;


import com.soap.soapserver.converters.mappers.PersonMapper;
import com.soap.soapserver.domain.dto.AbstractDTO;
import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.domain.dto.PersonDefaultResponseDTO;
import com.soap.soapserver.domain.dto.PersonSpecialInfoDTO;
import com.soap.soapserver.services.PersonService;
import java.time.LocalDateTime;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.badRequest;
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
        return (personService.deletePersonById(personId))
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(AbstractDTO.builder().modifiedDate(now()).id(personId).build())  //not deleted
                : ok(AbstractDTO.builder().modifiedDate(now()).id(personId).build());                                                           //deleted
    }

    @GetMapping("/persons/info")
    public ResponseEntity<?> isExisting(@RequestParam(value = "firstname", required = false) String firstname,
                                        @RequestParam(value = "lastname", required = false)  String lastname,
                                        @RequestParam(value = "email", required = false) String email) {

        if (firstname != null && lastname != null && email == null) {
            return ok(PersonSpecialInfoDTO.builder()
                .searchParam(firstname + ", " + lastname)
                .isExisting(personService.exists(firstname, lastname))
                .timestamp(LocalDateTime.now().toString())
                .build());
        } else if (firstname == null && lastname == null && email != null) {
            return ok(PersonSpecialInfoDTO.builder()
                .searchParam(email)
                .isExisting(personService.exists(email))
                .timestamp(LocalDateTime.now().toString())
                .build());
        } else {
            throw new ValidationException(format("Invalid params: firstname : [%s], lastname : [%s], email : [%s]", firstname, lastname, email));
        }
    }

    @GetMapping("/persons/count")
    public ResponseEntity<?> personCount(@RequestParam(value = "domainName", required = false) String domainName) {

        if (domainName != null) {
            return ok(PersonSpecialInfoDTO.builder()
                .searchParam(format("Person count by email provider [%s].", domainName))
                .count(personService.personCountByEmailProvider(domainName))
                .timestamp(LocalDateTime.now().toString())
                .build());
        } else {
            return ok(PersonSpecialInfoDTO.builder()
                .searchParam("Person count.")
                .count(personService.personCount())
                .timestamp(LocalDateTime.now().toString())
                .build());
        }
    }

}
