package pro.nova_guard.ng_operation.config;
import org.axonframework.extensions.mongo.eventhandling.saga.repository.MongoSagaStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.context.annotation.Primary;

@Configuration
public class AxonConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public DefaultMongoTemplate axonMongoTemplate(MongoClient mongoClient) {
        return DefaultMongoTemplate.builder()
                .mongoDatabase(mongoClient, "axon")
                .build();
    }

    @Bean
    public MongoEventStorageEngine eventStorageEngine(@Qualifier("jacksonSerializer") Serializer jacksonSerializer, DefaultMongoTemplate mongoTemplate) {
        return MongoEventStorageEngine.builder()
                .mongoTemplate(mongoTemplate)
                .eventSerializer(jacksonSerializer)
                .build();
    }

    @Bean
    public MongoSagaStore sagaStore(DefaultMongoTemplate mongoTemplate, @Qualifier("jacksonSerializer") Serializer jacksonSerializer) {
        return MongoSagaStore.builder()
                .mongoTemplate(mongoTemplate)
                .serializer(jacksonSerializer)
                .build();
    }

    @Bean
    @Primary
    public Serializer jacksonSerializer() {
        return JacksonSerializer.defaultSerializer();
    }
}


