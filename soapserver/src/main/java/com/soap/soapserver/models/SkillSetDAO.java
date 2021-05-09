package com.soap.soapserver.models;


import com.soap.soapserver.repository.PersonsRepository;
import com.soap.soapserver.repository.SkillsRepository;
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
@Document(collection = "skills")
@TypeAlias("skills")
@ToString(callSuper = true)
public class SkillSetDAO extends AbstractDocument {

        @Field(name="gambling")
        private Integer gambling;

        @Field(name="pickpocketing")
        private Integer pickpocketing;

        @Field(name="lockpicking")
        private Integer lockpicking;

        @Field(name="technology")
        private Integer technology;

        @Field(name="martialArts")
        private Integer martialArts;

        @Field(name="lightWeapons")
        private Integer lightWeapons;

        @Field(name="heavyWeapons")
        private Integer heavyWeapons;

        @Field(name="barter")
        private Integer barter;

        @Field(name="speechcraft")
        private Integer speechcraft;
}
