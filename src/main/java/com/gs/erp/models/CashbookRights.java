package com.gs.erp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="acd_master_cashbookrights")
public class CashbookRights {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String users;
	private String counter;
	public CashbookRights(Integer id, String users, String counter) {
		super();
		this.id = id;
		this.users = users;
		this.counter = counter;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	@Override
	public String toString() {
		return "CashbookRights [id=" + id + ", users=" + users + ", counter=" + counter + "]";
	}
	
	
}
