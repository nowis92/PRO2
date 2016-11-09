package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import model.Polozka;

public class DataManazerCSV implements DataManazer {
	private static final String ODD = ";";
	
	
	
	@Override
	public List<Polozka> nactiData(String soubor) throws Exception {
		ArrayList<Polozka> listPolozky = new ArrayList<>();
		
		BufferedReader vstup = new BufferedReader(new FileReader(soubor));
		
		String radek;
		
		while((radek = vstup.readLine()) != null){
			zpracujRadek(radek, listPolozky);
		}
		
		vstup.close();
		return listPolozky;
	}

	private void zpracujRadek(String radek, ArrayList<Polozka> listPolozky) {
		StringTokenizer st = new StringTokenizer(radek, ODD);
		String nazev = st.nextToken();
		String mnozstviTxt = st.nextToken();
		int mnozstvi = Integer.parseInt(mnozstviTxt);
		Polozka p = new Polozka(nazev, mnozstvi, 0);
		listPolozky.add(p);
		
	}

	@Override
	public void ulozData(String soubor, List<Polozka> listPolozky) throws Exception {
		PrintWriter vystup = new PrintWriter(new FileWriter(soubor));
		
		for (Polozka p : listPolozky) {
			vystup.println(p.getNazev() + ODD + p.getPocet());
		}		
		
		vystup.close();
		
	}

}
