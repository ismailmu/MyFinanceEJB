package org.imu.myfinance.servlets;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class EjbClientUtils {
	
	private static Context initialContext;
	private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
	
	public static Context getInitialContext(){
		Properties props = new Properties();
		try{
			if(initialContext==null){
				props.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
				initialContext = new InitialContext(props);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return initialContext;
	}

}
