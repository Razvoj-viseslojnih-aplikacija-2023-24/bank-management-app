package rva_backend.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
@Entity
public class BankService implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator (name= "BANK_SERVICE_SEQ_GENERATOR", sequenceName = "BANK_SERVICE_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "BANK_SERVICE_SEQ_GENERATOR")
	private int id;
	private String name;
	private String serviceDescription;
	private Date contractDate;
	private double commission;
	
	@ManyToOne
	@JoinColumn(name="branch")
	private Branch branch ;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User user;

	
	public BankService() {
		
	}


	public BankService(int id, String name, String serviceDescription, Date contractDate, double commission,
			Branch branch, User user) {
		super();
		this.id = id;
		this.name = name;
		this.serviceDescription = serviceDescription;
		this.contractDate = contractDate;
		this.commission = commission;
		this.branch = branch;
		this.user = user;
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


	public String getServiceDescription() {
		return serviceDescription;
	}


	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}


	public Date getContractDate() {
		return contractDate;
	}


	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}


	public double getCommission() {
		return commission;
	}


	public void setCommission(double commission) {
		this.commission = commission;
	}


	public Branch getBranch() {
		return branch;
	}


	public void setBranch(Branch branch) {
		this.branch = branch;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}
