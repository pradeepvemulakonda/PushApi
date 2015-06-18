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
package com.pradeep.pushi.test.publisher;

import com.pradeep.push.publish.queue.PublishObservations;

public class SendObservations {

	public static void main(final String[] args) {
		final PublishObservations observations = new PublishObservations();
		observations.publish("Observation 1", "patient1", "patient1ns");
		observations.publish("Observation 1", "patient2", "patient2ns");
		observations.publish("Observation 2", "patient2", "patient2ns");
		observations.publish("Observation 3", "patient2", "patient2ns");
		System.exit(1);
	}
}
