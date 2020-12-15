package com.soap.soapserver.repository;


import com.soap.soapserver.models.PersonDAO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonsRepository extends MongoRepository<PersonDAO, String> {

    @Query(value = "{firstname: ?0, lastname: ?1}")
    Optional<PersonDAO> findPersonByNameAndSurname(String firstname, String lastname);

    @Query("{ 'email' : ?0 }")
    Optional<PersonDAO> findPersonByEmail(String email);

    @Aggregation(pipeline = {
            "{$lookup: {from: \"quests\", localField: \"questStatus\",  foreignField: \"_id\",    as: \"questStatus\"}}",
            "{$unwind: {path: \"$questStatus\", preserveNullAndEmptyArrays: true}}",
            "{$lookup: {from: \"skills\", localField: \"skillSet\",     foreignField: \"_id\",    as: \"skillSet\"}}",
            "{$unwind: {path: \"$skillSet\", preserveNullAndEmptyArrays: true}}",
            "{$lookup: {from: \"stats\",  localField: \"stats\",        foreignField: \"_id\",    as: \"stats\"}}",
            "{$unwind: {path: \"$stats\", preserveNullAndEmptyArrays: true}}",
            "{$match : {_id: 'ObjectId(\"?0\")'}}"
    })
    Optional<PersonDAO> findFullInfoById(String id);
}
