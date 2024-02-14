package com.onito.tambola.tambolaticketgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.onito.tambola.tambolaticketgenerator")
@ConfigurationPropertiesScan({"com.onito.tambola.tambolaticketgenerator.*"})
public class TambolaTicketGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TambolaTicketGeneratorApplication.class, args);
	}

}
