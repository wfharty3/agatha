package com.preimerinc.agatha.controller;

import com.preimerinc.agatha.service.RulesEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agatha/settings")
public class SettingsController {

	@RequestMapping(value="/serverBase", method = RequestMethod.POST)
	public String setServerBase(@RequestBody String url) {
		System.setProperty("serverBase", url );
		return "Server base set to: " + url;
	}

	@RequestMapping(value="/serverBase", method = RequestMethod.GET)
	public String getServerBase() {
		return "Server base: " + System.getProperty("serverBase");
	}


}
