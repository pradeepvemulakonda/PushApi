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
package com.pradeep.push.domain;

import com.pradeep.push.observable.ObservableResource;

/**
 *
 */
public class Observation extends ObservableResource {

	private String observation = null;
	private  String patientId = null;
	private  String patientNamespace = null;

	@Override
	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(final String patientId) {
		this.patientId = patientId;
	}

	@Override
	public String getPatientNamespace() {
		return this.patientNamespace;
	}

	public void setPatientNamespace(final String patientNamespace) {
		this.patientNamespace = patientNamespace;
	}

	public String getObservation() {
		return this.observation;
	}

	public void setObservation(final String observation) {
		this.observation = observation;
	}

}
