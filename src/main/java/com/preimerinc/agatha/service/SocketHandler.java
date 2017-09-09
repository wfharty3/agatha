package com.preimerinc.agatha.service;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

	WebSocketSession session;

	@Autowired
	RulesEngineService reService;


	// This will send only to one client(most recently connected)
	public void counterIncrementedCallback(int counter) {
		//System.out.println("Trying to send:" + counter);
		if (session != null && session.isOpen()) {
			try {
				System.out.println("Now sending:" + counter);
				session.sendMessage(new TextMessage("{\"value\": \"" + counter + "\"}"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//else {
		//	System.out.println("Don't have open session to send:" + counter);
		//}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("Connection established");
		this.session = session;
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {

		String retValue = null;

		if ("CLOSE".equalsIgnoreCase(message.getPayload())) {
			session.close();
		} else {
			System.out.println("Received:" + message.getPayload());

			// run rules engine here

			FhirContext ctx = FhirContext.forDstu2();
			Observation obx = ctx.newJsonParser().parseResource(Observation.class, message.getPayload());

			reService.setData(obx);
			List<Flag> flags = reService.runRules();

			Bundle b = new Bundle();
			for (Flag f : flags) {
				b.addEntry().setResource(f);
			}

			// return bundle to web service
			retValue = ctx.newJsonParser().encodeBundleToString(b);

			if (session != null && session.isOpen()) {
				try {
					System.out.println("Now sending flags....");
					session.sendMessage(new TextMessage(retValue));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// end rule engine code

		}
	}
}
