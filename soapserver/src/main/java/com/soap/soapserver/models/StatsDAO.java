package com.soap.soapserver.models;


import com.soap.soapserver.repository.SkillsRepository;
import com.soap.soapserver.repository.StatsRepository;
import https.localhost._8443.api.v1.ws.persons.Stats;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "stats")
@TypeAlias("stats")
public class StatsDAO extends AbstractDocument {

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
