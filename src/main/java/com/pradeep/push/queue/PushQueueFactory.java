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
package com.pradeep.push.queue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PushQueueFactory {

	private PushQueueFactory() {
		// TODO Auto-generated constructor stub
	}

	private static final PushQueueFactory INSTANCE = new PushQueueFactory();

	public static final PushQueueFactory getFactory() {
		return INSTANCE;
	}

	public synchronized Channel getChannel(final String host) {
		Channel channel = null;
		Connection connection = null;
		try {
			final ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");

			connection = factory.newConnection();
			channel = connection.createChannel();

			channel.exchangeDeclare(Configuration.EXCHANGE_NAME, "direct");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return channel;
	}

	public synchronized void closeChannel(final Channel channel) {
		if (channel != null) {
			try {
				channel.close();
			} catch (IOException | TimeoutException e) {
				e.printStackTrace();
			}
		}
	}

	public void declareAndBindQueue(final Channel channel, final String queueName) throws IOException {
		channel.queueDeclare(queueName, false, false, false, null);
		channel.queueBind(queueName, Configuration.EXCHANGE_NAME, queueName);
	}

	public void publishMessage(final Channel channel, final String queueName, final String message) throws IOException {
		channel.basicPublish(Configuration.EXCHANGE_NAME, queueName, null, message.getBytes("UTF-8"));
	}
}
