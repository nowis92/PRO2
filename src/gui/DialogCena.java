package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DialogCena extends JDialog {
	private double cena;
	private JTextField tfCena;
	
	public DialogCena(JFrame vlastnik){
		super(vlastnik, true);
		setTitle("Cena položky");
		tfCena = new JTextField("", 20);
		setLayout(new BorderLayout());
		
		JPanel pnlText = new JPanel();
		pnlText.setLayout(new BoxLayout(pnlText, BoxLayout.Y_AXIS));
		pnlText.add(new JLabel("Zadej cenu položky:"));
		pnlText.add(tfCena);
		getContentPane().add(pnlText, BorderLayout.NORTH);
		
		
		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cena = Double.parseDouble(tfCena.getText());
					
					if (cena > 0){
						setVisible(false);
					}else{
						zobrazOptionDialog("Zadej cenu vìtší než 0");
						tfCena.grabFocus();
					}
					
					
					
				} catch (NumberFormatException exp) {
					zobrazOptionDialog("Zadej kladné èíslo");
					tfCena.grabFocus();
				}
				
			}
		});
		
		JButton btStorno = new JButton("Zrusit");
		btStorno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cena = -1;
				setVisible(false);
				
			}
		});
		
		JPanel pnlOvladani = new JPanel(new FlowLayout());
		pnlOvladani.add(btOk);
		pnlOvladani.add(btStorno);
		
		getContentPane().add(pnlOvladani, BorderLayout.SOUTH);
		pack();

	}
	public double getCena() {
		return cena;
	}
	
	
	@Override
	public void setVisible(boolean visible) {
		if (visible){
			tfCena.setText("");
			tfCena.grabFocus();
		}
		
		
		
		super.setVisible(visible);
	}
	
	
	private void zobrazOptionDialog(String zprava){
		JOptionPane.showMessageDialog(this, zprava,"Nakupni listek",JOptionPane.ERROR_MESSAGE);
	}
	
	
	
	
	
	
	
	
}
