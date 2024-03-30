package rvaBackend.servisi;

import java.util.List;

import rvaBackend.models.Dobavljac;

public interface DobavljacService extends CrudService<Dobavljac> {
	List<Dobavljac> getDobavljacsByAdresa(String adresa);
}
