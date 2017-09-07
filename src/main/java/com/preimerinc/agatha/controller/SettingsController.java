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
		System.setProperty("serverBase", url.trim() );
		return "Server base has been set to: " + url;
	}

	@RequestMapping(value="/serverBase", method = RequestMethod.GET)
	public String getServerBase() {
		return "Current Server base: " + System.getProperty("serverBase");
	}

	@RequestMapping(value="/outputFlagsTo", method = RequestMethod.POST)
	public String setOutput(@RequestBody String val) {
		System.setProperty("outputFlagsTo", val.trim() );
		return "Flag output option has been set to: " + val;
	}

	@RequestMapping(value="/outputFlagsTo", method = RequestMethod.GET)
	public String getOutput() {
		return "Current flag output option: " + System.getProperty("outputFlagsTo") + ".   Options are: console, local, remote";
	}

}
