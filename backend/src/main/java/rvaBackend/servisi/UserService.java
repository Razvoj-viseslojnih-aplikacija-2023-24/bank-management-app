package rvaBackend.servisi;

import java.util.List;

import rvaBackend.models.User;

public interface UserService extends CrudService<User> {
	
	List<User> getUsersByIdNumber (String idNumber);
}
