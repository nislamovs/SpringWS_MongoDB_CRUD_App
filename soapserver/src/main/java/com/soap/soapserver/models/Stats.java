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
        private Integer strength;

        @Field(name="endurance")
        private Integer endurance;

        @Field(name="dexterity")
        private Integer dexterity;

        @Field(name="intellect")
        private Integer intellect;

        @Field(name="attention")
        private Integer attention;

        @Field(name="personality")
        private Integer personality;

        @Field(name="luck")
        private Integer luck;

}
