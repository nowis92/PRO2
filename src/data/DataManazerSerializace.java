package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Polozka;

//Když implementujeme rozhraní, musíme importovat i jeho metody.
public class DataManazerSerializace implements DataManazer{

	@Override
	public List<Polozka> nactiData(String soubor) throws Exception {
		ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(soubor));
		ArrayList<Polozka> listPolozky = (ArrayList<Polozka>)ois.readObject();
		ois.close();
		return listPolozky;
	}

	@Override
	public void ulozData(String soubor, List<Polozka> listPolozky) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(soubor));
		oos.writeObject(listPolozky);
		oos.close();
	}

}
