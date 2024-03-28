package rva_backend.servisi;

import java.util.List;

import rva_backend.models.Artikl;

public interface ArtiklService extends CrudService<Artikl> {
	
	List<Artikl> getArtiklsByNaziv(String naziv);
}
