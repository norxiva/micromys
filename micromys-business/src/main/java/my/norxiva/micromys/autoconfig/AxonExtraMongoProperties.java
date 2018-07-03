package my.norxiva.micromys.autoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("axon.extra.mongo")
public class AxonExtraMongoProperties {

    private String database;
}
