package org.imu.myfinance.service;

import java.math.BigInteger;
import java.util.List;

import org.imu.myfinance.model.User;

public interface UserService {
	public List<User> getUsers();
	public User getUserById(BigInteger id);
}
