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
import com.google.common.collect.ImmutableList;

public class Email {
	private String subject;
	private String message;
	private Contact from;
	private ImmutableList<Contact> to;
	private ImmutableList<Contact> cc;
	private ImmutableList<Contact> bcc;
	
	public Email() {
		// Jackson deserialization
	}
	
	public Email(
			String subject, 
			String message, 
			Contact from, 
			ImmutableList<Contact> to,
			ImmutableList<Contact> cc,
			ImmutableList<Contact> bcc) {
		this.subject = subject;
		this.message = message;
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
	}
	
	@JsonProperty
	public String getSubject() {
		return subject;
	}
	
	@JsonProperty
	public String getMessage() {
		return message;
	}
	
	@JsonProperty
	public Contact getFrom() {
		return from;
	}
	
	@JsonProperty
	public ImmutableList<Contact> getTo() {
		return to;
	}
	
	@JsonProperty
	public ImmutableList<Contact> getCc() {
		return cc;
	}
	
	@JsonProperty
	public ImmutableList<Contact> getBcc() {
		return bcc;
	}
}
