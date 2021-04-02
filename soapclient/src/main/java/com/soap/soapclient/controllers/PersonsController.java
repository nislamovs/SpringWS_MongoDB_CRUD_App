package com.soap.soapclient.controllers;


import com.soap.soapclient.mappers.PersonMapper;
import com.soap.soapclient.services.PersonsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;

import java.util.stream.Collectors;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rest")
public class PersonsController {

    private final String PATH_PREFIX = "/api/v1/ws";
    private final String TARGET_NAMESPACE_PREFIX = "http://localhost:8082" + PATH_PREFIX;

    private final PersonsClient personService;
    private final PersonMapper personMapper;

    @GetMapping("/persons/{personId}")
    public ResponseEntity<?> getPersonsInfoById(@PathVariable("personId") @NotBlank String personId,
                                                @RequestParam(value = "fullInfo", defaultValue = "false") boolean isFullInfo) {

        return (isFullInfo) ? ok(personMapper.toRestDTO(personService.getFullPersonInfoById(personId)))
                            : ok(personMapper.toRestDTO(personService.getPartialPersonInfoById(personId)));

    }

    @GetMapping("/persons/email/{personEmail}")
    public ResponseEntity<?> getPersonsFullInfoByEmail(@PathVariable("personEmail") @NotBlank String personEmail,
                                                       @RequestParam(value = "fullInfo", defaultValue = "false") boolean isFullInfo) {

        return (isFullInfo) ? ok(personMapper.toRestDTO(personService.getFullPersonInfoByEmail(personEmail)))
                            : ok(personMapper.toRestDTO(personService.getPartialPersonInfoByEmail(personEmail)));
    }

    @GetMapping("/persons")
    public ResponseEntity<?> getPersonByNameAndSurname(@RequestParam("firstname") @NotBlank String firstname,
                                                       @RequestParam("lastname") @NotBlank String lastname,
                                                       @RequestParam(value = "fullInfo", defaultValue = "false") boolean isFullInfo) {

        return (isFullInfo) ? ok(personMapper.toRestDTO(personService.getFullPersonInfoByNameSurname(firstname, lastname)))
                : ok(personMapper.toRestDTO(personService.getPartialPersonInfoByNameSurname(firstname, lastname)));
    }

    @GetMapping("/persons/all")
    public ResponseEntity<?> getPersonsList(@RequestParam("page") @NotBlank int page,
                                            @RequestParam("size") @NotBlank int size,
                                            @RequestParam(value = "fullInfo", defaultValue = "false") boolean isFullInfo) {

        return isFullInfo ? ok(personService.getFullPersonInfoList(page, size).stream().map(personMapper::toRestDTO).collect(toList()))
                          : ok(personService.getPartialPersonInfoList(page, size).stream().map(personMapper::toRestDTO).collect(toList()));

    }

    ////////////////////


//    @PostMapping("/persons")
//    public ResponseEntity<?> createPerson(@RequestBody PersonDTO person) {
//
//        return ok(personMapper.toRestDTO(personService.createNewPerson(person)));
//    }
//
//    @PutMapping("/persons/{personId}")
//    public ResponseEntity<?> editPerson(@PathVariable("personId") @NotBlank String personId,
//                                        @RequestBody PersonDTO person) {
//
//        return ok(personMapper.toRestDTO(personService.editPersonData(person, personId)));
//    }
//
//    @DeleteMapping("/persons/{personId}")
//    public ResponseEntity<?> deletePerson(@PathVariable("personId") @NotBlank String personId) {
//        personService.deletePersonById(personId);
//
//        return ok(PersonDefaultResponseDTO.builder().id(personId).modifiedDate(now()).build());
//    }
}
