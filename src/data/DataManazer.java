package data;

import java.util.List;

import model.Polozka;

public interface DataManazer {
	
	public List<Polozka> nactiData(String soubor) throws Exception;
	
	public void ulozData(String soubor, List<Polozka> listPolozky) throws Exception;

	
}
