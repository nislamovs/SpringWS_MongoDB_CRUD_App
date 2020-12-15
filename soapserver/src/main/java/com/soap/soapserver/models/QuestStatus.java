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
public class QuestStatus extends AbstractDocument {

        @Field(name="findJohn")
        private boolean findJohn = false;

        @Field(name="killMary")
        private boolean killMary = false;

        @Field(name="steelCar")
        private boolean steelCar = false;

        @Field(name="casinoRobbery")
        private boolean casinoRobbery = false;

        @Field(name="steelPainting")
        private boolean steelPainting = false;
}


