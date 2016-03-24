package org.imu.myfinance.vaadin;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

import org.imu.myfinance.model.Finance;
import org.imu.myfinance.model.User;
import org.imu.myfinance.service.remote.FinanceServiceRemote;
import org.imu.myfinance.service.remote.UserServiceRemote;
import org.imu.myfinance.util.EjbClientUtil;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
public class FinanceForm extends UI {

	private static final long serialVersionUID = 1L;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = UserForm.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}

	@EJB
	private static FinanceServiceRemote finEjb;
	private static UserServiceRemote userEjb;

	@Override
	protected void init(VaadinRequest request) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		
		setContent(layout);

		Context ctx = EjbClientUtil.getInitialContext();
		try {
			finEjb = (FinanceServiceRemote)ctx.lookup(getLookupFinName());
			userEjb = (UserServiceRemote) ctx.lookup(getLookupUserName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		List<User> users = userEjb.getUsers();
		
		ComboBox combo = new ComboBox("User :");
		combo.setNewItemsAllowed(false);
		combo.setNullSelectionAllowed(false);
		
		for (User user : users) {
			combo.addItems(user.getUserId());
			combo.setItemCaption(user.getUserId(), user.getUserName());
		}
		
		layout.addComponent(combo);
		
		final Grid grid = new Grid();
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setSizeFull();
		grid.setColumnReorderingAllowed(true);
			
		layout.addComponent(grid);
		layout.setExpandRatio(combo, (float) .1);
		layout.setExpandRatio(grid, (float) .9);
		
		combo.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				BigInteger id= (BigInteger) event.getProperty().getValue();
				User user = userEjb.getUserById(id);
				List<Finance> finances = finEjb.getFinanceByUser(user);
				BeanItemContainer<Finance> container = new BeanItemContainer<Finance>(Finance.class,finances);
				grid.setCaption("List Of Finance, User : "+  user);
				grid.setContainerDataSource(container);
				
				grid.removeAllColumns();
				grid.addColumn("financeId");
				grid.addColumn("financeDate");
				grid.addColumn("financeAmount");
				grid.addColumn("type");
				grid.addColumn("financeDesc");
				
			}
		});
	
	}

	private static String getLookupFinName() {
		/*
		 * The app name is the EAR name of the deployed EJB without .ear suffix.
		 * Since we haven't deployed the application as a .ear, the app name for
		 * us will be an empty string
		 */
		String appName = "MyFinance";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = "MyFinanceService";

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This
		 * can be an empty string if distinct name is not specified.
		 */
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = "FinanceServiceImpl";

		// Fully qualified remote interface name
		final String interfaceName = FinanceServiceRemote.class.getName();

		// Create a look up string name
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;

		// name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" +
		// interfaceName;

		return name;
	}
	
	private static String getLookupUserName() {
		/*
		 * The app name is the EAR name of the deployed EJB without .ear suffix.
		 * Since we haven't deployed the application as a .ear, the app name for
		 * us will be an empty string
		 */
		String appName = "MyFinance";

		/*
		 * The module name is the JAR name of the deployed EJB without the .jar
		 * suffix.
		 */
		String moduleName = "MyFinanceService";

		/*
		 * AS7 allows each deployment to have an (optional) distinct name. This
		 * can be an empty string if distinct name is not specified.
		 */
		String distinctName = "";

		// The EJB bean implementation class name
		String beanName = "UserServiceImpl";

		// Fully qualified remote interface name
		final String interfaceName = UserServiceRemote.class.getName();

		// Create a look up string name
		String name = "ejb:" + appName + "/" + moduleName + "/" + distinctName
				+ "/" + beanName + "!" + interfaceName;

		// name = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" +
		// interfaceName;

		return name;
	}
}