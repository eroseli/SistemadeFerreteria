package Views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class JP_Entrada extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public JP_Entrada() {
		setLayout(null);
		
		JLabel JL_entrada = new JLabel("Bienvenido Usuairo al sistema");
		JL_entrada.setBounds(71, 205, 372, 42);
		JL_entrada.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		add(JL_entrada);

	}
}
