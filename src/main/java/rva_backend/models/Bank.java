package rva_backend.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Bank implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name= "BANK_SEQ_GENERATOR", sequenceName = "BANK_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "BANK_SEQ_GENERATOR")
	private int id;
	private String name;
	private String contact;
	private int tin;
	
	
	@OneToMany(mappedBy="bank")
	@JsonIgnore
	private List<Branch> branch;
	
	public Bank() {
		
	}
	
	public Bank(int id, String name, String contact, int tin) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.tin = tin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getTin() {
		return tin;
	}

	public void setTin(int tin) {
		this.tin = tin;
	}

	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}
	
	

}
