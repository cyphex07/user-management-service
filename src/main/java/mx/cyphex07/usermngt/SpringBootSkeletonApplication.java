package mx.cyphex07.usermngt;

import mx.cyphex07.usermngt.config.SecurityConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("mx.hexsilvar.skeleton")
@EntityScan("mx.hexsilvar.skeleton.model")
public class SpringBootSkeletonApplication {

  private static final Class<?>[] CONFIGS = {
      SpringBootSkeletonApplication.class,
      SecurityConfig.class
  };

  public static void main(String[] args) {
    SpringApplication.run(CONFIGS, args);
  }

}
