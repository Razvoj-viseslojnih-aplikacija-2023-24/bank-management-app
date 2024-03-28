package rva_backend.servisi;

import java.util.List;

import rva_backend.models.User;

public interface UserService extends CrudService<User> {
	
	List<User> getUsersByIdNumber (String idNumber);
}
