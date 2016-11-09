package model;

import java.util.ArrayList;
import java.util.List;

import data.DataManazer;
import data.DataManazerSerializace;

public class PolozkyServices {
	private List<Polozka> listPolozky;
	private List<Polozka> kosikList;
	private DataManazer dm;
	
	public PolozkyServices(){
		listPolozky = new ArrayList<>();
		kosikList = new ArrayList<>();
		dm = new DataManazerSerializace();
	}
	
	public void pridejpolozku(Polozka p){
		listPolozky.add(p);
	}
	
	public Polozka getPolozka(int ktery){
		return listPolozky.get(ktery);
	}
	
	public void setListPolozky(List<Polozka> listPolozky){
		this.listPolozky = listPolozky;
	}
	
	public List<Polozka> getListPolozky(){
		return listPolozky;
	}
	
	public void smazPolozkuZNakupnihoListkuAKosiku(int ktera){
		Polozka p = listPolozky.remove(ktera);
		
		if (p != null) {
			kosikList.remove(p);
		}
	}
	
	public double spocitejCenuPolozekVKosiku(){
		double cenaCelkem = 0;
		
		for (Polozka p : kosikList) {
			cenaCelkem = cenaCelkem + p.getCena();
		}
		
		return cenaCelkem;
	}
	
	public boolean isPolozkaVKosiku(int ktera){
		return isPolozkaVKosiku(listPolozky.get(ktera));
	}
	
	public boolean isPolozkaVKosiku(Polozka p){
		return kosikList.contains(p);
	}
	
	public void vymazPolozkyZKosiku(){
		//kdyz mazeme polozky z kosiku
		//1. vynulujeme cenu u polozek v kosiku
		//2. odstranime polozky z kosiku
		
		for (Polozka p : kosikList){
			p.setCena(0);
		}
		
		kosikList.clear();
	}
	
	public void updatePolozky(int ktera, double cenaJednohoKusuPolozky){
		Polozka p = listPolozky.get(ktera);
		
		//pokud polozka neni v kosiku
		if( ! isPolozkaVKosiku(p)){
			//tak ji pridame, pokud cena je > 0
			if(cenaJednohoKusuPolozky > 0){
				p.setCena(cenaJednohoKusuPolozky + p.getPocet());
				kosikList.add(p);
			}
		}else{ //polozka je v kosiku, je nutne ji z kosiku vyjmout
			p.setCena(0);
			kosikList.remove(p);
		}
	}
	
	public void nactiNakupniListek(String soubor) throws Exception{
		listPolozky = dm.nactiData(soubor);
	}
	
	public void ulozNakupniListek(String soubor) throws Exception{
		dm.ulozData(soubor, listPolozky);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
