package pro.nova_guard.ng_operation.config;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SerializerConfiguration {
    @Bean
    @Qualifier("defaultAxonXStream")
    public XStream xStream() {
        XStream xStream = new XStream();

        xStream.allowTypesByWildcard(new String[] {
                "our.package.**"
        });
        return xStream;
    }

    @Bean
    public Serializer xStreamSerializer(XStream xStream) {
        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }
}
