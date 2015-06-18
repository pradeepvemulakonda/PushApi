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
package com.pradeep.push.publish.queue;

import com.google.gson.Gson;
import com.pradeep.push.domain.ConfigException;
import com.pradeep.push.domain.Observation;
import com.pradeep.push.domain.Resource;
import com.pradeep.push.queue.Configuration;
import com.pradeep.push.queue.PushQueueFactory;
import com.rabbitmq.client.Channel;

public class PublishObservations {

	public void publish(final String message, final String patientId, final String patientns) {
		try {
			final Channel channel = PushQueueFactory.getFactory().getChannel(Configuration.HOST);
			final Observation observation = new Observation();
			observation.setObservation(message);
			observation.setPatientId(patientId);
			observation.setPatientNamespace(patientns);
			final String jsonMessage = new Gson().toJson(observation);
			PushQueueFactory.getFactory().publishMessage(channel, Resource.OBSERVATION.toString(), jsonMessage);
			System.out.println(" [x] Sent '" + jsonMessage + "'");
			PushQueueFactory.getFactory().closeChannel(channel);
		} catch (final Exception e) {
			throw new ConfigException(e);
		}
	}
}
