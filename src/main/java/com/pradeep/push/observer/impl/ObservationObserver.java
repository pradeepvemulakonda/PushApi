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
package com.pradeep.push.observer.impl;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;

import com.google.gson.Gson;
import com.pradeep.push.domain.Patient;
import com.pradeep.push.observable.ObservableResource;
import com.pradeep.push.rest.registry.RegistryEntry;

/**
 *
 */
public class ObservationObserver extends AbstractResourceObserver {

	/**
	 * @param observableResource
	 * @param entry
	 */
	public ObservationObserver(final ObservableResource observableResource, final RegistryEntry entry) {
		super(observableResource, entry);
	}

	@Override
	public boolean updateBasic(final Patient resource, final HttpPost httppost) {
		if (resource.getPatientId().equals(getRegistryentry().getPatientId())) {
			httppost.setEntity(new ByteArrayEntity(new Gson().toJson(resource).getBytes()));
			return true;
		}
		return false;
	}

}
