/*
 * Copyright (c) Orchestral Developments Ltd and the Orion Health group of companies (2001 - 2015).
 *
 * This document is copyright. Except for the purpose of fair reviewing, no part
 * of this publication may be reproduced or transmitted in any form or by any
 * means, electronic or mechanical, including photocopying, recording, or any
 * information storage and retrieval system, without permission in writing from
 * the publisher. Infringers of copyright render themselves liable for
 * prosecution.
 */
package com.pradeep.push.rest.registry;

import java.util.List;

import com.pradeep.push.domain.Resource;
import com.pradeep.push.domain.RestEndpoint;

public class Registery {

	private final static Registery FHIR_REGISTERY = new Registery();
	private final PersistentRegistry persistentRegistry;

	private Registery() {
		this.persistentRegistry = new PersistentRegistry();
	}

	public static Registery getInstance() {
		return FHIR_REGISTERY;
	}

	public List<RegistryEntry> getSubscribers() {
		return this.persistentRegistry.selectRegistrations();
	}

	public void register(final Resource resource, final RestEndpoint restEndpoint, final String patient) {
		this.persistentRegistry.insertRegistration(resource.toString(), restEndpoint, patient);
	}

	public void deRegister(final Resource resource, final RestEndpoint restEndpoint, final String patient) {
		this.persistentRegistry.deleteRegistration(resource.toString(), restEndpoint, patient);
	}
}
