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

import java.io.IOException;

import net.terryyiu.emailservice.core.Email;

/**
 * Defines an email service provider that email send requests can be forwarded to.
 */
public interface EmailServiceProvider {

	/**
	 * Sends an email through this email service provider.
	 * @param email the email to send
	 * @return true if the email was sent successfully, false otherwise.
	 * @throws IOException thrown if the email could not be sent
	 */
	boolean send(Email email) throws IOException;
	
}
