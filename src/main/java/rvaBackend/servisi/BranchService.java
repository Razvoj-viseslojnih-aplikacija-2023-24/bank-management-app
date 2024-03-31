package rvaBackend.servisi;

import java.util.List;

import rvaBackend.models.Bank;
import rvaBackend.models.Branch;


public interface BranchService extends CrudService<Branch> {
	List<Branch> getBranchesByAddress(String address);
	List<Branch> getForeignKey(Bank bank);
}
