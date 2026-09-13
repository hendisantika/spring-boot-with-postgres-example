package id.my.hendisantika.example;

import id.my.hendisantika.example.config.TestContainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpringBootWithPostgresExampleApplicationTests extends TestContainersConfig {

    @Test
    void contextLoads() {
    }

}
