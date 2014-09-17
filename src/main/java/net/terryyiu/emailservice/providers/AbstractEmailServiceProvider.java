/*
 * EmailService - RESTful service that sends emails
 * Copyright (C) 2014  Terry Yiu
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.terryyiu.emailservice.providers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import net.terryyiu.emailservice.core.Email;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractEmailServiceProvider implements
		EmailServiceProvider {
	
	@Override
	public boolean send(Email email) throws IOException {
		HttpURLConnection connection = createConnection();
		
		// Create a Map from an email object, which can be translated to JSON which
		// the specific email service provider understands.
		Map<String, Object> map = getRequestPostData(email);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(map);
		
		DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
		dos.writeBytes(json);
		dos.flush();
		dos.close();
		
		int responseCode = connection.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + getServiceUrl());
		System.out.println("JSON: " + json);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		connection.disconnect();
		return false;
	}
	
	private HttpURLConnection createConnection() throws IOException {
		URL url = new URL(getServiceUrl());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setUseCaches(false);
		
		modifyConnection(connection);
		
		return connection;
	}
	
	protected void modifyConnection(HttpURLConnection connection) {
		// Subclasses can override this method to define custom connection configuration.
	}
	
	protected abstract Map<String, Object> getRequestPostData(Email email);
	
	protected abstract String getServiceUrl();

}
