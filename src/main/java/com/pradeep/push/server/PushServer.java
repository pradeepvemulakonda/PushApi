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
package com.pradeep.push.server;

import com.pradeep.push.domain.Observation;
import com.pradeep.push.domain.Resource;
import com.pradeep.push.domain.ResourceConsumerMap;
import com.pradeep.push.message.consumer.MasterMessageConsumer;
import com.pradeep.push.message.consumer.impl.ObservationMessageConsumer;
import com.pradeep.push.observable.ObservableFactory;
import com.pradeep.push.rest.registry.Registery;

public class PushServer {

	public static void main(final String[] args) throws Exception {
		final Registery registery = Registery.getInstance();
		final ObservableFactory factory = ObservableFactory.getFactory();
		factory.registerResource(Observation.class, new Observation());
		final ResourceConsumerMap consumerMap = new ResourceConsumerMap();
		consumerMap.addConsumer(Resource.OBSERVATION, new ObservationMessageConsumer(registery, factory));
		final MasterMessageConsumer consumeObservations = new MasterMessageConsumer(consumerMap);
		final Thread obThread = new Thread(consumeObservations);
		obThread.start();
	}
}
