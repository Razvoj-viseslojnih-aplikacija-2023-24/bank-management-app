package rvaBackend.servisi;

import java.util.List;

import rvaBackend.models.BankService;
import rvaBackend.models.Branch;
import rvaBackend.models.User;



public interface BankServiceService extends CrudService<BankService> {

	List<BankService> getServicesByName(String name);
	List<BankService> getByForeignKey(Branch branch);
	List<BankService> getByForeignKey(User user);
}
