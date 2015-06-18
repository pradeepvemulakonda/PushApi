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

import java.net.URL;

public class RestEndpoint {

	private URL endpoint;

	public RestEndpoint() {
	}

	public RestEndpoint(final URL url) {
		this.endpoint = url;
	}

	public URL getURL() {
		return this.endpoint;
	}

	public void setEndpoint(final URL endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.endpoint == null) ? 0 : this.endpoint.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RestEndpoint other = (RestEndpoint) obj;
		if (this.endpoint == null) {
			if (other.endpoint != null) {
				return false;
			}
		} else if (!this.endpoint.equals(other.endpoint)) {
			return false;
		}
		return true;
	}

}
