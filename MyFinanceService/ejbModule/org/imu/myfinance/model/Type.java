package org.imu.myfinance.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the m_type database table.
 * 
 */
@Entity
@Table(name="m_type")
@NamedQuery(name="Type.getAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="type_id")
	private BigInteger typeId;

	@Column(name="type_desc")
	private byte typeDesc;

	@Column(name="type_name")
	private String typeName;
	
	@ManyToOne
	@JoinColumn(name="created_by")
	private User userTypeCreated;
	
	@Column(name="created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name="modified_by")
	private User userTypeModified;

	
	@Column(name="modified_date")
	private Date modifiedDate;
	
	//bi-directional many-to-one association to TFinance
	@OneToMany(mappedBy="Type")
	private List<Finance> finances;

	public Type() {
	}

	public BigInteger getTypeId() {
		return typeId;
	}

	public void setTypeId(BigInteger typeId) {
		this.typeId = typeId;
	}

	public byte getTypeDesc() {
		return typeDesc;
	}
	
	public String getTypeMode() {
		if (getTypeDesc() == 1) {
			return "Income";
		}else {
			return "Expense";
		}
	}

	public void setTypeDesc(byte typeDesc) {
		this.typeDesc = typeDesc;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public User getUserTypeCreated() {
		return userTypeCreated;
	}

	public void setUserTypeCreated(User userTypeCreated) {
		this.userTypeCreated = userTypeCreated;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUserTypeModified() {
		return userTypeModified;
	}

	public void setUserTypeModified(User userTypeModified) {
		this.userTypeModified = userTypeModified;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<Finance> getFinances() {
		return finances;
	}

	public void setFinances(List<Finance> finances) {
		this.finances = finances;
	}
	
	@Override
	public String toString() {
		return getTypeName();
	}
}