package com.soap.soapclient.controllers;


import com.soap.soapclient.mappers.PersonMapper;
import com.soap.soapclient.services.PersonsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static java.time.Instant.now;
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
    public ResponseEntity<?> getPersonsInfoById(@PathVariable("personId") @NotBlank String personId) {


        return ok(personMapper.toRestDTO(personService.getPersonById(personId).getPerson()));
    }

//    @GetMapping("/persons/{personEmail}")
//    public ResponseEntity<?> getPersonsFullInfoByEmail(@PathVariable("personEmail") @NotBlank String personEmail,
//                                                       @RequestParam(value = "fullInfo", required = false) @NotBlank boolean isFullInfo) {
//
//        return isFullInfo ? ok(personMapper.toRestDTO(personService.getFullPersonInfoById(personEmail)))
//                : ok(personMapper.toRestDTO(personService.getPersonByEmail(personEmail)));
//    }
//
//    @GetMapping("/persons")
//    public ResponseEntity<?> getPersonsList(@RequestParam("page") @NotBlank int page,
//                                            @RequestParam("size") @NotBlank int size) {
//
//        return ok(personService.getPersonList(page, size).stream().map(personMapper::toDTO).collect(Collectors.toList()));
//    }
//
//    @GetMapping("/persons")
//    public ResponseEntity<?> getPersonByNameAndSurname(@RequestParam("firstname") @NotBlank String firstname,
//                                                       @RequestParam("lastname") @NotBlank String lastname) {
//
//        return ok(personMapper.toRestDTO(personService.getPersonByNameAndSurname(firstname, lastname)));
//    }
//
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