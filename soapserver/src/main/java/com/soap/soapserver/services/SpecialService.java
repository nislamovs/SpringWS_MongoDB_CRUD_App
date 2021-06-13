package com.soap.soapserver.services;

import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.repository.FuncRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ScriptOperations;
import org.springframework.data.mongodb.core.script.ExecutableMongoScript;
import org.springframework.data.mongodb.core.script.NamedMongoScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SpecialService {

    private MongoOperations mongoOperations;
    private final FuncRepository funcRepository;
    private final MongoTemplate template;

    @Transactional
    public void editPersonData(PersonDTO personDTO, String personId) {

        System.out.println("asdasdad");
        funcRepository.findAll().stream().forEach(System.out::println);

        ScriptOperations scriptOps = template.scriptOps();
        try {
            String z1 = (String) scriptOps.call("echo", "execute script via name");
            System.out.println("z1--->" +z1);
        } catch (Exception e) {

        }

// Execute script directly
        ExecutableMongoScript echoScript = new ExecutableMongoScript("function(x) { return x; }");
        String ss = (String) scriptOps.execute(echoScript, "directly execute script");
        System.out.println("ss--->" +ss);


// Register script and call it later
        scriptOps.register(new NamedMongoScript("echo", echoScript));
        String z2 = (String) scriptOps.call("echo", "execute script via name");
        System.out.println("z2--->" +z2);


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
}
