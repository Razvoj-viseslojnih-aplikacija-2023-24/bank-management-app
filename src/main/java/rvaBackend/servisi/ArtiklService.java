package rvaBackend.servisi;

import java.util.List;

import rvaBackend.models.Artikl;

public interface ArtiklService extends CrudService<Artikl> {
	
	List<Artikl> getArtiklsByNaziv(String naziv);
}
