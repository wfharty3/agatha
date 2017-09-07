package com.preimerinc.agatha.config;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RulesEngineConfig {

	@Bean
	public RulesEngine makeRuleEngine() {

//		HypernatremiaRule naRule = new HypernatremiaRule();

		RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();
//		rulesEngine.registerRule(naRule);
		return rulesEngine;
	}

}
