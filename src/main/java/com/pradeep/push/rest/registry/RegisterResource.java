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
package com.pradeep.push.rest.registry;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.pradeep.push.domain.Resource;
import com.pradeep.push.domain.RestEndpoint;

@Path("/Registry")
public class RegisterResource {

	private Registery fhirRegistery;

	@POST
	@Path("/{patient}/{resource}")
	@Consumes("application/json")
	public Response register(@PathParam("resource") final Resource resource, @PathParam("patient") final String patient,
			final RestEndpoint endpoint) {
		this.fhirRegistery.register(Resource.OBSERVATION, endpoint, patient);
		return Response.ok().build();
	}

	public void setFhirRegistery(final Registery fhirRegistery) {
		this.fhirRegistery = fhirRegistery;
	}

	@DELETE
	@Path("/{patient}/{resource}")
	@Consumes("application/json")
	public Response unregister(@PathParam("resource") final Resource resource, @PathParam("patient") final String patient,
			final RestEndpoint endpoint) {
		this.fhirRegistery.deRegister(Resource.OBSERVATION, endpoint, patient);
		return Response.ok().build();
	}
}
