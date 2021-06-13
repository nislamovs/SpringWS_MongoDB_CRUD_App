package com.soap.soapserver.configuration.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CascadeUpdateCallback implements ReflectionUtils.FieldCallback {

    private Object source;
    private MongoOperations mongoOperations;

    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);
        System.out.println(">>>>>>>>>>>>>>>>>>>>..   update");
        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(Cascade.class)) {
            final Object fieldValue = field.get(source);

            if (Objects.nonNull(fieldValue)) {
                FieldCallback callback = new FieldCallback();
                final CascadeType cascadeType = field.getAnnotation(Cascade.class).value();

                if (cascadeType.equals(CascadeType.UPDATE) || cascadeType.equals(CascadeType.ALL)) {
                    if (fieldValue instanceof Collection<?>) {
                        ((Collection<?>) fieldValue).forEach(mongoOperations::save);
                    } else {
                        ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                        mongoOperations.save(fieldValue);
                    }
                }
            }
        }
    }
}
