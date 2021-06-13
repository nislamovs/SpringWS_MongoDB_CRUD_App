package com.soap.soapserver.repository;

import com.soap.soapserver.models.FuncDAO;
import com.soap.soapserver.models.StatsDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "system.js")
public interface FuncRepository extends MongoRepository<FuncDAO, String> {


    @Query(value = "{totalCount: 0}")
    Optional<FuncDAO> updateStatsValue(String email, String statName, String statValue);

}
