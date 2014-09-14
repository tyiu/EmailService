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

package net.terryyiu.emailservice.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contact information for a single entity.
 */
public class Contact {
	/**
	 * Name of the contact.
	 */
	private String name;
	
	/**
	 * Email address of the contact.
	 */
	private String emailAddress;
	
	/**
	 * Creates a new contact. Called when JSON is deserialized through Jackson.
	 */
	public Contact() {
		// Jackson deserialization
	}
	
	/**
	 * Creates a new contact with a given name and email address.
	 * @param name
	 * @param emailAddress
	 */
	public Contact(String name, String emailAddress) {
		this.name = name;
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Gets the name of the contact.
	 * @return name
	 */
	@JsonProperty
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the email address of the contact.
	 * @return email address
	 */
	@JsonProperty
	public String getEmailAddress() {
		return emailAddress;
	}
	
	@Override
	public String toString() {
		if (name == null) {
			return emailAddress;
		}
		
		return name + " <" + emailAddress + ">";
	}
}
