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

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.pradeep.push.domain.Patient;
import com.pradeep.push.observable.ObservableResource;
import com.pradeep.push.rest.registry.RegistryEntry;

public abstract class AbstractResourceObserver implements Observer {

	// private final Updatable<T> updatableResource;
	private final RegistryEntry registryEntry;

	public AbstractResourceObserver(final ObservableResource updatableResource, final RegistryEntry registryEntry) {
		// this.updatableResource = updatableResource;
		this.registryEntry = registryEntry;
	}

	@Override
	public void update(final Observable resource, final Object arg) {
		final CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			final HttpPost httppost = new HttpPost(getRegistryentry().getEndpoint().getURL().toURI());
			if (!updateBasic((ObservableResource) resource, httppost)) {
				return;
			}
			httpclient.execute(httppost, new ResponseHandler<String>() {

				@Override
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					System.out.println(response.getStatusLine());
					return response.getEntity().toString();
				}
			});
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (final IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public abstract boolean updateBasic(final Patient resource, final HttpPost httppost);

	protected RegistryEntry getRegistryentry() {
		return this.registryEntry;
	}


}
