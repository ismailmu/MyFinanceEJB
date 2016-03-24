package org.imu.myfinance.service.remote;

import javax.ejb.Remote;

import org.imu.myfinance.service.HelloService;

@Remote
public interface HelloServiceRemote extends HelloService {
	
}
