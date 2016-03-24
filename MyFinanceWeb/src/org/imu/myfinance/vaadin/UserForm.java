package org.imu.myfinance.vaadin;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

import org.imu.myfinance.model.User;
import org.imu.myfinance.service.remote.UserServiceRemote;
import org.imu.myfinance.util.EjbClientUtil;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
public class UserForm extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = UserForm.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}

	private static final long serialVersionUID = 1L;

	@EJB
	private static UserServiceRemote ejb;

	@Override
	protected void init(VaadinRequest request) {

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		
		setContent(layout);

		Context ctx = EjbClientUtil.getInitialContext();
		String jndiname = getLookupName();
		try {
			ejb = (UserServiceRemote)ctx.lookup(jndiname);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		List<User> users = ejb.getUsers();
		
		final BeanItemContainer<User> container =
				new BeanItemContainer<User>(User.class, users);
		
		Grid gridUser = new Grid(container);
		gridUser.setCaption("List Of User");
		gridUser.setColumnOrder("userId","userName");
		gridUser.removeColumn("userCreated");
		gridUser.removeColumn("userModified");
		gridUser.removeColumn("modifiedDate");
		gridUser.removeColumn("userPass");
		gridUser.removeColumn("userFinanceCreated");
		gridUser.removeColumn("userFinanceModified");
		gridUser.removeColumn("userTypeCreated");
		gridUser.removeColumn("userTypeModified");
		gridUser.setSelectionMode(SelectionMode.SINGLE);
		
		layout.addComponent(gridUser);
		
		gridUser.addItemClickListener(new ItemClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				Notification.show("Value: " +
						container.getContainerProperty(event.getItemId(),
						event.getPropertyId()).getValue().toString());
			}
		});
		gridUser.setSizeFull();
	}

	private static String getLookupName() {
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
