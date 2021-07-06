package com.mercadolibre.projetointegrador;

import com.mercadolibre.projetointegrador.config.SpringConfig;
import com.mercadolibre.projetointegrador.util.ScopeUtils;
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
