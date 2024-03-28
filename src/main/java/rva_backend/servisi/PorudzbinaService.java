package rva_backend.servisi;

import java.util.List;

import rva_backend.models.Dobavljac;
import rva_backend.models.Porudzbina;

public interface PorudzbinaService extends CrudService<Porudzbina> {
	List<Porudzbina> getPorudzbinaByPlaceno(boolean placeno);
	List<Porudzbina> getForeignKey(Dobavljac dobavljac);
}
