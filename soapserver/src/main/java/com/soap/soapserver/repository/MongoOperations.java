package com.soap.soapserver.repository;

import com.mongodb.BasicDBObject;
import com.soap.soapserver.domain.dto.StatsDTO;
import com.soap.soapserver.domain.exceptions.PersonNotFoundException;
import com.soap.soapserver.domain.exceptions.SkillNotFoundException;
import com.soap.soapserver.domain.exceptions.StatNotFoundException;
import com.soap.soapserver.models.PersonDAO;
import com.soap.soapserver.models.QuestStatusDAO;
import com.soap.soapserver.models.SkillSetDAO;
import com.soap.soapserver.models.StatsDAO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static java.lang.String.format;
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

    public Long updateQuestStatus(String personEmail, String questName) {

        Query personQuery = Query.query(Criteria.where("email").is(personEmail));
        PersonDAO person =  mongo.findOne(personQuery, PersonDAO.class);
        if (person == null)
            throw new PersonNotFoundException(format("Person by email [%s] was not found.", personEmail));

        Query questStatusQuery = Query.query(Criteria.where("_id").is(person.getQuestStatus().getId()));
        Update updateQuestStatusQuery = new Update().set(questName, true);

        return mongo.updateMulti(questStatusQuery, updateQuestStatusQuery, QuestStatusDAO.class).getModifiedCount();
    }

    public Long updateSkillValue(String personEmail, String skillName, int skillValue) {

        Query personQuery = Query.query(Criteria.where("email").is(personEmail));
        PersonDAO person =  mongo.findOne(personQuery, PersonDAO.class);
        if (person == null)
            throw new PersonNotFoundException(format("Person by email [%s] was not found.", personEmail));

        Query skillsQuery = Query.query(Criteria.where("_id").is(person.getSkillSet().getId()));
        Update updateSkillValueQuery = new Update();

        try {
            updateSkillValueQuery.set(SkillSetDAO.class.getField(skillName).getName(), skillValue);
        } catch (NoSuchFieldException e) {
            throw new SkillNotFoundException(format("Skill by name [%s] was not found.", skillName));
        }

        return mongo.updateMulti(skillsQuery, updateSkillValueQuery, SkillSetDAO.class).getModifiedCount();
    }

    public Long updateStatsValue(String personEmail, String statsName, int statsValue) {

        Query personQuery = Query.query(Criteria.where("email").is(personEmail));
        PersonDAO person =  mongo.findOne(personQuery, PersonDAO.class);
        if (person == null)
            throw new PersonNotFoundException(format("Person by email [%s] was not found.", personEmail));

        Query statsQuery = Query.query(Criteria.where("_id").is(person.getStats().getId()));
        Update updateStatsValueQuery = new Update();

        try {
            updateStatsValueQuery.set(StatsDAO.class.getField(statsName).getName(), statsValue);
        } catch (NoSuchFieldException e) {
            throw new StatNotFoundException(format("Stat by name [%s] was not found.", statsName));
        }

        return mongo.updateMulti(statsQuery, updateStatsValueQuery, StatsDAO.class).getModifiedCount();
    }
}
