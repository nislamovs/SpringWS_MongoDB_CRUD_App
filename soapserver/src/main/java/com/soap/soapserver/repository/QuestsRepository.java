package com.soap.soapserver.repository;

import com.soap.soapserver.models.QuestStatusDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "questsRepo")
public interface QuestsRepository extends MongoRepository<QuestStatusDAO, String> {

    @Query(value = "{totalCount: 0}")
    Optional<QuestStatusDAO> markQuestAsPassed(String personEmail, String questName);
}
