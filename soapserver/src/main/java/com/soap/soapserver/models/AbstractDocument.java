package com.soap.soapserver.models;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AbstractDocument {

    @Id
    @Field(name="_id")
    private String id;

    @Field(name="createdDate")
    @CreatedDate
    private Instant createdDate;

    @Field(name="createdBy")
    @CreatedBy
    private String createdBy;

    @Field(name="modifiedDate")
    @LastModifiedDate
    private Instant modifiedDate;

    @Field(name="modifiedBy")
    @LastModifiedBy
    private String modifiedBy;

//    @Field(name="version")
//    @Version
//    private Long version;
}
