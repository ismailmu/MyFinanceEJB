package org.imu.myfinance.main;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;

import org.imu.myfinance.service.remote.HelloServiceRemote;
import org.imu.myfinance.util.EjbClientUtil;

public class Main1 {

	@EJB
	private static HelloServiceRemote ejb;
	
	public static void main(String[] args) {
		try {
			Context ctx = EjbClientUtil.getInitialContext();
			String jndiname = getLookupName();
			ejb = (HelloServiceRemote)ctx.lookup(jndiname);
			ejb.sayHello();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Main1() {
		super();
	}
	
	private static String getLookupName() {
		/* 
		The app name is the EAR name of the deployed EJB without .ear suffix. 
		Since we haven't deployed the application as a .ear, 
		the app name for us will be an empty string
		*/
		        String appName = "MyFinance";
		 
		        /* The module name is the JAR name of the deployed EJB 
		        without the .jar suffix.
		        */
		        String moduleName = "MyFinanceService";
		 
		/*AS7 allows each deployment to have an (optional) distinct name. 
		This can be an empty string if distinct name is not specified.
		*/
		        String distinctName = "";
		 
		        // The EJB bean implementation class name
		        String beanName = "HelloServiceImpl";
		 
		        // Fully qualified remote interface name
		        final String interfaceName = HelloServiceRemote.class.getName();
		 
		        // Create a look up string name
		        String name = "ejb:" + appName + "/" + moduleName + "/" + 
		            distinctName    + "/" + beanName + "!" + interfaceName;
		        
		        //name = "ejb:" + appName + "/" + moduleName + "/"  + beanName + "!" + interfaceName;
		 
		        return name;
		    }

}