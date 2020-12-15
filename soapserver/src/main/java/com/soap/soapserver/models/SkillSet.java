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
@Document(collection = "skills")
@TypeAlias("skills")
public class SkillSet extends AbstractDocument {

        @Field(name="gambling")
        private int gambling;

        @Field(name="pickpocketing")
        private int pickpocketing;

        @Field(name="lockpicking")
        private int lockpicking;

        @Field(name="technology")
        private int technology;

        @Field(name="martialArts")
        private int martialArts;

        @Field(name="lightWeapons")
        private int lightWeapons;

        @Field(name="heavyWeapons")
        private int heavyWeapons;

        @Field(name="barter")
        private int barter;

        @Field(name="speechcraft")
        private int speechcraft;
}
