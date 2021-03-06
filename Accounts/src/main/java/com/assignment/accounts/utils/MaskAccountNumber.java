package com.assignment.accounts.utils;

import org.springframework.stereotype.Component;

@Component
public class MaskAccountNumber {
	
	/* public static void main(String[] args) {
		 
	      String account = "1234567898765432";
	      System.out.println("account: " + account);
	 
	      System.out.println("Masked number examples:");
	 
	      //  mask all bust last 4 numbers
	      System.out.println(maskNumber(account, "xxxxxxxxxxxx####"));
	 
	      //  insert dashes
	      System.out.println(maskNumber(account, "####-####-####-####"));
	 
	      //  insert dashes and mask all but last 4 numbers
	      System.out.println(maskNumber(account, "xxxx-xxxx-xxxx-####"));
	   }*/
	 
	 
	   /**
	   * @param number The number in plain format
	   * @param mask The  mask pattern. 
	   *    Use # to include the digit from the position. 
	   *    Use x to mask the digit at that position.
	   *    Any other char will be inserted.
	   *
	   * @return The masked card number
	   */
	   public String maskNumber(String number) {
	 
	      int index = 0;
	      String mask ="xxx-xxx-xxx-####";
	      StringBuilder masked = new StringBuilder();
	      for (int i = 0; i < mask.length(); i++) {
	         char c = mask.charAt(i);
	         if (c == '#') {
	            masked.append(number.charAt(index));
	            index++;
	         } else if (c == 'x') {
	            masked.append(c);
	            index++;
	         } else {
	            masked.append(c);
	         }
	      }
	      return masked.toString();
	   }

}
