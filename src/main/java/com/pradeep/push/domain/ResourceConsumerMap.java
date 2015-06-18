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

import java.util.HashMap;
import java.util.Map;

import com.pradeep.push.message.consumer.QueueAware;

public class ResourceConsumerMap {

	private final Map<String, QueueAware> consumerList = new HashMap();

	public void addConsumer(final Resource resource, final QueueAware consumer) {
		this.consumerList.put(resource.toString(), consumer);
	}

	public void removewConsumer(final Resource resource) {
		this.consumerList.remove(resource.toString());
	}

	public QueueAware get(final Resource resource) {
		return this.consumerList.remove(resource.toString());
	}
}

