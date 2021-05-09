package com.soap.soapserver.repository;

import com.soap.soapserver.models.StatsDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "statsRepo")
public interface StatsRepository extends MongoRepository<StatsDAO, String> {


    @Query(value = "{totalCount: 0}")
    Optional<StatsDAO> updateStatsValue(String email, String statName, String statValue);

}
