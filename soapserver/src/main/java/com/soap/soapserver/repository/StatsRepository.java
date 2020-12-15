package com.soap.soapserver.repository;

import com.soap.soapserver.models.Stats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatsRepository extends MongoRepository<Stats, String> {


    @Query(value = "{totalCount: 0}")
    Optional<Stats> updateStatsValue(String email, String statName, String statValue);

}
