package com.preimerinc.agatha;

import com.preimerinc.agatha.util.FhirServersEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgathaApplication {

	public static void main(String[] args) {
		System.setProperty("serverBase", FhirServersEnum.UHN_DSTU2.getUrl());
		System.setProperty("outputFlagsTo", "console");
		SpringApplication.run(AgathaApplication.class, args);
	}
}
