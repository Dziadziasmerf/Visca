package pl.edu.agh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication
public class ViscaApplication {

    @Autowired
    CommandExecutor commandExecutor;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ViscaApplication.class, args);
        context.getBean(CommandExecutor.class).openPort();
    }


    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ViscaApplication.class);
    }

}
