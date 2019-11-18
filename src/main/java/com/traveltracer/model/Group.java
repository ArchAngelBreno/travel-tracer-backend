package com.traveltracer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	
	@ManyToMany(mappedBy = "groups")
	private List<User> users;
	
	@OneToMany(mappedBy="group",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<ActivtySpend> activtySpend;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ActivtySpend> getActivtySpend() {
		return activtySpend;
	}

	public void setActivtySpend(List<ActivtySpend> activtySpend) {
		this.activtySpend = activtySpend;
	}
	
	
	
}
