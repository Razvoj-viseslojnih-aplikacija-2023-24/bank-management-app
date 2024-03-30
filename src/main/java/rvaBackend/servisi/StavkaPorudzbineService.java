package rvaBackend.servisi;

import java.util.List;

import rvaBackend.models.Artikl;
import rvaBackend.models.Porudzbina;
import rvaBackend.models.StavkaPorudzbine;

public interface StavkaPorudzbineService extends CrudService<StavkaPorudzbine> {
	
	List<StavkaPorudzbine> getStavkasByCenaLessThan(double cena);
	List<StavkaPorudzbine> getByForeignKey(Artikl artikl);
	List<StavkaPorudzbine> getByForeignKey(Porudzbina porudzbina);
}
