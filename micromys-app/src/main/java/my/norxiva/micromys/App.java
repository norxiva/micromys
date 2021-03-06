package my.norxiva.micromys;

import org.axonframework.boot.autoconfig.JdbcAutoConfiguration;
import org.axonframework.boot.autoconfig.JpaAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
// exclude axon jdbc and jpa auto configuration
@EnableAutoConfiguration(exclude = {JdbcAutoConfiguration.class, JpaAutoConfiguration.class})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
