package model;

import javax.swing.table.AbstractTableModel;

public class TableModelPolozky extends AbstractTableModel{
	
	private String[] sloupce = {"V kosiku", "Polozka","Mnozstvi","Cena"};
	private PolozkyServices ps;
	
	public TableModelPolozky(PolozkyServices ps){
		this.ps = ps;
	}
	
	
	
	
	
	
	@Override
	public int getColumnCount() {
		return sloupce.length;
	}

	@Override
	public int getRowCount() {
		return ps.getListPolozky().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Polozka p = ps.getListPolozky().get(rowIndex);
		
		switch (columnIndex){
		case 0: return ps.isPolozkaVKosiku(p);
		case 1: return p.getNazev();
		case 2: return p.getPocet();
		case 3: return p.getCena();
		default:
		return null;
	
		}	
	}
	
	@Override
	public String getColumnName(int column) {
		return sloupce[column];
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex){
			case 0: return boolean.class;
			case 1: return String.class;
			case 2: return Integer.class;
			case 3: return String.class;
			default: return String.class;
			}
	}
	
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 0 ;	
	}
	
	
	
}
