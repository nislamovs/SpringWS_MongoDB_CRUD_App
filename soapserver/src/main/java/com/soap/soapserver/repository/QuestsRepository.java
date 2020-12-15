package com.soap.soapserver.repository;

import com.soap.soapserver.models.QuestStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestsRepository extends MongoRepository<QuestStatus, String> {

    @Query(value = "{totalCount: 0}")
    Optional<QuestStatus> markQuestAsPassed(String personEmail, String questName);
}
