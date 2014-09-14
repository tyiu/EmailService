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

package net.terryyiu.emailservice;

import net.terryyiu.emailservice.resources.SendEmailResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class EmailServiceApplication extends Application<EmailServiceConfiguration> {
	
	public static void main(String[] args) throws Exception {
		new EmailServiceApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<EmailServiceConfiguration> bootstrap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(EmailServiceConfiguration configuration,
			Environment environment) throws Exception {
		final SendEmailResource resource = new SendEmailResource();
		environment.jersey().register(resource);
	}

}
