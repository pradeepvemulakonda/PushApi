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

import com.pradeep.push.domain.RestEndpoint;

public class RegistryEntry {

	private String resource;
	private RestEndpoint endpoint;
	private String patientId;
	private String patientNamespace;

	public RegistryEntry(final String resource, final RestEndpoint endpoint, final String patientId, final String patientNamespace) {
		super();
		this.resource = resource;
		this.endpoint = endpoint;
		this.patientId = patientId;
		this.patientNamespace = patientNamespace;
	}

	public String getResource() {
		return this.resource;
	}

	public void setResource(final String resource) {
		this.resource = resource;
	}

	public RestEndpoint getEndpoint() {
		return this.endpoint;
	}

	public void setEndpoint(final RestEndpoint endpoint) {
		this.endpoint = endpoint;
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(final String patientId) {
		this.patientId = patientId;
	}

	public String getPatientNamespace() {
		return this.patientNamespace;
	}

	public void setPatientNamespace(final String patientNamespace) {
		this.patientNamespace = patientNamespace;
	}

}
