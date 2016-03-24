package org.imu.myfinance.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the m_user database table.
 * 
 */
@Entity
@Table(name="m_user")
@NamedQuery(name="User.getAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private BigInteger userId;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_pass")
	private String userPass;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User userCreated;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name="modified_by")
	private User userModified;

	@Column(name="modified_date")
	private Date modifiedDate;

	@OneToMany(mappedBy="userFinanceCreated")
	private List<Finance> userFinanceCreated;
	
	@OneToMany(mappedBy="userFinanceModified")
	private List<Finance> userFinanceModified;
	
	@OneToMany(mappedBy="userTypeCreated")
	private List<Type> userTypeCreated;
	
	@OneToMany(mappedBy="userTypeModified")
	private List<Type> userTypeModified;

	public User() {
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public User getUserCreated() {
		return userCreated;
	}

	public void setUserCreated(User userCreated) {
		this.userCreated = userCreated;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUserModified() {
		return userModified;
	}

	public void setUserModified(User userModified) {
		this.userModified = userModified;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<Finance> getUserFinanceCreated() {
		return userFinanceCreated;
	}

	public void setUserFinanceCreated(List<Finance> userFinanceCreated) {
		this.userFinanceCreated = userFinanceCreated;
	}

	public List<Finance> getUserFinanceModified() {
		return userFinanceModified;
	}

	public void setUserFinanceModified(List<Finance> userFinanceModified) {
		this.userFinanceModified = userFinanceModified;
	}

	public List<Type> getUserTypeCreated() {
		return userTypeCreated;
	}

	public void setUserTypeCreated(List<Type> userTypeCreated) {
		this.userTypeCreated = userTypeCreated;
	}

	public List<Type> getUserTypeModified() {
		return userTypeModified;
	}

	public void setUserTypeModified(List<Type> userTypeModified) {
		this.userTypeModified = userTypeModified;
	}
	
	@Override
	public String toString() {
		return getUserName();
	}
}