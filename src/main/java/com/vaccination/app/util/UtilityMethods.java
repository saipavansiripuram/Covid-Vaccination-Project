package com.vaccination.app.util;

import org.springframework.stereotype.Component;

@Component
public class UtilityMethods {
	public String getLicenceKey()
	{
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(9);
		for (int i = 0; i < 9; i++) {
		
			int index=(int)(AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString
			      .charAt(index));
		}
		return sb.toString();
	}
}
