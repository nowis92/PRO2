package model;

import java.io.Serializable;

public class Polozka implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nazev;
	private int pocet;
	transient private double cena;
	
	public Polozka(String nazev, int pocet, double cena) {
		this.nazev = nazev;
		this.pocet = pocet;
		this.cena = cena;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public int getPocet() {
		return pocet;
	}

	public void setPocet(int pocet) {
		this.pocet = pocet;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
}
