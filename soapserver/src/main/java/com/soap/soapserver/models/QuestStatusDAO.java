package com.soap.soapserver.models;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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


