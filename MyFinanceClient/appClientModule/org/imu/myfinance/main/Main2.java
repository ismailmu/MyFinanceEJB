package org.imu.myfinance.main;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;

import org.imu.myfinance.model.User;
import org.imu.myfinance.service.remote.UserServiceRemote;
import org.imu.myfinance.util.EjbClientUtil;

public class Main2 {

	@EJB
	private static UserServiceRemote ejb;
	
	public static void main(String[] args) {
		try {
			Context ctx = EjbClientUtil.getInitialContext();
			String jndiname = getLookupName();
			ejb = (UserServiceRemote)ctx.lookup(jndiname);
			List<User> users = ejb.getUsers();
			for (User mUser : users) {
				System.out.println("user id : " + mUser.getUserId());
				System.out.println("user name : " + mUser.getUserName());
				System.out.println("created date : " + mUser.getCreatedDate().toString());
				System.out.println("----------------------------------------------");
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Main2() {
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
		        String beanName = "UserServiceImpl";
		 
		        // Fully qualified remote interface name
		        final String interfaceName = UserServiceRemote.class.getName();
		 
		        // Create a look up string name
		        String name = "ejb:" + appName + "/" + moduleName + "/" + 
		            distinctName    + "/" + beanName + "!" + interfaceName;
		        
		        //name = "ejb:" + appName + "/" + moduleName + "/"  + beanName + "!" + interfaceName;
		 
		        return name;
		    }

}