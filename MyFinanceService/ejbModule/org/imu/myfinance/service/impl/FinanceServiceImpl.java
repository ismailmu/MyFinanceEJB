package org.imu.myfinance.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.imu.myfinance.model.Finance;
import org.imu.myfinance.model.User;
import org.imu.myfinance.service.local.FinanceServiceLocal;
import org.imu.myfinance.service.remote.FinanceServiceRemote;

@Stateless
@SuppressWarnings("unchecked")
public class FinanceServiceImpl implements FinanceServiceLocal,
		FinanceServiceRemote {

	@PersistenceContext(unitName="MyFinanceService")
	private EntityManager em;
	

	@Override
	public List<Finance> getFinances() {
		
		Query q = em.createQuery("select f from Finance f");
		return q.getResultList();
	}

	@Override
	public List<Finance> getFinanceByUser(User user) {
		Query q = em.createQuery("select f from Finance f where f.userFinanceCreated = :user");
		q.setParameter("user", user);
		return q.getResultList();
	}

}
