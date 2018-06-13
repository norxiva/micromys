package my.norxiva.micromys.autoconfig;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("distributed")
@Configuration
@EnableDiscoveryClient
public class EurekaConfig {
}
