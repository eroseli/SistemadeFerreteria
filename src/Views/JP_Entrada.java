package Views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class JP_Entrada extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public JP_Entrada() {
		setLayout(null);
		
		JLabel JL_entrada = new JLabel("Bienvenido Usuairo al sistema");
		JL_entrada.setForeground(new Color(128, 128, 128));
		JL_entrada.setHorizontalAlignment(SwingConstants.CENTER);
		JL_entrada.setBounds(0, 103, 450, 42);
		JL_entrada.setFont(new Font("Segoe UI", Font.BOLD, 26));
		add(JL_entrada);

	}
}
