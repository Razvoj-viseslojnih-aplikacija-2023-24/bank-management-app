package rvaBackend.servisi;

import java.util.List;

import rvaBackend.models.Dobavljac;
import rvaBackend.models.Porudzbina;

public interface PorudzbinaService extends CrudService<Porudzbina> {
	List<Porudzbina> getPorudzbinaByPlaceno(boolean placeno);
	List<Porudzbina> getForeignKey(Dobavljac dobavljac);
}
