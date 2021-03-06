package com.soap.soapserver.models;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDocument {//implements Persistable<String> {

//    private boolean persisted;
//
//    @Override
//    public boolean isNew() {
//        return !persisted;
//    }

    @Id
    @Field(name="_id")
    private String id;

    @CreatedDate
    @Field(name="createdDate")
    private Instant createdDate;

    @CreatedBy
    @Field(name="createdBy")
    private String createdBy;

    @LastModifiedDate
    @Field(name="modifiedDate")
    private Instant modifiedDate;

    @LastModifiedBy
    @Field(name="modifiedBy")
    private String modifiedBy;

    @Field(name="version")
    @Version
//    @Transient
    private Long version;
}
