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

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.core.util.Base64;

import net.terryyiu.emailservice.core.Email;
import net.terryyiu.emailservice.util.EmailUtil;

public class MailgunEmailServiceProvider extends AbstractEmailServiceProvider {

	private static final String SERVICE_URL = "https://api.mailgun.net/v2/sandbox1450d16db62f4dbba21c8f961f7c013e.mailgun.org/messages";
	
	/**
	 * API key to send emails through Mailgun.
	 * TODO Move api key into a config file.
	 */
	private static final String API_KEY = "api:MailgunApiKey";

	@Override
	protected Map<String, Object> getRequestPostData(Email email) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// Create String for comma delimited recipient names and email addresses.
		EmailUtil.addToMapIfNotNull(map, "to", EmailUtil.getCommaDelimitedEmailAddresses(email.getTo()));
		EmailUtil.addToMapIfNotNull(map, "cc", EmailUtil.getCommaDelimitedEmailAddresses(email.getCc()));
		EmailUtil.addToMapIfNotNull(map, "bcc", EmailUtil.getCommaDelimitedEmailAddresses(email.getBcc()));
		
		map.put("subject", email.getSubject());
		map.put("text", email.getMessage());
		map.put("from", email.getFrom().toString());
		
		return map;
	}
	
	@Override
	protected void modifyConnection(HttpURLConnection connection) {
		byte[] authBytes = Base64.encode(API_KEY);
		String authString = new String(authBytes);
		connection.setRequestProperty("Authorization", "Basic " + authString);
	}
	
	@Override
	protected String getServiceUrl() {
		return SERVICE_URL;
	}
	
	@Override
	public String toString() {
		return "Mailgun";
	}

}
