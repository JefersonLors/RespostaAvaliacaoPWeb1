package exemplo.seguranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SegurancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SegurancaApplication.class, args);
	}

}
