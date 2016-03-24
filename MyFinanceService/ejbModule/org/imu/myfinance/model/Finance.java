package org.imu.myfinance.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the t_finance database table.
 * 
 */
@Entity
@Table(name="t_finance")
@NamedQuery(name="Finance.getAll", query="SELECT f FROM Finance f")
public class Finance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="finance_id")
	private BigInteger financeId;

	@Column(name="finance_amount")
	private int financeAmount;

	@Column(name="finance_date")
	private Date financeDate;

	@Column(name="finance_desc")
	private String financeDesc;

	@Column(name="finance_month")
	private int financeMonth;

	@Column(name="finance_year")
	private int financeYear;

	@ManyToOne
	@JoinColumn(name="finance_type")
	private Type Type;

	@ManyToOne
	@JoinColumn(name="created_by")
	private User userFinanceCreated;
	
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="modified_date")
	private Date modifiedDate;
	
	@ManyToOne
	@JoinColumn(name="modified_by")
	private User userFinanceModified;

	public Finance() {
	}

	public BigInteger getFinanceId() {
		return financeId;
	}

	public void setFinanceId(BigInteger financeId) {
		this.financeId = financeId;
	}

	public int getFinanceAmount() {
		return financeAmount;
	}

	public void setFinanceAmount(int financeAmount) {
		this.financeAmount = financeAmount;
	}

	public Date getFinanceDate() {
		return financeDate;
	}

	public void setFinanceDate(Date financeDate) {
		this.financeDate = financeDate;
	}

	public String getFinanceDesc() {
		return financeDesc;
	}

	public void setFinanceDesc(String financeDesc) {
		this.financeDesc = financeDesc;
	}

	public int getFinanceMonth() {
		return financeMonth;
	}

	public void setFinanceMonth(int financeMonth) {
		this.financeMonth = financeMonth;
	}

	public int getFinanceYear() {
		return financeYear;
	}

	public void setFinanceYear(int financeYear) {
		this.financeYear = financeYear;
	}

	public Type getType() {
		return Type;
	}

	public void setType(Type type) {
		Type = type;
	}

	public User getUserFinanceCreated() {
		return userFinanceCreated;
	}

	public void setUserFinanceCreated(User userFinanceCreated) {
		this.userFinanceCreated = userFinanceCreated;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public User getUserFinanceModified() {
		return userFinanceModified;
	}

	public void setUserFinanceModified(User userFinanceModified) {
		this.userFinanceModified = userFinanceModified;
	}
}