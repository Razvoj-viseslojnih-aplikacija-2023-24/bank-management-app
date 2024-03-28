package rva_backend.servisi;

import java.util.List;

import rva_backend.models.Bank;


public interface BankService extends CrudService<Bank> {
	List<Bank> getBanksByTin(int tin);

}
