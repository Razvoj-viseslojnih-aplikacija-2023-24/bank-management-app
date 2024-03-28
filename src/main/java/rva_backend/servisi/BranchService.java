package rva_backend.servisi;

import java.util.List;

import rva_backend.models.Bank;
import rva_backend.models.Branch;


public interface BranchService extends CrudService<Branch> {
	List<Branch> getBranchesByAdress(String adress);
	List<Branch> getForeignKey(Bank bank);
}
