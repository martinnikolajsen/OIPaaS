package oipaas.oipaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OiPaaSApplication {

	public static void main(String[] args) {
		SpringApplication.run(OiPaaSApplication.class, args);
	}

}
