package com.preimerinc.agatha.service;

import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import com.preimerinc.agatha.repository.FlagRepository;
import com.preimerinc.agatha.rule.EnhancedRule;
import com.preimerinc.agatha.rule.HypokalemiaRule;
import com.preimerinc.agatha.rule.HyponatremiaRule;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.easyrules.api.Rule;
import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RulesEngineService {

	private RulesEngine re;

	@Autowired
	public RulesEngineService(RulesEngine re) {
		this.re = re;
	}

	@Autowired
	private FlagRepository flagRepo;


	private Observation data;
	private Bundle flags;

	public void setData(Observation data) {
		this.data = data;
	}

	private void setRuleData() {
		Set<Rule> rules = re.getRules();
		for (Rule r : rules) {
			System.out.println(r.getName());
		}
	}

	private void registerRules() {
		re.registerRule(new HyponatremiaRule(data));
		re.registerRule(new HypokalemiaRule(data));
		// register other rules that should run here
	}

	public List<Flag> runRules() {
		registerRules();
		re.fireRules();
		// collect resulting flags from each rule
		Set<Rule> rules = re.getRules();
		List<Flag> flags = new ArrayList<Flag>();

		for (Rule r : rules) {
			EnhancedRule re = (EnhancedRule)r;
			for (Flag f: re.getFlags()) {
				flags.add(f);
			}
			System.out.println(r.getName());
		}
		return flags;
	}

}
