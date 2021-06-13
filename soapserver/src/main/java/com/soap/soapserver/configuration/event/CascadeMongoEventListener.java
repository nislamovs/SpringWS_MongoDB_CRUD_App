package com.soap.soapserver.configuration.event;

import com.soap.soapserver.models.PersonDAO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Objects;

public class CascadeMongoEventListener extends AbstractMongoEventListener<PersonDAO> {

    @Autowired
    private MongoOperations mongoOperations;
    private PersonDAO personDAO;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<PersonDAO> event) {
        final Object id = Objects.requireNonNull(event.getDocument()).get("_id");
        personDAO = mongoOperations.findById(id, PersonDAO.class);
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<PersonDAO> event) {
        ReflectionUtils.doWithFields(PersonDAO.class, new CascadeDeleteCallback(personDAO, mongoOperations));
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<PersonDAO> event) {
        final Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new CascadeSaveCallback(source, mongoOperations));
    }
}