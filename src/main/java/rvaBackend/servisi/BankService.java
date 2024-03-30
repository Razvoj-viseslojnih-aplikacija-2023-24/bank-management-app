package rvaBackend.servisi;

import java.util.List;

import rvaBackend.models.Bank;


public interface BankService extends CrudService<Bank> {
	List<Bank> getBanksByTin(int tin);

}
