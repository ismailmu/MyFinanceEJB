package org.imu.myfinance.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.imu.myfinance.model.User;
import org.imu.myfinance.service.local.UserServiceLocal;
import org.imu.myfinance.service.remote.UserServiceRemote;

@Stateless
public class UserServiceImpl implements UserServiceLocal, UserServiceRemote {

	@PersistenceContext(unitName="MyFinanceService")
	private EntityManager em;
	//private EntityManagerFactory emf;
	
	private UserServiceImpl(){
		init();
	}
	
	private void init() {
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		//emf = Persistence.createEntityManagerFactory("MyFinanceService");
		//em = emf.createEntityManager();
		//EntityTransaction t = em.getTransaction();
		
		//t.begin();
		Query q = em.createQuery("select u from User u");
		List<User> users = q.getResultList();
		//t.commit();
		
		//em.close();
	
		return users;
	}

	@Override
	public User getUserById(BigInteger id) {
		Query q = em.createQuery("select u from User u where u.userId = :id");
		q.setParameter("id", id);
		return (User) q.getSingleResult();
	}

}
