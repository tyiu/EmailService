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

import java.util.ArrayList;
import java.util.List;

/**
 * This class selects a random available email service provider to forward email send requests to.
 */
public class EmailServiceProviderSelector {
	
	/**
	 * Singleton instance of email service provider selector.
	 */
	private static EmailServiceProviderSelector singleton;
	
	/**
	 * List of available email providers.
	 * Providers from this list could potentially move to unavailableProviders during runtime.
	 */
	private final List<EmailServiceProvider> availableProviders;
	
	/**
	 * List of unavailable email providers.
	 * Providers from this list could potentially move to availableProviders during runtime.
	 */
	private final List<EmailServiceProvider> unavailableProviders;
	
	/**
	 * Gets the singleton instance of email service provider selector.
	 * @return EmailServiceProviderSelector singleton
	 */
	public static EmailServiceProviderSelector getSingleton() {
		if (singleton == null) {
			singleton = new EmailServiceProviderSelector();
		}
		return singleton;
	}
	
	/**
	 * Creates a new email service provider selector.
	 */
	private EmailServiceProviderSelector() {
		availableProviders = new ArrayList<EmailServiceProvider>();
		unavailableProviders = new ArrayList<EmailServiceProvider>();
		
		availableProviders.add(new MailgunEmailServiceProvider());
		availableProviders.add(new MandrillEmailServiceProvider());
		availableProviders.add(new SendGridEmailServiceProvider());
	}
	
	/**
	 * Gets a random available {@link EmailServiceProvider}.
	 * @return EmailServiceProvider
	 */
	public EmailServiceProvider getAvailableProvider() {
		int size = availableProviders.size();
		if (size == 0) {
			return null;
		}
		
		int index = (int) (Math.random() * size);
		
		System.out.println("Random index " + index);
		return availableProviders.get(index);
	}
	
}
