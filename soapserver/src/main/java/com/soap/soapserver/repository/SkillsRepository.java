package com.soap.soapserver.repository;

import com.soap.soapserver.models.SkillSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsRepository extends MongoRepository<SkillSet, String> {

    @Query(value = "{totalCount: 0}")
    Optional<SkillSet> updateSkillValue(String personEmail, String skillName, int skillValue);

}