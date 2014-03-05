package com.tistory.tazz009.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name="USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = -5143503006821860152L;

	@Id
	@Column(name = "USER_ID", nullable = false)
	private String userId;
	
	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@ElementCollection
	@JoinTable(
			name="ADDRESS", 
			joinColumns=@JoinColumn(name="USER_ID")
	)
	private Collection<Address> addresses;
	
	public User() {
		 this.addresses = new ArrayList<Address>();
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(
				this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
