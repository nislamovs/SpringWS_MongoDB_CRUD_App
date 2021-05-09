package com.soap.soapserver.models;


import com.soap.soapserver.configuration.event.Cascade;
import com.soap.soapserver.domain.enums.Perks;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "persons")
@TypeAlias("persons")
@ToString(callSuper = true)
public class PersonDAO extends AbstractDocument {

    @Field(name = "name")
    private String name;

    @Field(name = "surname")
    private String surname;

    @Field(name = "phone")
    private String phone;

    @Field(name = "email")
    @Indexed(name = "email", unique = true)
    private String email;

    @Field(name = "address")
    private Address address;

    @DBRef(db = "characters", lazy = false)
    @Cascade
    private QuestStatusDAO questStatus;

    @DBRef(db = "characters", lazy = false)
    @Cascade
    private SkillSetDAO skillSet;

    @DBRef(db = "characters", lazy = false)
    @Cascade
    private StatsDAO stats;

    @Field(name = "perksSet")
    private Set<Perks> perksSet;

}
