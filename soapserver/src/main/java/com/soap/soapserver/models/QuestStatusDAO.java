package com.soap.soapserver.models;


import com.soap.soapserver.repository.PersonsRepository;
import com.soap.soapserver.repository.QuestsRepository;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "quests")
@TypeAlias("quests")
@ToString(callSuper = true)
public class QuestStatusDAO extends AbstractDocument {

        @Field(name="findJohn")
        private Boolean findJohn;

        @Field(name="killMary")
        private Boolean killMary;

        @Field(name="steelCar")
        private Boolean steelCar;

        @Field(name="casinoRobbery")
        private Boolean casinoRobbery;

        @Field(name="steelPainting")
        private Boolean steelPainting;
}


