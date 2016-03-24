package org.imu.myfinance.service.impl;

import javax.ejb.Stateless;

import org.imu.myfinance.service.local.HelloServiceLocal;
import org.imu.myfinance.service.remote.HelloServiceRemote;

@Stateless
public class HelloServiceImpl implements HelloServiceRemote,HelloServiceLocal  {

	public HelloServiceImpl() {}
	
	@Override
	public void sayHello() {
		System.out.println("Hello EJB 3.x");
	}

	@Override
	public String sayHello(String name) {
		
		return "Hello " + name;
	}

}
