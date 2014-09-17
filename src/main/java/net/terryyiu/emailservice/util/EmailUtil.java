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

package net.terryyiu.emailservice.util;

import java.util.Map;

import net.terryyiu.emailservice.core.Contact;

/**
 * This class contains a collection of utility methods that are used for
 * performing various operations related to this email service.
 */
public final class EmailUtil {
	
	/**
	 * Instances of this class should not be created.
	 */
	private EmailUtil() {
		// No-op.
	}
	
	/**
	 * Adds a value V into a map under key K only if the value is not null.
	 * 
	 * @param map
	 *            the map to add the entry under
	 * @param key
	 *            the key to add to the map
	 * @param value
	 *            the value to add to the map
	 */
	public static final <K, V> void addToMapIfNotNull(Map<K, V> map, K key, V value) {
		if (value == null) {
			return;
		}
		
		map.put(key, value);
	}
	
	/**
	 * Gets an array of email addresses extracted from a given array of contacts
	 * in the same order.
	 * 
	 * @param contacts
	 *            the list of contacts to extract email addresses from
	 * @return array of email addresses
	 */
	public static final String[] getEmailAddresses(Contact[] contacts) {
		if (contacts == null) {
			return null;
		}

		int length = contacts.length;
		String[] emails = new String[length];
		for (int i = 0; i < length; ++i) {
			emails[i] = contacts[i].getEmailAddress();
		}
		
		return emails;
	}
	
	/**
	 * Gets an array of names extracted from a given array of contacts in the
	 * same order.
	 * 
	 * @param contacts
	 *            the list of contacts to extract names from
	 * @return array of names
	 */
	public static final String[] getNames(Contact[] contacts) {
		if (contacts == null) {
			return null;
		}

		int length = contacts.length;
		String[] names = new String[length];
		for (int i = 0; i < length; ++i) {
			names[i] = contacts[i].getName();
		}
		
		return names;
	}
	
	/**
	 * Gets a comma delimited string containing all the email addresses
	 * extracted from a given array of contacts
	 * 
	 * @param contacts the list of contacts to extract email addresses from
	 * @return comma delimited string of email addresses
	 */
	public static final String getCommaDelimitedEmailAddresses(Contact[] contacts) {
		if (contacts == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < contacts.length; ++i) {
			if (i > 0) {
				sb.append(",");
			}
			
			sb.append(contacts[i].toString());
		}
		
		return sb.toString();
	}
}
