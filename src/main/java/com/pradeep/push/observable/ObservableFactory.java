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
package com.pradeep.push.observable;

import java.util.HashMap;
import java.util.Map;

/**
 * Returns the Observable instance of a resource
 */
public class ObservableFactory {

	private static final ObservableFactory INSTANCE = new ObservableFactory();
	private final Map<Class, ObservableResource> resources = new HashMap<Class, ObservableResource>();

	// singleton
	private ObservableFactory() {

	}

	public static ObservableFactory getFactory() {
		return INSTANCE;
	}

	/**
	 * Get a resource for observation
	 * 
	 * @param resourceClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends ObservableResource> T getResource(final Class<T> resourceClass) {
		return (T) this.resources.get(resourceClass);
	}

	/**
	 * Registers a resource for observation
	 *
	 * @param resourceClass
	 * @param resource
	 */
	public <T extends ObservableResource> void registerResource(final Class<T> resourceClass, final ObservableResource resource) {
		this.resources.put(resourceClass, resource);
	}

}
