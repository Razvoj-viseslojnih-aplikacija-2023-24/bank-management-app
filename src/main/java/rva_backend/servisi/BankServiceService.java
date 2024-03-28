package rva_backend.servisi;

import java.util.List;

import rva_backend.models.BankService;
import rva_backend.models.Branch;
import rva_backend.models.User;



public interface BankServiceService extends CrudService<BankService> {

	List<BankService> getServicesByName(String name);
	List<BankService> getByForeignKey(Branch branch);
	List<BankService> getByForeignKey(User user);
}
