package org.shop.serviceI.dto;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 284818240483384387L;

	private String id;
	private String name;
	private String phone;
	private String sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
