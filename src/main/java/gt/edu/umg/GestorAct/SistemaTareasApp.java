package gt.edu.umg.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "gt.edu.umg.database.model")
@EnableJpaRepositories(basePackages = "gt.edu.umg.database.repository")
@ComponentScan(basePackages = {
        "gt.edu.umg.taskmanager",
        "gt.edu.umg.database",
        "gt.edu.umg.rabbit"
})
public class SistemaTareasApp {
    public static void main(String[] args) {
        SpringApplication.run(SistemaTareasApp.class, args);
    }
}
