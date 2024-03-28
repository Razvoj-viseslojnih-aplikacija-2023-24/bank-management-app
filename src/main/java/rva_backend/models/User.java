package rva_backend.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator (name= "USER_SEQ_GENERATOR", sequenceName = "USER_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
	private int id;
	private String name;
	private String surname;
	private String idNumber;
	
	
	@OneToMany (mappedBy= "user")
	@JsonIgnore
	private List<BankService> bankService;
	
	private User () {
		
	}
	
	private User (int id, String name, String surname, String idNumber) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.idNumber = idNumber;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public List<BankService> getBankService() {
		return bankService;
	}

	public void setBankService(List<BankService> bankService) {
		this.bankService = bankService;
	}
	
	
}
