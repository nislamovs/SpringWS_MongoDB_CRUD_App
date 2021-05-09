package com.soap.soapserver.repository;

import com.soap.soapserver.models.SkillSetDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "skillsRepo")
public interface SkillsRepository extends MongoRepository<SkillSetDAO, String> {

    @Query(value = "{totalCount: 0}")
    Optional<SkillSetDAO> updateSkillValue(String personEmail, String skillName, int skillValue);

}