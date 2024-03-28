package rva_backend.servisi;

import java.util.List;

import rva_backend.models.Dobavljac;

public interface DobavljacService extends CrudService<Dobavljac> {
	List<Dobavljac> getDobavljacsByAdresa(String adresa);
}
