package com.preimerinc.agatha.controller;

import ca.uhn.fhir.model.api.Bundle;
import com.preimerinc.agatha.model.AgathaFlag;
import com.preimerinc.agatha.repository.FlagRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agatha/flag")
public class FlagController {

	@Autowired
	FlagRepository flagRepo;

	@RequestMapping("/local")
	public Bundle listAllLocal() {
		return flagRepo.listFlagsLocal();
	}
}
