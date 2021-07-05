package com.mercadolibre.dambetan01;

import com.mercadolibre.dambetan01.config.SpringConfig;
import com.mercadolibre.dambetan01.util.ScopeUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
/*
	final DataInsertionService dataInsertionService;

	public Application(DataInsertionService dataInsertionService) {
		this.dataInsertionService = dataInsertionService;
	}
*/
	public static void main(String[] args) {
		ScopeUtils.calculateScopeSuffix();
		new SpringApplicationBuilder(SpringConfig.class).registerShutdownHook(true)
				.run(args);





	}
}
