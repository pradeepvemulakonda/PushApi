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

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pradeep.push.domain.Resource;
import com.pradeep.push.domain.RestEndpoint;

public class PersistentRegistry
{

	// jdbc Connection
	private static Connection conn = null;
	private static String DBMANE = "Push-API";
	private static String REGISTRY_TABLE = "Registry";
	private static String dbURL = "jdbc:derby:" + DBMANE + ";create=true";

	public PersistentRegistry()
	{
		createConnection();
	}

	public static void main(final String[] args) {
		final PersistentRegistry persistentRegistry = new PersistentRegistry();
		try {
			persistentRegistry.deleteRegistration(Resource.OBSERVATION.toString(), new RestEndpoint(
					new URL("http://localhost:9001/Observation")), "test@testns");
		} catch (final MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createConnection()
	{
		try
		{
			final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			Class.forName(driver).newInstance();
			// Get a connection
			conn = DriverManager.getConnection(dbURL);
		} catch (final Exception except)
		{
			except.printStackTrace();
		}
	}

	public void insertRegistration(final String resource, final RestEndpoint endpoint, final String patient)
	{
		PreparedStatement stmt = null;
		final String[] patientData = patient.split("@");
		try
		{
			stmt = conn.prepareStatement("insert into  " + REGISTRY_TABLE + " values (?,?,?,?)");
			stmt.setString(1, resource);
			stmt.setString(2, endpoint.getURL().toExternalForm());
			stmt.setString(3, patientData[0]);
			stmt.setString(4, patientData[1]);
			stmt.execute();
			stmt.close();
		} catch (final SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
			try {
				stmt.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteRegistration(final String resource, final RestEndpoint endpoint, final String patient)
	{
		PreparedStatement stmt = null;
		final String[] patientData = patient.split("@");
		try
		{
			stmt = conn.prepareStatement("delete from  " + REGISTRY_TABLE
					+ " where resource=? and endpoint=? and patientId=? and patientNamespace = ?");
			stmt.setString(1, resource);
			stmt.setString(2, endpoint.getURL().toExternalForm());
			stmt.setString(3, patientData[0]);
			stmt.setString(4, patientData[1]);
			stmt.execute();
			stmt.close();
		} catch (final SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
			try {
				stmt.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<RegistryEntry> selectRegistrations()
	{
		final List<RegistryEntry> entries = new ArrayList<RegistryEntry>();
		try
		{
			final PreparedStatement stmt = conn.prepareStatement("select * from " + REGISTRY_TABLE);
			final ResultSet results = stmt.executeQuery();

			while (results.next())
			{
				final String resource = results.getString(1);
				final RestEndpoint endpoint = new RestEndpoint(new URL(results.getString(2)));
				final String patientId = results.getString(3);
				final String patientNamespace = results.getString(4);
				entries.add(new RegistryEntry(resource, endpoint, patientId, patientNamespace));
			}
			results.close();
			stmt.close();
		} catch (final SQLException sqlExcept)
		{
			sqlExcept.printStackTrace();
		} catch (final MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entries;
	}

	public void shutdown()
	{
		try
		{
			if (conn != null)
			{
				DriverManager.getConnection(dbURL + ";shutdown=true");
				conn.close();
			}
		} catch (final SQLException sqlExcept)
		{

		}

	}
}