package org.imu.myfinance.service;

import java.util.List;

import org.imu.myfinance.model.Finance;
import org.imu.myfinance.model.User;

public interface FinanceService {
	public List<Finance> getFinances();
	public List<Finance> getFinanceByUser(User user);
}
