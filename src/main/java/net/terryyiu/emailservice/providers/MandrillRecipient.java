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

/**
 * This class encapsulates a specific format that an email recipient looks like
 * for the Mandrill email service provider.
 */
public class MandrillRecipient {
	/**
	 * Email address of the recipient.
	 */
	private final String emailAddress;
	
	/**
	 * Name of the recipient.
	 */
	private final String name;
	
	/**
	 * Recipient type. Either "to", "cc", or "bcc".
	 */
	private final String type;
	
	/**
	 * Creates a new Mandrill recipient through the Mandrill email service provider. 
	 * @param emailAddress email address of the recipient
	 * @param name name of the recipient
	 * @param type recipient type. Either "to", "cc", or "bcc"
	 */
	public MandrillRecipient(String emailAddress, String name, String type) {
		this.emailAddress = emailAddress;
		this.name = name;
		this.type = type;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
}
