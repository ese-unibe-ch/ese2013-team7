package com.ese2013.mensaunibe.model.api;

import java.math.BigInteger;
import java.security.MessageDigest;

import android.accounts.Account;
import android.accounts.AccountManager;

import com.ese2013.mensaunibe.App;

/**
 * @author group7
 * @author Jan Binzegger
 */

public class UserEmailMD5Fetcher {
	/**
	 * reads the user's account email and returns a hash of it
	 * @return the md5 hash of the user's email
	 */
	public static String getEmail() {
	      AccountManager accountManager = AccountManager.get(App.getAppContext()); 
	      Account account = getAccount(accountManager);

	      if (account == null) {
	    		return null;
	      } else {
	    	  return doMD5(account.name);
	      }
	  }

	  private static Account getAccount(AccountManager accountManager) {
	      Account[] accounts = accountManager.getAccountsByType("com.google");
	      Account account;
	      if (accounts.length > 0) {
	    	  account = accounts[0];      
	      } else {
	    	  account = null;
	      }
	      return account;
	  }
	  
	  /**
	   * do the actual md5 hashing
	   * @param string
	   * @return a md5-hashed string
	   */
	  public static String doMD5(String string) {
		  String hashtext = null;
		  
		  try {
			  MessageDigest m = MessageDigest.getInstance("MD5");
			  m.reset();
			  m.update(string.getBytes());
			  byte[] digest = m.digest();
			  BigInteger bigInt = new BigInteger(1,digest);
			  hashtext = bigInt.toString(16);
			  // Now we need to zero pad it if you actually want the full 32 chars.
			  while(hashtext.length() < 32 ){
				  hashtext = "0"+hashtext;
			  }
		  } catch (Exception e) {
			  
		  }
		  return hashtext;
	  }
}
