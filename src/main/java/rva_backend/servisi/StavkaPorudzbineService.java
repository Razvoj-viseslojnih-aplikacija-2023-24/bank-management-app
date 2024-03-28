package rva_backend.servisi;

import java.util.List;

import rva_backend.models.Artikl;
import rva_backend.models.Porudzbina;
import rva_backend.models.StavkaPorudzbine;

public interface StavkaPorudzbineService extends CrudService<StavkaPorudzbine> {
	
	List<StavkaPorudzbine> getStavkasByCenaLessThan(double cena);
	List<StavkaPorudzbine> getByForeignKey(Artikl artikl);
	List<StavkaPorudzbine> getByForeignKey(Porudzbina porudzbina);
}
