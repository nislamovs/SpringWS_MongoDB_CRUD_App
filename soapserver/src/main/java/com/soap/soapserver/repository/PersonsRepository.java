package com.soap.soapserver.repository;


import com.soap.soapserver.domain.dto.PersonDTO;
import com.soap.soapserver.models.PersonDAO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository(value = "personsRepo")
public interface PersonsRepository extends MongoRepository<PersonDAO, String> {

    @Query(value = "{ '_id' : 'ObjectId(\"?0\")' }",
            fields = "{_id:1," +
                     " name:1," +
                     " surname:1," +
                     " phone:1," +
                     " email:1," +
                     " address:1," +
                     " createdDate:1," +
                     " createdBy:1," +
                     " modifiedDate:1," +
                     " modifiedBy:1}")
    Optional<PersonDAO> findPartialPersonInfoById(String id);

    @Aggregation(pipeline = {
//            "{$addFields: {quests: { $arrayElemAt: [{ $objectToArray: '$quests' }, 1] }, stats: { $arrayElemAt: [{ $objectToArray: '$stats' }, 1] }, skillSet: { $arrayElemAt: [{ $objectToArray: '$skillSet' }, 1] } } }",
//            "{$addFields: {quests: '$quests.v', stats: '$stats.v', skillSet: '$skillSet.v'} }",
//
//            "{$lookup: {from: quests, localField: questStatus,  foreignField: _id,    as: questStatus}}",
//            "{$unwind: {path: $questStatus, preserveNullAndEmptyArrays: true}}",
//            "{$lookup: {from: skills, localField: skillSet,     foreignField: _id,    as: skillSet}}",
//            "{$unwind: {path: $skillSet, preserveNullAndEmptyArrays: true}}",
//            "{$lookup: {from: stats,  localField: stats,        foreignField: _id,    as: stats}}",
//            "{$unwind: {path: $stats, preserveNullAndEmptyArrays: true}}",
            "{$match : {_id: 'ObjectId(\"?0\")'}}"
    })
    Optional<PersonDAO> findFullPersonInfoById(String id);

    @Query(value = "{ 'email' : ?0 }",
            fields = "{_id:1," +
                    " name:1," +
                    " surname:1," +
                    " phone:1," +
                    " email:1," +
                    " address:1," +
                    " createdDate:1," +
                    " createdBy:1," +
                    " modifiedDate:1," +
                    " modifiedBy:1}")
    Optional<PersonDAO> findPartialPersonInfoByEmail(String email);

    @Aggregation(pipeline = {
//            "{$lookup: {from: quests, localField: questStatus,  foreignField: _id,    as: questStatus}}",
//            "{$unwind: {path: $questStatus, preserveNullAndEmptyArrays: true}}",
//            "{$lookup: {from: skills, localField: skillSet,     foreignField: _id,    as: skillSet}}",
//            "{$unwind: {path: $skillSet, preserveNullAndEmptyArrays: true}}",
//            "{$lookup: {from: stats,  localField: stats,        foreignField: _id,    as: stats}}",
//            "{$unwind: {path: $stats, preserveNullAndEmptyArrays: true}}",
            "{$match : {email: '?0'}}"
    })
    Optional<PersonDAO> findFullPersonInfoByEmail(String email);

    @Query(value = "{'name': {$regex: ?0, $options: 'i' }, 'surname': {$regex: ?1, $options: 'i'}}",
            fields = "{_id:1," +
                    " name:1," +
                    " surname:1," +
                    " phone:1," +
                    " email:1," +
                    " address:1," +
                    " createdDate:1," +
                    " createdBy:1," +
                    " modifiedDate:1," +
                    " modifiedBy:1}")
    Optional<PersonDAO> findPartialPersonInfoByNameAndSurname(String firstname, String lastname);

    @Aggregation(pipeline = {
//            "{$lookup: {from: quests, localField: questStatus,  foreignField: _id,    as: questStatus}}",
//            "{$unwind: {path: $questStatus, preserveNullAndEmptyArrays: true}}",
//            "{$lookup: {from: skills, localField: skillSet,     foreignField: _id,    as: skillSet}}",
//            "{$unwind: {path: $skillSet, preserveNullAndEmptyArrays: true}}",
//            "{$lookup: {from: stats,  localField: stats,        foreignField: _id,    as: stats}}",
//            "{$unwind: {path: $stats, preserveNullAndEmptyArrays: true}}",
            "{$match : {'name': {$regex: ?0, $options: 'i' }, 'surname': {$regex: ?1, $options: 'i'}} }"
    })
    Optional<PersonDAO> findFullPersonInfoByNameAndSurname(String firstname, String lastname);

    @Aggregation(pipeline = {
            "{$skip: ?0}",
            "{$limit: ?1}",
            "{$project: {_id:1, name:1, surname:1, phone:1, email:1, address:1, createdDate:1, createdBy:1, modifiedDate:1, modifiedBy:1}}"
    })
    List<PersonDAO> findAllPartialPersonInfo(int skip, int limit);

    @Aggregation(pipeline = {
//            "{$lookup: {from: quests, localField: questStatus,  foreignField: _id,    as: questStatus}}",
//            "{$unwind: {path: $questStatus, preserveNullAndEmptyArrays: true}}",
//            "{$lookup: {from: skills, localField: skillSet,     foreignField: _id,    as: skillSet}}",
//            "{$unwind: {path: $skillSet, preserveNullAndEmptyArrays: true}}",
//            "{$lookup: {from: stats,  localField: stats,        foreignField: _id,    as: stats}}",
//            "{$unwind: {path: $stats, preserveNullAndEmptyArrays: true}}",
            "{$skip: ?0}",
            "{$limit: ?1}"
    })
    List<PersonDAO> findAllFullPersonInfo(int skip, int limit);

//    @Query(value="{'_id' : 'ObjectId(\"?0\")'}")
//    PersonDTO createNewPerson(PersonDAO newPerson);
//
//    @Query(value="{'_id' : 'ObjectId(\"?0\")'}")
//    void updateExistingPerson(PersonDAO person);
//
    @DeleteQuery(value="{'_id' : 'ObjectId(\"?0\")'}")
    void deletePersonById(String id);

    @ExistsQuery(value = "{'name': {$regex: ?0, $options: 'i' }, 'surname': {$regex: ?1, $options: 'i'}}")
    boolean existByNameSurname(String firstname, String lastname);

    @ExistsQuery(value = "{'email': {$regex: ?0, $options: 'i' }}")
    boolean existByEmail(String email);

    @CountQuery(value = "{}")
    int personsCount();

    @CountQuery(value = "{'email': {$regex : \".*?0.*\"}}")
    int emailDomainCountByEmailProviderName(String emailProvider);

}
