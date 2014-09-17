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

import net.terryyiu.emailservice.core.Contact;
import net.terryyiu.emailservice.core.Email;

/**
 * This service provider sends email through Mandrill.
 */
public class MandrillEmailServiceProvider extends AbstractEmailServiceProvider {

	/**
	 * Mandrill's RESTful endpoint that handles sending emails.
	 */
	private static final String SERVICE_URL = "https://mandrillapp.com/api/1.0/messages/send.json";
	
	/**
	 * API key to send emails through Mandrill.
	 * TODO Move api key into a config file.
	 */
	private static final String API_KEY = "MandrillApiKey";

	@Override
	protected Map<String, Object> getRequestPostData(Email email) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", API_KEY);
		
		Map<String, Object> messageMap = new HashMap<String, Object>();
		Contact[] toContacts = email.getTo();
		Contact[] ccContacts = email.getCc();
		Contact[] bccContacts = email.getBcc();
		int toLength = (toContacts == null) ? 0 : toContacts.length;
		int ccLength = (ccContacts == null) ? 0 : ccContacts.length;
		int bccLength = (bccContacts == null) ? 0 : bccContacts.length;
		
		MandrillRecipient[] recipients = new MandrillRecipient[toLength + ccLength + bccLength];
		int count = 0;
		
		// TODO Refactor the following code.
		for (int i = 0; i < toLength; ++i) {
			Contact contact = toContacts[i];
			recipients[count++] = new MandrillRecipient(contact.getEmailAddress(), contact.getName(), "to");
		}
		for (int i = 0; i < ccLength; ++i) {
			Contact contact = ccContacts[i];
			recipients[count++] = new MandrillRecipient(contact.getEmailAddress(), contact.getName(), "cc");
		}
		for (int i = 0; i < bccLength; ++i) {
			Contact contact = bccContacts[i];
			recipients[count++] = new MandrillRecipient(contact.getEmailAddress(), contact.getName(), "bcc");
		}
		
		messageMap.put("to", recipients);
		map.put("message", messageMap);
		
		map.put("subject", email.getSubject());
		map.put("text", email.getMessage());
		map.put("from_email", email.getFrom().getEmailAddress());
		map.put("from_name", email.getFrom().getName());
		
		return map;
	}
	
	@Override
	protected String getServiceUrl() {
		return SERVICE_URL;
	}
	
	@Override
	public String toString() {
		return "Mandrill";
	}

}
