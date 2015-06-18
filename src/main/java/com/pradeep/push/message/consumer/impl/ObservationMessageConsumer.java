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
package com.pradeep.push.message.consumer.impl;

import com.google.gson.Gson;
import com.pradeep.push.domain.Observation;
import com.pradeep.push.domain.Resource;
import com.pradeep.push.message.consumer.QueueAware;
import com.pradeep.push.observable.ObservableFactory;
import com.pradeep.push.observer.impl.ObservationObserver;
import com.pradeep.push.rest.registry.Registery;
import com.pradeep.push.rest.registry.RegistryEntry;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

/**
 *
 */
public class ObservationMessageConsumer implements QueueAware {

	final Registery fhirRegistery;
	final ObservableFactory factory;
	QueueingConsumer consumer;

	public ObservationMessageConsumer(final Registery fhirRegistery, final ObservableFactory factory) {
		this.fhirRegistery = fhirRegistery;
		this.factory = factory;
	}

	@Override
	public void run() {
		try {
			final Observation observation = this.factory.getResource(Observation.class);
			for (final RegistryEntry endpoint : this.fhirRegistery.getSubscribers()) {
				if (Resource.OBSERVATION.toString().equals(endpoint.getResource())) {
					observation.addObserver(new ObservationObserver(observation, endpoint));
				}
			}
			while (true) {
				QueueingConsumer.Delivery delivery;
				delivery = this.consumer.nextDelivery();
				final String message = new String(delivery.getBody());
				final String routingKey = delivery.getEnvelope().getRoutingKey();
				final Observation ob = new Gson().fromJson(message, Observation.class);
				observation.setObservation(ob.getObservation());
				observation.setPatientId(ob.getPatientId());
				observation.setPatientNamespace(ob.getPatientNamespace());
				System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");
				observation.publish();
			}
		} catch (ShutdownSignalException | ConsumerCancelledException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void setQueueConsumer(final QueueingConsumer consumer) {
		this.consumer = consumer;
	}
}

