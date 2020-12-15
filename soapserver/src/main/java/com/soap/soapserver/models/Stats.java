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
@Document(collection = "stats")
@TypeAlias("stats")
public class Stats extends AbstractDocument {

        @Field(name="strength")
        private int strength;

        @Field(name="endurance")
        private int endurance;

        @Field(name="dexterity")
        private int dexterity;

        @Field(name="intellect")
        private int intellect;

        @Field(name="attention")
        private int attention;

        @Field(name="personality")
        private int personality;

        @Field(name="luck")
        private int luck;

}
