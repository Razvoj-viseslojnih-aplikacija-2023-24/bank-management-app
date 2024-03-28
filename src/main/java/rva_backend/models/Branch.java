package rva_backend.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;


@Entity
public class Branch implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator (name= "BRANCH_SEQ_GENERATOR", sequenceName = "BRANCH_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "BRANCH_SEQ_GENERATOR")
	private int id;
	private String adress;
	private int counters;
	private boolean hasBoss;

	@ManyToOne
	@JoinColumn(name="bank")
	private Bank bank;
	
	
	@OneToMany (mappedBy= "branch")
	@JsonIgnore
	private List<BankService> bankService;
	
	public Branch() {
		
	}

	public Branch(int id, String adress, int counters, boolean hasBoss, Bank bank, List<BankService> bankService) {
		super();
		this.id = id;
		this.adress = adress;
		this.counters = counters;
		this.hasBoss = hasBoss;
		this.bank = bank;
		this.bankService = bankService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getCounters() {
		return counters;
	}

	public void setCounters(int counters) {
		this.counters = counters;
	}

	public boolean isHasBoss() {
		return hasBoss;
	}

	public void setHasBoss(boolean hasBoss) {
		this.hasBoss = hasBoss;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<BankService> getBankService() {
		return bankService;
	}

	public void setBankService(List<BankService> bankService) {
		this.bankService = bankService;
	}
	
	
}
