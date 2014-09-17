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

import java.util.HashMap;
import java.util.Map;

import net.terryyiu.emailservice.core.Email;
import net.terryyiu.emailservice.util.EmailUtil;

public class SendGridEmailServiceProvider extends AbstractEmailServiceProvider {
	
	private static final String SERVICE_URL = "https://api.sendgrid.com/api/mail.send.json";

	@Override
	protected Map<String, Object> getRequestPostData(Email email) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// Create two String arrays for storing direct recipient
		// email addresses and contact names.
		EmailUtil.addToMapIfNotNull(map, "toname", EmailUtil.getNames(email.getTo()));
		EmailUtil.addToMapIfNotNull(map, "to", EmailUtil.getEmailAddresses(email.getTo()));
		
		// Create a String array for storing carbon copy recipient
		// email addresses.
		EmailUtil.addToMapIfNotNull(map, "cc", EmailUtil.getEmailAddresses(email.getCc()));
		
		// Create a String array for storing blind carbon copy recipient
		// email addresses.
		EmailUtil.addToMapIfNotNull(map, "bcc", EmailUtil.getEmailAddresses(email.getBcc()));
		
		map.put("subject", email.getSubject());
		map.put("text", email.getMessage());
		
		map.put("from", email.getFrom().getEmailAddress());
		map.put("fromname", email.getFrom().getName());
		
		return map;
	}
	
	@Override
	protected String getServiceUrl() {
		return SERVICE_URL;
	}
	
	@Override
	public String toString() {
		return "SendGrid";
	}
	
}
