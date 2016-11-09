package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.Polozka;
import model.PolozkyServices;
import model.TableModelPolozky;

public class HlavniOkno extends JFrame{
	public static final String SOUBOR = "nakupnilistek.obj";
	public static final String CENA_CELKEM = "cena celkem: ";
	
	private DialogCena dlgCena;
	
	private TableModelPolozky tblModel;
	private JTable tabPolozky;
	private PolozkyServices ps;
	
	private JTextField tfNazev;
	private JTextField tfMnozstvi;
	private JLabel lbCenaCelkem;
	
	private JButton btPridat;
	private JButton btOdebrat;
	private JButton btUloz;
	private JButton btNacti;
	
	
	public HlavniOkno() {
		super ("Nakupni listek");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dlgCena = new DialogCena(this);
		
		ps = new PolozkyServices();
		tblModel = new TableModelPolozky(ps);
		initGUI();
	}
	private void initGUI(){
		tfNazev = new JTextField("rohlik", 10);
		tfMnozstvi = new JTextField("10", 10);
		lbCenaCelkem = new JLabel(CENA_CELKEM + "0 kè");
		
		btPridat = new JButton("Pridat polozku");
		btPridat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pridejPolozkuDoListu();

			}
		});
		btOdebrat = new JButton("Smazat polozku");
		btOdebrat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabPolozky.getSelectedRow() > -1){
					smazPolozkuZListku(tabPolozky.getSelectedRow());
				}
			}
		});
		btUloz = new JButton("Uloz");
		btUloz.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				uloz();
				
			}
		});
		btNacti = new JButton("Nacti");
		btNacti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nacti();
				
			}
		});
		tabPolozky = new JTable(tblModel);
		tabPolozky.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabPolozky.setPreferredScrollableViewportSize(new Dimension(400, 200));
		tabPolozky.setToolTipText("Polozky nakupniho listku");
		tabPolozky.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tabPolozky.getSelectedRow() > -1 && tabPolozky.getSelectedColumn() == 0){
					aktualizujKosik(tabPolozky.getSelectedRow());
					aktualizujCelkovouCenu();
				}
				
			}
		});
		JPanel pnlPolozka = new JPanel();
		pnlPolozka.setLayout(new GridLayout(2, 3));
		pnlPolozka.add(new JLabel("Nazev"));
		pnlPolozka.add(new JLabel("Mnozstvi"));
		pnlPolozka.add(btPridat);
		pnlPolozka.add(tfNazev);
		pnlPolozka.add(tfMnozstvi);
		pnlPolozka.add(btOdebrat);
		
		JPanel pnlSpodni = new JPanel(new BorderLayout());
		pnlSpodni.add(lbCenaCelkem, BorderLayout.NORTH);
		
		JPanel pnlTlacitka = new JPanel(new FlowLayout());
		pnlTlacitka.add(btUloz);
		pnlTlacitka.add(btNacti);
		
		pnlSpodni.add(pnlTlacitka, BorderLayout.SOUTH);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnlPolozka, "North");
		getContentPane().add(new JScrollPane(tabPolozky), "Center");
		getContentPane().add(pnlSpodni, "South");
		pack();
		setSize(680, 480);
		
	}
	protected void aktualizujCelkovouCenu() {
		lbCenaCelkem.setText(CENA_CELKEM + ps.spocitejCenuPolozekVKosiku());
		
	}
	protected void aktualizujKosik(int selectedRow) {
		double cena = 0;
		
		if( ! ps.isPolozkaVKosiku(selectedRow)){
			cena = getCenaJednePolozkyZDialoguOdUzivatele();
		}
		ps.updatePolozky(selectedRow, cena);
		tblModel.fireTableDataChanged();
		
	}
	private double getCenaJednePolozkyZDialoguOdUzivatele() {
		dlgCena.setLocationRelativeTo(this);
		dlgCena.setVisible(true);
		return dlgCena.getCena();
	}
	protected void nacti() {
		try {
			ps.vymazPolozkyZKosiku();
			ps.nactiNakupniListek(SOUBOR);
			tblModel.fireTableDataChanged();
			aktualizujCelkovouCenu();

		} catch (FileNotFoundException e) {
			System.out.println("Došlo k chybì:" + e.getMessage());	
		} catch (IOException e) {
			System.out.println("Došlo k chybì:" + e.getMessage());	
		} catch (ClassNotFoundException e) {
			System.out.println("Došlo k chybì:" + e.getMessage());	
		} catch (Exception e) {
			System.out.println("Došlo k chybì:" + e.getMessage());	
		}
		
	}
	protected void uloz() {
		try {
			ps.ulozNakupniListek(SOUBOR);
		} catch (Exception e) {
			System.out.println("Došlo k chybì:" + e.getMessage());
		}
		
	}
	protected void smazPolozkuZListku(int selectedRow) {
		ps.smazPolozkuZNakupnihoListkuAKosiku(selectedRow);
		tblModel.fireTableDataChanged();
		aktualizujCelkovouCenu();
		
	}
	protected void pridejPolozkuDoListu() {
		String nazev = tfNazev.getText();
		
		if ( ! "".equals(nazev)){
			 try{
				 int mnozstvi = Integer.parseInt(tfMnozstvi.getText());
				 
				 Polozka p = new Polozka(nazev, mnozstvi, 0);
				 ps.pridejpolozku(p);
				 tblModel.fireTableDataChanged();
				 
				 tfNazev.setText("");
				 tfMnozstvi.setText("");
				 tfNazev.grabFocus();
				 
				 
			 }catch(NumberFormatException e){
				 JOptionPane.showMessageDialog(this,
							"Zadejte množství!",
							"Nakupni listek",
							JOptionPane.ERROR_MESSAGE);
					tfMnozstvi.grabFocus();
				 

			 }
			
	
			
		}else{
			JOptionPane.showMessageDialog(this,
					"Zadejte název položky!",
					"Nakupni listek",
					JOptionPane.ERROR_MESSAGE);
			tfNazev.grabFocus();
		}
				
	}
}
