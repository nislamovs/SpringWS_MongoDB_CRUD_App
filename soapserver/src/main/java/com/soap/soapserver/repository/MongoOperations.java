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
    private final PersonsRepository personsRepository;

    public void deleteWithOrphans(String id) {

        Optional<PersonDAO> personToDelete = personsRepository.findFullPersonInfoById(id);

        if (personToDelete.isPresent()) {
            mongo.remove(query(where("_id").is(personToDelete.get().getQuestStatus().getId())), QuestStatusDAO.class);
            mongo.remove(query(where("_id").is(personToDelete.get().getSkillSet().getId())), SkillSetDAO.class);
            mongo.remove(query(where("_id").is(personToDelete.get().getStats().getId())), StatsDAO.class);
            mongo.remove(query(where("_id").is(personToDelete.get().getId())), PersonDAO.class);
        }
        //TODO exception handling
    }

//    public void deleteWithOrphans2(String id) {
//
//        //Also working example
//
//        Query query(where("_id").is(format("ObjectId(\"%s\")", id)).)
//
//        LookupOperation questsLookup = LookupOperation.newLookup()
//                .from("img")
//                .localField("_id")
//                .foreignField("_id")
//                .as("uniqueImgs");
//
//        LookupOperation skillsLookup = LookupOperation.newLookup()
//                .from("img")
//                .localField("_id")
//                .foreignField("_id")
//                .as("uniqueImgs");
//
//        LookupOperation statsLookup = LookupOperation.newLookup()
//                .from("img")
//                .localField("_id")
//                .foreignField("_id")
//                .as("uniqueImgs");
//
//        Aggregation agg = new Aggregation(
//                match(Criteria.where("idOfUser").is(loggedInAccount.getId())),
//                questsLookup, skillsLookup, statsLookup
//        );
//
//        AggregationResults aggregationResults = mongo.aggregate(agg, "comment", String.clas);
//    }
}
