package com.soap.soapserver.configuration;

import com.soap.soapserver.configuration.event.CascadeMongoEventListener;
import com.soap.soapserver.converters.mongoConverters.dateConverters.*;
import lombok.SneakyThrows;
import net.ozwolf.mongo.migrations.MongoTrek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableMongoRepositories(basePackages={"com.soap.soapserver.repository"})
@EnableMongoAuditing
public class MongoDBConfiguration {

    @Value(value = "${mongodb.uri}")
    String uri;
    @Value(value = "${spring.data.mongodb.database}")
    String database;
    @Value(value = "${application.property.mongotrek.changelog.collection.name}")
    String changeLogName;
    @Value(value = "${application.property.mongotrek.script.path}")
    String changeLogLocation;

    @Autowired MongoDatabaseFactory mongoDbFactory;
    @Autowired MongoMappingContext mongoMappingContext;

    @Bean
    @SneakyThrows
    public MongoTrek store() {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(uri);
        System.out.println(database);
        System.out.println(changeLogName);
        System.out.println(changeLogLocation);
        System.out.println("---------------------------------------------------------------------------------------");

        MongoTrek trek = new MongoTrek(changeLogLocation, uri + database);
        trek.setSchemaVersionCollection(changeLogName);
        trek.migrate();

        return trek;
    }

    @Bean
    public CascadeMongoEventListener cascadingSaveMongoEventListener() {
        return new CascadeMongoEventListener();
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter> converters = new ArrayList<>();

        converters.add(new InstantToStringConverter());
        converters.add(new StringToInstantConverter());
        converters.add(new LocalDateToStringConverter());
        converters.add(new StringToLocalDateConverter());

        return new MongoCustomConversions(converters);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {

        //getting rid of _class
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

//    @Bean
//    public AuditorAware<String> auditorProvider() {
//        return null;
//    }
}
