package apl;

import javax.swing.SwingUtilities;

import gui.HlavniOkno;

public class NakupniListekAP {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new HlavniOkno().setVisible(true);
				
			}
		});

	}

}
