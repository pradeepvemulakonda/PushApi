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
package com.pradeep.push.message.consumer;

import com.pradeep.push.domain.ConfigException;
import com.pradeep.push.domain.Resource;
import com.pradeep.push.domain.ResourceConsumerMap;
import com.pradeep.push.queue.Configuration;
import com.pradeep.push.queue.PushQueueFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

/**
 *
 */
public class MasterMessageConsumer implements Runnable {

	private final ResourceConsumerMap resourceConsumerMap;

	/**
	 * @param resourceConsumerMap
	 */
	public MasterMessageConsumer(final ResourceConsumerMap resourceConsumerMap) {
		this.resourceConsumerMap = resourceConsumerMap;
	}

	@Override
	public void run() {
		try {
			final Channel channel = PushQueueFactory.getFactory().getChannel(Configuration.HOST);
			for (final Resource resource : Resource.values()) {
				PushQueueFactory.getFactory().declareAndBindQueue(channel, resource.toString());
				final QueueingConsumer consumer = new QueueingConsumer(channel);
				channel.basicConsume(resource.toString(), true, consumer);
				final QueueAware resourceConsumer = this.resourceConsumerMap.get(resource);
				if (resourceConsumer != null) {
					resourceConsumer.setQueueConsumer(consumer);
					final Thread consumerThread = new Thread(resourceConsumer);
					consumerThread.start();
				}
			}

		} catch (final Exception e) {
			throw new ConfigException(e);
		}
	}
}
