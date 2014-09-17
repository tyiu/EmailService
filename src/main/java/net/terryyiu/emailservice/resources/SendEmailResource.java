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

package net.terryyiu.emailservice.resources;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.terryyiu.emailservice.core.Email;
import net.terryyiu.emailservice.providers.EmailServiceProvider;
import net.terryyiu.emailservice.providers.EmailServiceProviderSelector;

@Path("/email/send")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SendEmailResource {
	
	@POST
	public Response send(Email email) throws IOException {
		if (email.getFrom() == null || email.getTo() == null || email.getSubject() == null || email.getMessage() == null) {
			return Response.status(Status.BAD_REQUEST).entity(
					"Invalid request. The from, to, subject, and message fields are required.").build();
		}
		
		// Get available email service provider at random to send email through.
		EmailServiceProvider provider = EmailServiceProviderSelector.getSingleton().getAvailableProvider();
		if (provider == null) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
					"No available email service providers to send email to.").build();
		}
		
		boolean success = provider.send(email);
		if (!success) {
			// TODO Remove failed provider from available to unavailable list in selector
			// and keep attempting other providers.
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(
					"Could not send email through email service provider " + provider.toString()).build();
		}
		
		return Response.status(Status.OK).entity(
				String.format(
						"/email/send is called. from=%s to=%s subject=%s message=%s", 
						email.getFrom(), 
						email.getTo()[0], 
						email.getSubject(), 
						email.getMessage()
						)).build();
	}
}
