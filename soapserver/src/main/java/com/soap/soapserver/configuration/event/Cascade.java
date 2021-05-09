package com.soap.soapserver.configuration.event;

import com.soap.soapserver.configuration.event.CascadeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Cascade {

  CascadeType value() default CascadeType.ALL;
}
