package com.soap.soapserver.repository;

import com.soap.soapserver.models.PersonDAO;
import com.soap.soapserver.models.QuestStatusDAO;
import com.soap.soapserver.models.SkillSetDAO;
import com.soap.soapserver.models.StatsDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@Repository
@RequiredArgsConstructor
public class MongoOperations {

    private final MongoTemplate mongo;
    private final PersonsRepository personRepository;

    public void deleteWithOrphans(String id) {

        Optional<PersonDAO> personToDelete = personRepository.findFullPersonInfoById(id);

        if (personToDelete.isPresent()) {
            mongo.remove(query(where("_id").is(personToDelete.get().getQuestStatus().getId())), QuestStatusDAO.class);
            mongo.remove(query(where("_id").is(personToDelete.get().getSkillSet().getId())), SkillSetDAO.class);
            mongo.remove(query(where("_id").is(personToDelete.get().getStats().getId())), StatsDAO.class);
            mongo.remove(query(where("_id").is(personToDelete.get().getId())), PersonDAO.class);
        }
        //TODO exception handling
    }

    //Also working example
    public void deleteWithOrphans2(String id) {

        personRepository.findFullPersonInfoById(id).ifPresent(personRepository::delete);

        //TODO exception handling
    }
}
